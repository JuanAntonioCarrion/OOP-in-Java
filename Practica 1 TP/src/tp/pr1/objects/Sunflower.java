package tp.pr1.objects;
import tp.pr1.logic.Game;

public class Sunflower {
	public final static int COSTE = 20;
	private int frecuencia;
	private int resistencia;
	private int posX;
	private int posY;
	private int ciclos; //genera soles si esta a 2
	private Game game;
	
	public Sunflower(int posX, int posY, Game game) { //crear un sunflower
		this.game = game;
		frecuencia = 10;
		resistencia = 1;
		this.posX = posX;
		this.posY = posY;
		ciclos = 0;
	}
	
	public int getX() { //Devuelve la posición x de la planta
		return posX;
	}
	
	public int getY() { //Devuelve la posición y de la planta
		return posY;
	}
	public void updateGirasol() { //update sunflower
		
		int soles = 0;
		if (ciclos == 2) { //Si ciclos == 2 => +10 de soles
			soles = frecuencia;
			ciclos = 1;
		}
		else 
			ciclos++;
	
		game.SolesAumentar(soles);
	}
	
	public int getResistenciaSunflower() { //Devuelve la resistencia de la planta
		return this.resistencia;
	}
	
	public boolean coincideG(int posXZ, int posYZSiguiente) { //Compara las coordenadas de la planta con => las coordenadas de una nueva planta (comando add) , o => con las del zombie (casilla siguiente libre)
		return posXZ == posX && posYZSiguiente == posY; 
	}
}
