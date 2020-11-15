package Objects;

public class PetaCereza extends Plant{
	public static final int COSTE = 50;
	public static final int CICLOS = 2;
	public static final int DANO = 10;
	public static final int RESISTENCIA = 2;
	
	public static final String NOMBRE = "Petacereza";
    public static final String NOMBRE_ACORTADO = "c";
    public static final String NOMBRE_PRINT = "Peta[c]ereza";
    
    public PetaCereza() {
    	super(COSTE, CICLOS, DANO, RESISTENCIA, NOMBRE, NOMBRE_ACORTADO, NOMBRE_PRINT);
    }
    
    public void update() {
    	
		if (ciclosRestantes == 1) { //Si ciclos == 2 => explota
	
			for(int i = posX-1; i <= posX+1; i++) {
				
				for(int j = posY-1; j <= posY+1; j++) {
					
					game.dispararCasilla(i, j, dano);
				}
			}
			resistencia = 0;
		}
		else 
			ciclosRestantes--;
    }
}
