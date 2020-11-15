package tp.pr1.objects;
import tp.pr1.logic.Game;

public class Zombie { //El daño es siempre uno
	private int resistencia;
	private int posX;
	private int posY;
	private Game game;
	private int velocidad; //el zombie avanza una casilla si esta a 2
	
	public Zombie(int posX, int posY, Game game) { //crear un zombie
		resistencia = 5;
		velocidad = 1; 
		this.posX = posX;
		this.posY = posY; 
		this.game = game;
	}
	
	public int getX() { //Devuelve la posición x del zombie
		return posX;
	}
	
	public int getY() { //Devuelve la posición y del zombie
		return posY;
	}
	
	public boolean quitarVidaZombie() { //si tiene delante un peashooter, le quita resistencia
	
		boolean muerto = false;
		
		resistencia--;
		
		if (resistencia == 0) //si la resistencia del zombie ha llegado a 0 => desplaza posiciones del array
			muerto = true;
		
		return muerto;
	}
	
	public boolean poderAvanzarCasilla() {
		
		boolean avanzarCiclo = false;
		
		if (velocidad == 2) { //si la velocidad del zombie == 2 => avanza una casilla
			avanzarCiclo = true;
			velocidad = 1;
		}
	
		else 
			velocidad++;
		
		return avanzarCiclo;
	}
	
	public int getResistenciaZombie() { //Devuelve la resistencia del zombie
		
		return this.resistencia;
	}
	
	public boolean coincide(int posXL, int posYL){ //compara las coordenadas del zombie con las del peashooter para saber si hay un zombie delante y quitarle vida
		
		boolean coincide = false;

		if(this.posX == posXL && this.posY>posYL) {
			
			quitarVidaZombie();
			coincide=true;
		}
		return coincide;
	}
	
	public boolean coincideZ(int posXZ, int posYZSiguiente) { //Compara las coordenadas del zombie con => las del zombie (casilla siguiente libre)
		return posXZ == posX && posYZSiguiente == posY;
	}
	
	public boolean updateZombie(){ //upate zombie
		boolean haLlegado = false;
		if (game.casillaLibre(posX, posY - 1) && poderAvanzarCasilla()) { //comprueba que la casilla siguiente este vacía para poder avanzar(siempre y cuando haya cumplido dos ciclos, por supuesto)
			posY -= 1; //Si la casilla está vacia, el zombie avanza
			haLlegado = posY == 0;
		}	
		else 	//si el zombie no puede avanzar porque la casilla delante está ocupada, comprueba que sea una planta para quitarle vida
			game.quitarVida(posX, posY); 
		return haLlegado;
	}
}
