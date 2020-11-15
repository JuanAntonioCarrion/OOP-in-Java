package tp.pr1.objects;
import tp.pr1.logic.Game;

public class Peashooter { //Al ser la cadencia y el daño solo un punto estimamos innecesario declararlos como atributos privados.
	
	public final static int COSTE = 50;
	private int resistencia;
	private int posX;
	private int posY;
	private Game game;
	
	public Peashooter(int posX, int posY, Game game){ //Crear un peashooter
	
		resistencia = 3;
		this.posX = posX;
		this.posY = posY;
		this.game = game;
	}
	
	public int getX() { //Devuelve la posición x de la planta
		return posX;
	}
	
	public int getY() { //Devuelve la posición y de la planta
		return posY;
	}
	
	public void updateLanz() { //update peashooter
		
		game.dispararZombies(this.posX,this.posY);
		
	}
	public boolean buscarLanzaguisantes(int posX, int posY) { //hace una búsqueda para saber si delante del zombie hay una planta (compara coordenadas planta con la siguiente casilla del zombie)
		
		boolean encontrado = false;
		
		if (this.posX == posX && this.posY == posY)
			encontrado = true;
		
		return encontrado;	
	}	
	
	public boolean quitarVidaLanzaguisantes() { //resta un punto de vida a la planta si un zombie le ha disparado
	
		boolean muerto = false;
		
		resistencia--;
		
		if (resistencia == 0) //Si no quedan puntos de vida, devuelve un booleano de que la planta ha muerto => desplazar posiciones array
			muerto = true;
		
		return muerto;
	}
	
	public int getResistenciaPeashooter() { //Devuelve la resistencia de la planta
		return this.resistencia;
	}
	
	public boolean coincideL(int posXZ, int posYZSiguiente) { //Compara las coordenadas de la planta con => las coordenadas de una nueva planta (comando add) , o => con las del zombie (casilla siguiente libre)
		return posXZ == posX && posYZSiguiente == posY;
	}
}
