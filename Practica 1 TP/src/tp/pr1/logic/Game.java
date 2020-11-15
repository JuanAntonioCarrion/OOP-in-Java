package tp.pr1.logic;
import java.util.Random;
import tp.pr1.lists.SunflowerList;
import tp.pr1.lists.ZombieList;
import tp.pr1.lists.PeashooterList;
import tp.pr1.managers.SuncoinManager;
import tp.pr1.managers.ZombieManager;
import tp.pr1.logic.Level;
import tp.pr1.objects.Peashooter;
import tp.pr1.objects.Zombie;
import tp.pr1.objects.Sunflower;

public class Game {
	public static final int MAX_COLUMNAS = 8;
	public static final int MAX_FILAS = 4;
	private SunflowerList listaGirasoles;
	private ZombieList listaZombies;
	private PeashooterList listaLanzaguisantes;
	private SuncoinManager managerSoles;
	private ZombieManager managerZombies;
	private Level level;
	private int ciclos;
	private Random rand;
	private boolean zombieWins;
	private boolean playerWins;
	
	public Game(Level level, Random rand){
		ciclos = 0;
		this.level = level;
		this.rand = rand;
	}
	
	public void reiniciarCiclos() {
		ciclos = 0;
	}
	
	public String elementoTablero(int posX, int posY) { //Busca en las listas si en la coordenada del tablero hay algún elemento
		
		String elemento = "";
		boolean encontradoS = false, encontradoP = false, encontradoZ = false;
		encontradoS = listaGirasoles.coincide(posX, posY);
		encontradoP = listaLanzaguisantes.coincide(posX, posY);
		encontradoZ = listaZombies.coincide(posX, posY);
		
		if (encontradoS) 
			elemento = listaGirasoles.detectarElemento(posX, posY);
		else if (encontradoP)
			elemento = listaLanzaguisantes.detectarElemento(posX, posY);
		else if (encontradoZ)
			elemento = listaZombies.detectarElemento(posX, posY);
		
		return elemento;
	}
	
	public void inicializarJuego() { //Cada vez que se inicia un nuevo juego se crean los arrays para la gestion de plantas y zombies.
		listaGirasoles = new SunflowerList();
		listaZombies = new ZombieList();
		listaLanzaguisantes = new PeashooterList();
		managerZombies = new ZombieManager(level, rand);
		managerSoles = new SuncoinManager();
	}
	
	public void abrirAyuda() { //cuando op = help
		System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
		System.out.println("List: Prints the list of available plants.");
		System.out.println("Reset: Starts a new game.");
		System.out.println("Help: Prints this help message.");
		System.out.println("Exit: Terminates the program.");
		System.out.println("[none]: Skips cycle.");
	}
	
	public void abrirLista() { //cuando op = list
		System.out.println("[S]unflower: Cost: 20 suncoins Harm: 0");
		System.out.println("[P]eashooter: Cost: 50 suncoins Harm: 1");
	}
	
	public void SolesAumentar(int soles){
		managerSoles.setSoles(soles); //suma al número de soles los que han generado los girasoles en ese ciclo
	}
	
