package Objects;

public class Sunflower extends Plant {
	public static final int COSTE = 20;
	protected static final int CICLOS = 2;
	protected static final int FRECUENCIA = 10;
	protected static final int DANO = 0;
	protected static final int RESISTENCIA = 2;
	
	public static final String NOMBRE = "sunflower";
    public static final String NOMBRE_ACORTADO = "s";
    public static final String NOMBRE_PRINT = "[s]unflower";
    
    public Sunflower() {
    
    	super(COSTE, CICLOS, DANO, RESISTENCIA, NOMBRE, NOMBRE_ACORTADO, NOMBRE_PRINT);
    }
    
    public void update() {
    	
    	int soles = 0;
		if (ciclosRestantes == 1) { //Si ciclos == 2 => +10 de soles
			soles = FRECUENCIA;
			ciclosRestantes = CICLOS;
		}
		else 
			ciclosRestantes--;
	
		game.SolesAumentar(soles);
    }
}