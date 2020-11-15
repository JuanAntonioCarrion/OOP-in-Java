package Objects;

import java.io.BufferedWriter;

import Exceptions.CommandExecuteException;
import Logic.Game;
import Managers.FactoryPlant;
import Managers.FactoryZombie;

public class ListaGameObject {
	
	public static final int MAX = 28;
	private GameObject[] lista;
	private int contador;
	private String tipoLista;
	
	public ListaGameObject(String tipoLista) {
		
		lista = new GameObject[MAX];
		contador = 0;
		this.tipoLista = tipoLista;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void crear(GameObject objeto) {
		lista[contador] = objeto;
		contador++;
	}

	public String detectarElemento(int posX, int posY) {
		String elemento = "";
		int i = 0;
		boolean encontrado = false;
		
		while(!encontrado && i < contador) {
			encontrado = lista[i].coincide(posX, posY);
			if (!encontrado)
				i++;
		}
		
		if (encontrado) {
			elemento =  lista[i].getNombreAcort() + "[" + lista[i].getResistencia() + "]";
		}	
		
		return elemento;
	}
	
	public boolean coincide(int posX, int posY) { 
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < contador) {
			encontrado = lista[i].coincideElemento(posX, posY); 
			i++;
		}
		
		return encontrado;
	}
	
	public void buscarElementoPlanta(int posX, int posY, int dano) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contador) {
			encontrado = lista[i].coincideElemento(posX, posY);
			if (!encontrado)
				i++;
			else	
				quitarVidaPlanta(i, dano); //Si se ha encontrado una planta con esas coordenadas, llama a esta función para restar su resistencia y comprobar si hay que eliminarla de la lista o no
		}
	}
		
	public void buscarElementoZombie(int posX, int posY, int dano) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contador) {
			encontrado = lista[i].buscarZombieEnFila(posX, posY);
			if (!encontrado)
				i++;
			else
				quitarVidaZombie(i, dano);
		}	
	}
	
	public void dispararCasilla(int posX, int posY, int dano) { //solo para zombie
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contador) {
			
			encontrado = lista[i].coincide(posX, posY);
			
			if (!encontrado)
				i++;
			else
				quitarVidaZombie(i, dano);
		}	
	}
	
     public void quitarVidaPlanta(int posPlantaArray, int dano) {
		
		boolean resistenciaCero = false;
			
		resistenciaCero = lista[posPlantaArray].quitarVidaElemento(dano);
			
		if(resistenciaCero == true) { //si la vida de la planta está a cero, se elimina del array => desplazar array hacia la izquierda
				
			matar(posPlantaArray);

    	}
	}
     
     private void matar(int posArray) {
    	for (int i = posArray + 1; i < contador; i++) {
 			lista[i - 1] = lista[i];
 		}
    	contador--;
     }

     public void quitarVidaZombie(int posZombieArray, int dano) {
 		
			boolean resistenciaCero = false;
			
			resistenciaCero = lista[posZombieArray].quitarVidaElemento(dano);
			
			if(resistenciaCero == true) {
				
			 matarZombie(posZombieArray);
			
			contador--;
		}
	 }
     
     private void matarZombie(int posZombieArray) {
    	for (int i = posZombieArray + 1; i < contador; i++) {
				lista[i - 1] = lista[i];
		}
     }
     
    public void updateElemento(){
		
    	for (int i = 0; i < contador; i++) {
			lista[i].update();
		
			if (lista[i].resistencia == 0) {
				matar(i);
				i--;
			}
	    }	
    }
    
    public String devolverLista(int contadorTotal) {
		String elemento = "";
		
		for (int i = 0; i < contadorTotal; i++) {
			
			if (contador <= i)
				elemento = lista[i].infoCompleta();
			
			else
				elemento = lista[i].infoCompleta();
		}
		
		return elemento;
	}
    
    public int SumaTotalContador() { //Esta estaba duplicado el contadorPlantas y zombies
    	return contador;
    }
    
    public int detectarPosicion(int posX, int posY) {
    	int posicion = 0;
    	boolean encontrado = false;
    	
    	while (!encontrado && posicion < contador) {
    		encontrado = posX == lista[posicion].posX && lista[posicion].posY == posY;
    		if (!encontrado)
    			posicion++;
    	}
    	return posicion;
    }
    
    public boolean haLlegado() {
    	boolean llegado = false;
    	int i = 0;
    	while (!llegado && i < contador) {
    		llegado = lista[i].getY() == 0;
    		
    		i++;
    	}
    		
    	return llegado;
    }
    
    public String detectarElementoFichero(BufferedWriter writer){
    	
    	String elemento = "";
    	
    	for(int i = 0; i < contador; i++ ) {
    	elemento = elemento + lista[i].getNombreAcort().toUpperCase() + ":" + lista[i].getResistencia() + ":"+ lista[i].getX() +":"+ lista[i].getY() +":"+ lista[i].getCiclo() + ", ";
    	}
       	
    	return elemento;
    }
    
    public String detectarElementoDebug(int posX, int posY) {
		String elemento = "";
		int i = 0;
		boolean encontrado = false;
		
		while(!encontrado && i < contador) {
			encontrado = lista[i].coincide(posX, posY);
			if (!encontrado)
				i++;
		}
		
		if (encontrado) {
			elemento =  lista[i].getNombreAcort().toUpperCase() + ","+"[" + "l:" + lista[i].getResistencia() + ","+ "x:" + lista[i].getX() +","+"y:" + lista[i].getY() +","+ "t:" + lista[i].getCiclo() + "]";
		}

		return elemento;
	}
    
    public void parsearLista(String[] stringLista, Game game) throws CommandExecuteException {
    	for (int i = 0; i < stringLista.length; i++) {
    		checkeaObjeto(stringLista[i], game);
    	}
    }
    
    public void checkeaObjeto(String objeto, Game game) throws CommandExecuteException {
		String[] words = objeto.split(":\\s*");
		int lr;
		int x;
		int y;
		int t;
		GameObject copiaObjeto = null;
		if (words.length != 5) {
			throw new CommandExecuteException("Not enough attributes for object\n");
		}
		try {
			lr = Integer.parseInt(words[1]);
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[3]);
			t = Integer.parseInt(words[4]);
		}
		catch(NumberFormatException ex){
			throw new CommandExecuteException("One of the parameters is not a number\n");
		}
		
		game.encontradoEnCopia(x, y);
		
		if (tipoLista == "Zombies") {
			if (x >= Game.MAX_FILAS || y >= Game.MAX_COLUMNAS || x < 0 || y < 0)
				throw new CommandExecuteException("One of the objects is out of the board\n");
			copiaObjeto = FactoryZombie.getZombie(words[0]);
			
		}
		else {
			if (x >= Game.MAX_FILAS || y >= Game.MAX_COLUMNAS - 1 || x < 0 || y < 0)
				throw new CommandExecuteException("One of the objects is out of the board\n");
			copiaObjeto = FactoryPlant.getPlant(words[0]);
		}
		
		if (t > copiaObjeto.getCiclo() || t < 0) {
			throw new CommandExecuteException("One of the objects has an impossible number of cycles\n");
		}
		if (lr > copiaObjeto.getResistencia() || lr <= 0) {
			throw new CommandExecuteException("One of the objects has an impossible number of resistance\n");
		}
			
		
		copiaObjeto.setX(x);
		copiaObjeto.setY(y);
		copiaObjeto.setCiclos(t);
		copiaObjeto.setVida(lr);
		copiaObjeto.setGame(game);
		
		crear(copiaObjeto);
		
	}
   
}