    public boolean update() {
		
    	boolean exit = false;
		
		//acumular sol girasoles
		listaGirasoles.updateListaG();
		
		//funcion disparar plantas azombies
		listaLanzaguisantes.updateP();
		
		//funcion avanzar zombie => zombie llega final => game over
		zombieWins = listaZombies.updateZ();
		playerWins = (managerZombies.getNumZombiesQuedanSalir() == 0 && listaZombies.getContadorZombie() == 0);
		if (zombieWins || playerWins) 
			exit = true;
		else  //sumar si no se ha acabado la partida => ni win ni game over		
			this.ciclos++;
		
		return exit;
	}
    
	
    public boolean casillaLibre(int posXZ, int posYZ) { //Compara las coordenadas de los elementos de todas las listas con las coordenadas de una nueva planta (comando add)
	   boolean coincideG , coincideP, coincideZ;
	   coincideG = listaGirasoles.coincide(posXZ,posYZ);
	   coincideP = listaLanzaguisantes.coincide(posXZ,posYZ);  
	   coincideZ = listaZombies.coincide(posXZ,posYZ);
	   return (!coincideG && !coincideP && !coincideZ);   
     }
   
    
    public void dispararZombies(int posXL, int posYL) { //Recorre la lista de los zombies para ver si hay algún zombie delante de un peashooter para quitarle vida al zombie
	   listaZombies.quitarVidaZ(posXL, posYL);
    }
	
  
     boolean elegirOpcion(String[] entrada) { //función del menu de opciones
		boolean opcionCorrecta = false, casillaDentro = false, dineroSuficiente = false, libre = false, comandoCorrecto = false;
		//boolean okPlanta = false;
			if (entrada[0].equals("a") || entrada[0].equals("add")) {
				casillaDentro = comprobarDentro(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3])); //Se comprueba que la casilla esta vacia
				dineroSuficiente = compararPrecio(entrada[1]); 	//Se comprueba que tienes dinero suficiente
				libre = casillaLibre(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3])); //Se comprueba que la casilla esta libre
				comandoCorrecto = entrada[1].equals("s") || entrada[1].equals("sunflower") || entrada[1].equals("p") || entrada[1].equals("peashooter"); //Se comprueba que has metido añadir una planta
				opcionCorrecta = (casillaDentro && dineroSuficiente && libre && comandoCorrecto); //Se comprueba que todo esta correcto, condicion necesaria para plantar
				
				if (opcionCorrecta && (entrada[1].equals("s") || entrada[1].equals("sunflower"))) {
						managerSoles.restarSoles(Sunflower.COSTE);
						generarGirasol(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3]));
				}
				
				else if (opcionCorrecta && entrada[1].equals("p") || entrada[1].equals("peashooter")){
						generarLanzaguisantes(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3]));
						managerSoles.restarSoles(Peashooter.COSTE);	
				}

				else {
					if (!casillaDentro)
						System.out.println("Out of the board");
					else if (!dineroSuficiente)
						System.out.println("Not enough suncoins");
					else if (!libre)
						System.out.println("Occupied cell");
					else
						System.out.println("Bad command");
					System.out.println("");
				}
			}
			
			else if (entrada[0].equals("l") || entrada[0].equals("list")) {
				abrirLista();
				opcionCorrecta = false;
			}
				
			else if (entrada[0].equals("h") || entrada[0].equals("help")) {
				abrirAyuda();
				opcionCorrecta = false;
			}
				
			else{
				if (entrada[0] != null && !entrada[0].isEmpty())
					System.out.println("Unknown command");
				else 
					opcionCorrecta = true;
			}
		return opcionCorrecta;
	}	
     
	
	public void generarGirasol(int posX, int posY) {
		Sunflower girasol = new Sunflower(posX, posY, this);
		listaGirasoles.crearGirasol(girasol);
	}
	
	public void generarZombie(int posX, int posY) {
		Zombie zombie = new Zombie(posX, posY, this);
		listaZombies.crearZombie(zombie);
		managerZombies.setNumZombiesQuedanSalir(managerZombies.getNumZombiesQuedanSalir() - 1);
	}
	
	public void generarLanzaguisantes(int posX, int posY) {
		Peashooter lanzaguisantes = new Peashooter(posX, posY, this);
		listaLanzaguisantes.crearLanzaguisantes(lanzaguisantes);
	}

	public boolean comprobarDentro(int posX, int posY) { //comprueba que las coordenadas introducidas por el usuario al añadir una planta estén dentro del tablero	
		return (posX >= 0 && posX < 4 && posY >= 0 && posY < 8);
	}

	public boolean compararPrecio(String planta) { //Comprueba si el jugador tiene soles suficientes para comprar una planta
		boolean comprable = true;
		if (planta.equals("s") || planta.equals("sunflower")) 
			comprable = Sunflower.COSTE <= managerSoles.getNumeroSoles();	
		else if (planta.equals("p") || planta.equals("peashooter")) 
			comprable = Peashooter.COSTE <= managerSoles.getNumeroSoles();
		return comprable;
	}
	
	public void quitarVida(int posXZ, int posYZ) { //Compara las coordenadas de los elementos de todas las listas con las del zombie (casilla siguiente libre)
		boolean coincideG, coincideP;
		coincideG = listaGirasoles.coincide(posXZ,posYZ-1);
		coincideP = listaLanzaguisantes.coincide(posXZ,posYZ-1);
		if (coincideG)
			listaGirasoles.buscarGirasol(posXZ, posYZ - 1);
		else if (coincideP)
			listaLanzaguisantes.buscarPeashooter(posXZ, posYZ - 1);
	}
	
	public void computerAction() {
		int casillaZombie = 0;
		boolean libre, crearZombie = managerZombies.isZombieAdded();
		if (crearZombie) {
			casillaZombie = rand.nextInt(4);
			libre = casillaLibre(casillaZombie, MAX_COLUMNAS - 1);
			if (libre)
				generarZombie(casillaZombie, MAX_COLUMNAS - 1);
		}
	}
	
	public void draw() { //Crea e imprime por pantalla el tablero
		GamePrinter tablero = new GamePrinter(this, MAX_FILAS, MAX_COLUMNAS);
		System.out.println("Number of cycles: " + ciclos);
		System.out.println("Sun coins: " + managerSoles.getNumeroSoles());
		System.out.println("Remaining zombies: " + managerZombies.getNumZombiesQuedanSalir());
		System.out.println(tablero.toString());
		if (zombieWins) {
			System.out.println("Game over");
			System.out.println("Zombies win!");
		}
		else if (playerWins) {
			System.out.println("Game over");
			System.out.println("Player wins!");
		}
	}
}
