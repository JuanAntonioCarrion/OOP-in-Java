package Logic;

import java.util.Random;
import Objects.ListaGameObject;
import Managers.SuncoinManager;
import Managers.ZombieManager;
import Logic.Level;
import Objects.Plant;
import Objects.Zombie;

public class Game {
	public static final int MAX_COLUMNAS = 8;
	public static final int MAX_FILAS = 4;
	private ListaGameObject listaZombies;
	private ListaGameObject listaPlantas;
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
	
	public String elementoTablero(int posX, int posY) {
		String elemento = "";
		elemento = listaZombies.detectarElemento(posX, posY);
		if (elemento == "") {
			elemento = listaPlantas.detectarElemento(posX, posY);
		}
		
		return elemento;
	}
	
	public void inicializarJuego() { //Cada vez que se inicia un nuevo juego se crean los arrays para la gestion de plantas y zombies.
		listaPlantas = new ListaGameObject();
		listaZombies = new ListaGameObject();
		managerZombies = new ZombieManager(level, rand);
		managerSoles = new SuncoinManager();
		ciclos = 0;
	}
	
	public void SolesAumentar(int soles){
		managerSoles.setSoles(soles); //suma al número de soles los que han generado los girasoles en ese ciclo
	}
	
    public boolean isFinished() {
    	playerWins = false;
    	boolean acabado = false;
    	playerWins = (managerZombies.getNumZombiesQuedanSalir() == 0 && listaZombies.getContador() == 0);
    	
		if (zombieWins || playerWins) {
			acabado = true;	
			
			if (zombieWins)
				System.out.println("Zombies win");
			else
				System.out.println("Player wins");

		}
		
		return acabado;
	}
    
    public void update() {
		//funcion avanzar zombie => zombie llega final => game over
		listaPlantas.updateElemento();
		listaZombies.updateElemento();
		zombieWins = listaZombies.haLlegado();
		generarZombie();
		this.ciclos++;
			//sumar si no se ha acabado la partida => ni win ni game over				
	}
	
    public boolean casillaOcupada(int posX, int posY) { //Compara las coordenadas de los elementos de todas las listas con las coordenadas de una nueva planta (comando add)
	   return listaPlantas.coincide(posX, posY) || listaZombies.coincide(posX, posY);  
     }
   
    
    public void dispararZombies(int posX, int posY, int dano) { //Recorre la lista de los zombies para ver si hay algún zombie delante de un peashooter para quitarle vida al zombie
	   listaZombies.buscarElementoZombie(posX, posY, dano);
    }
    
    public void dispararCasilla(int posX, int posY, int dano) {
    	listaZombies.dispararCasilla(posX, posY, dano);
    }
    
    public void anadirPlanta(Plant planta, int posX, int posY) {
    	planta.setAtributos(posX, posY, this);
    	listaPlantas.crear(planta);
    }

	public boolean comprobarDentro(int posX, int posY) { //comprueba que las coordenadas introducidas por el usuario al añadir una planta estén dentro del tablero	
		return (posX >= 0 && posX < 4 && posY >= 0 && posY < 7);
	}

	public boolean compararPrecio(int coste) { //Comprueba si el jugador tiene soles suficientes para comprar una planta
		return coste <= managerSoles.getNumeroSoles();	
	}
	
	public void quitarVida(int posXZ, int posYZ, int dano) { //Compara las coordenadas de los elementos de todas las listas con las del zombie (casilla siguiente libre)
	
		listaPlantas.buscarElementoPlanta(posXZ, posYZ - 1, dano);
	}
	
	public void generarZombie() {
		int casillaZombie = 0;
		Zombie zombie = null;
		boolean ocupada;
		casillaZombie = rand.nextInt(4);
		ocupada = casillaOcupada(casillaZombie, MAX_COLUMNAS - 1);
		if (!ocupada) {
			zombie = managerZombies.generarZombie(casillaZombie, MAX_COLUMNAS - 1, this);
			if (zombie != null)
				listaZombies.crear(zombie);
		}
	}

	public void gastarDinero(int precio) {
		managerSoles.restarSoles(precio);
	}
	
	public void gananZombies() {
		zombieWins = true;
	}
	
	public int getContadorTotal() {
		return listaZombies.SumaTotalContador() + listaPlantas.SumaTotalContador();
	}
	
	public void imprimirDatos() {
		System.out.println("Number of cycles: "+ ciclos);
		System.out.println("Sun coins: " + managerSoles.getNumeroSoles());
		System.out.println("Remaining zombies: " + managerZombies.getNumZombiesQuedanSalir());
	}
	
	public String elementoTableroDebug(int posX, int posY) {
		String elemento = "";
		elemento = listaZombies.detectarElementoDebug(posX, posY);
		
		if(elemento == "") {
			
			elemento = listaPlantas.detectarElementoDebug(posX, posY);
		}
		return elemento;
	}
}