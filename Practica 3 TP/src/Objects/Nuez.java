package Objects;

public class Nuez extends Plant{
	public static final int COSTE = 50;
	protected static final int CICLOS = 0;
	protected static final int DANO = 0;
	protected static final int RESISTENCIA = 10;
	
	public static final String NOMBRE = "Nuez";
    public static final String NOMBRE_ACORTADO = "n";
    public static final String NOMBRE_PRINT = "[N]uez";
    
    public Nuez() {
    	super(COSTE, CICLOS, DANO, RESISTENCIA, NOMBRE, NOMBRE_ACORTADO, NOMBRE_PRINT);
    }
    
    public void update() {}//update vacío
}
