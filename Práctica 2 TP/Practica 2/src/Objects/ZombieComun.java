package Objects;

public class ZombieComun extends Zombie {
	private static final int RESISTENCIA = 5;
	private static final int DANO = 1;
	private static final int CICLOS = 2;
	private static final String NOMBRE = "ZombieComun";
	private static final String NOMBRE_ACORTADO = "Z";
	
	public ZombieComun() {
		super(RESISTENCIA, DANO, CICLOS, NOMBRE, NOMBRE_ACORTADO);
	}
}
