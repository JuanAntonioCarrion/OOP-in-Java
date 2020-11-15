package Logic;

import java.util.Random;

import Exceptions.CommandExecuteException;
import Objects.ListaGameObject;
import Managers.PrinterManager;
import Managers.SuncoinManager;
import Managers.ZombieManager;
import Logic.Level;
import Objects.Plant;
import Objects.Zombie;
import Printer.BoardPrinter;
import Printer.GamePrinter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import Exceptions.FileContentsException;


public class Game {
	public static final int MAX_COLUMNAS = 8;
	public static final int MAX_FILAS = 4;
	private ListaGameObject listaZombies;
	private ListaGameObject listaPlantas;
	private ListaGameObject listaZombiesCopia;
	private ListaGameObject listaPlantasCopia;
	private SuncoinManager managerSoles;
	private ZombieManager managerZombies;
	private Level level;
	private int ciclos;
	private Random rand;
	private boolean zombieWins;
	private boolean playerWins;
	private boolean exit;
	private GamePrinter gp;
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	public final static String HEADER = "Plants Vs Zombies v3.0";
	
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
	
	public void salir() {
		exit = true;
		System.out.println();
		System.out.println("****** Game over!: User exit ******\r\n");
	}
	
	public void inicializarJuego() { //Cada vez que se inicia un nuevo juego se crean los arrays para la gestion de plantas y zombies.
		listaPlantas = new ListaGameObject("Plantas");
		listaZombies = new ListaGameObject("Zombies");
		managerZombies = new ZombieManager(level, rand);
		managerSoles = new SuncoinManager();
		ciclos = 0;
	}
	
