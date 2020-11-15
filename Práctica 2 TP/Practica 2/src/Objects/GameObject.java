package Objects;
import Logic.Game;;

public abstract class GameObject implements Cloneable {
	protected int resistencia, dano, ciclosRestantes, posX, posY;
	protected String nombre, nombreAcort;
	protected Game game;
	
	public GameObject(int resistencia, int dano, int ciclos, String nombre, String nombreAcort) {
		this.resistencia = resistencia;
		this.dano = dano;
		ciclosRestantes = ciclos;
		this.nombre = nombre;
		this.nombreAcort = nombreAcort;
	}
	
	public void setAtributos(int x, int y, Game game) {
		posX = x;
		posY = y;
		this.game = game;
	}
	
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	

	
	public String getNombreAcort() {
		return nombreAcort;
	}
	
	
	public int getDamage() {
		return dano;
	}
	
	public int getResistencia() {
		return resistencia;
	}
	
	public int getCiclo() {
		return ciclosRestantes;
	}
	
	public boolean coincide(int posX, int posY) {
		return posX == this.posX && posY == this.posY;
	}
	
	public abstract void update();
	
	public boolean coincideElemento(int posX, int posY){
		
		return posX == this.posX && posY == this.posY;
	}
	
	public boolean quitarVidaElemento(int dano) { //?
		
		boolean muerto = false;
		
		resistencia -= dano;
		
		if (resistencia <= 0)
			muerto = true;
		
		return muerto;
	}
	
	public boolean buscarZombieEnFila(int posX, int posY) {
		boolean encontrado = false;
		while (!encontrado && posY < Game.MAX_COLUMNAS) {
			encontrado = (posX == this.posX && posY == this.posY);
			posY++;
		}
		return encontrado;
	}
	
	public String infoCompleta() {
		String info = nombreAcort.toUpperCase() + "[l:" + resistencia + ",x:" + posX + ",y:" + posY + ",t:" + resistencia + "]";
		return info;
	}
}
