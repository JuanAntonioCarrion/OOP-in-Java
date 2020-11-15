package Objects;

public class Peashooter extends Plant{
	public static final int COSTE = 50;
	public static final int CICLOS = 0;
	public static final int DANO = 1;
	public static final int RESISTENCIA = 3;
	public static final String NOMBRE = "Peashooter";
    public static final String NOMBRE_ACORTADO = "p";
    public static final String NOMBRE_PRINT = "[P]eashooter";
    
    public Peashooter(){
    	super(COSTE, CICLOS, DANO, RESISTENCIA, NOMBRE, NOMBRE_ACORTADO,NOMBRE_PRINT);
    }
    
    public void update() {
    	
    	game.dispararZombies(this.posX,this.posY, dano);
    }   
}