	public void SolesAumentar(int soles){
		managerSoles.aumSoles(soles); //suma al número de soles los que han generado los girasoles en ese ciclo
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
		
		if (exit) {
			acabado = true;
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
    
    public void casillaOcupadaCopia(int posX, int posY) throws CommandExecuteException{
    	if (listaPlantasCopia.coincide(posX, posY) || listaZombiesCopia.coincide(posX, posY))
    		throw new CommandExecuteException("Two objects were in the same position \n");
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

	public void comprobarDentro(int posX, int posY, String nombrePlanta) throws CommandExecuteException{ //comprueba que las coordenadas introducidas por el usuario al añadir una planta estén dentro del tablero	
		if (posX < 0 || posX >= 4 || posY < 0 || posY >= 7)
			throw new CommandExecuteException("Failed to add " + nombrePlanta + ": (" + posX +", " + posY +") is an invalid position\n" );
	}

	public void compararPrecio(int coste, String nombrePlanta) throws CommandExecuteException{ //Comprueba si el jugador tiene soles suficientes para comprar una planta
		if(coste > managerSoles.getNumeroSoles())	
			throw new CommandExecuteException("Failed to add " + nombrePlanta + ": Not enough suncoins to buy it\n");

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
	
	public void printGame() {
		imprimirDatos();
		gp.printGame(this);
	}
	
	public void fijarRelease() {
		gp = PrinterManager.parsePrinter("release");
	}
	
	public void modificarInterfaz(BoardPrinter tablero) {
		gp = tablero;
	}
	
	public void insertarAlFichero(BufferedWriter writer) throws CommandExecuteException  {
		
		try {
			writer.write(HEADER);
			writer.newLine();
			writer.newLine();
			writer.write("cycle: " + ciclos);
			writer.newLine();
			writer.write("sunCoins: " + managerSoles.getNumeroSoles());
			writer.newLine();
			writer.write("level: " + level);
			writer.newLine();
			writer.write("remZombies: " + managerZombies.getNumZombiesQuedanSalir());
			writer.newLine();
			if (listaPlantas.getContador() > 0)
				writer.write("plantList: " + listaPlantas.detectarElementoFichero(writer).substring(0, (listaPlantas.detectarElementoFichero(writer).length())-2));
			else
				writer.write("plantList: ");
			writer.newLine();
			if (listaZombies.getContador() > 0)
				writer.write("zombieList: " + listaZombies.detectarElementoFichero(writer).substring(0, (listaZombies.detectarElementoFichero(writer).length())-2));
			else
				writer.write("zombieList: ");
		}
		catch(IOException ex) {
			System.err.println("There was an error in the save process");
		}
	}

	public void load(BufferedReader reader) throws CommandExecuteException  {
		
		String[] ciclosCopia;
		String[] suncoinsCopia;
		String[] numZombiesSalirCopia;
		String[] levelCopia;
		String[] listaPlantasCopia;
		String[] listaZombiesCopia;
		
		try {
			
			ciclosCopia = loadLine(reader, "cycle", false);
			suncoinsCopia = loadLine(reader, "sunCoins", false);
			levelCopia = loadLine(reader, "level", false);	
			numZombiesSalirCopia = loadLine(reader, "remZombies", false);
			listaPlantasCopia = loadLine(reader, "plantList", true);		
			listaZombiesCopia = loadLine(reader, "zombieList", true);
			hacerCopia(ciclosCopia, suncoinsCopia, numZombiesSalirCopia, levelCopia, listaPlantasCopia, listaZombiesCopia);
		}
		
		catch(IOException ex) {
			throw new CommandExecuteException(ex.getMessage());
		}
	}
	
	public void hacerCopia(String[] ciclosCopia, String[] suncoinsCopia, String[] numZombiesSalirCopia, String[] levelCopia, String[] listaPlantasCopia, String[] listaZombiesCopia ) throws CommandExecuteException{
		int copiaCiclos;
		int copiaSuncoins;
		int copiaNumZombiesSalir;
		Level copiaLevel;
		this.listaPlantasCopia = new ListaGameObject("Plantas");
		this.listaZombiesCopia = new ListaGameObject("Zombies");
		try {
			copiaCiclos = Integer.parseInt(ciclosCopia[0]);
		}
		catch(NumberFormatException ex) {
			throw new CommandExecuteException("Error, number of cycles was expected\n");
		}
		
		if (copiaCiclos < 0) {
			throw new CommandExecuteException("Error, number of cycles was expected to be positive\n");
		}
		
		try {
			copiaSuncoins = Integer.parseInt(suncoinsCopia[0]);
		}
		catch(NumberFormatException ex) {
			throw new CommandExecuteException("Error, number of suncoins was expected\n");
		}
		if (copiaSuncoins < 0) {
			throw new CommandExecuteException("Error, number of suncoins was expected to be positive\n");
		}
		
		try {
			copiaNumZombiesSalir = Integer.parseInt(numZombiesSalirCopia[0]);
		}
		catch(NumberFormatException ex) {
			throw new CommandExecuteException("Error, number of zombies remaining was expected\n");
		}
		
		copiaLevel = Level.parse(levelCopia[0]);
		if (copiaLevel == null)
			throw new CommandExecuteException("Error, expected a valid Level name (EASY, HARD, INSANE)\n");
			this.listaPlantasCopia.parsearLista(listaPlantasCopia, this);
			this.listaZombiesCopia.parsearLista(listaZombiesCopia, this);
			if (this.listaZombiesCopia.getContador() + copiaNumZombiesSalir > copiaLevel.getNumeroZombies()) {
				throw new CommandExecuteException("Error, too much zombies for this level\n");
		}
		
		ciclos = copiaCiclos;
		managerSoles.setSoles(copiaSuncoins);
		level = copiaLevel;
		managerZombies.setNumZombiesQuedanSalir(copiaNumZombiesSalir);
		listaPlantas = this.listaPlantasCopia;
		listaZombies = this.listaZombiesCopia;
	}
	
	public void encontradoEnCopia(int posX, int posY) throws CommandExecuteException{
		casillaOcupadaCopia(posX, posY);
	}
	
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException{
		String line = inStream.readLine().trim(); 
		// absence of the prefix is invalid 
		if ( !line.startsWith(prefix + ":") ) 
			throw new FileContentsException(wrongPrefixMsg + prefix + "\n");
		// cut the prefix and the following colon off the line
		// then trim it to get the attribute contents
		String contentString = line.substring(prefix.length()+1).trim();
		String[] words;
		// the attribute contents are not empty
		if (!contentString.equals("")) {
			if (!isList ) { 
				// split non-list attribute contents into words 
				// using 1-or-more-white-spaces as separator
				words = contentString.split("\\s+");
				// a non-list attribute with contents of more than one word is invalid 
				if (words.length != 1) 
					throw new FileContentsException(lineTooLongMsg + prefix + "\n");
				} 
			else 
				// split list attribute contents into words 
				// using comma+0-or-more-white-spaces as separator 
				words = contentString.split(",\\s*"); 
			// the attribute contents are empty 
			}
		else { 
			// a non-list attribute with empty contents is invalid 
			if (!isList) 
				throw new FileContentsException(lineTooShortMsg + prefix + "\n");
			// a list attibute with empty contents is valid; 
			// use a zero-length array to store its words 
			words = new String[0]; 
			} 
		return words;
	}
	
	public void anadePlanta(Plant planta, int posX, int posY) throws CommandExecuteException{
		
		if (planta != null) {
			
			comprobarDentro(posX, posY, planta.getNombre());	
			compararPrecio(planta.getCoste(), planta.getNombre());
			casillaOcupadaAnadir(posX, posY, planta); 
			planta.setAtributos(posX, posY, this);
			anadirPlanta(planta, posX, posY);
			gastarDinero(planta.getCoste());
		}
		
		else
			throw new CommandExecuteException("Invalid object \n");
	}
	
	public void casillaOcupadaAnadir(int posX, int posY, Plant planta) throws CommandExecuteException{
		 //Compara las coordenadas de los elementos de todas las listas con las coordenadas de una nueva planta (comando add)
		if (listaPlantas.coincide(posX, posY) || listaZombies.coincide(posX, posY)){
			throw new CommandExecuteException("Failed to add " + planta.getNombre() + ": position (" + posX +", " + posY +") is already occupied\n");
		}
		     
	}
}



