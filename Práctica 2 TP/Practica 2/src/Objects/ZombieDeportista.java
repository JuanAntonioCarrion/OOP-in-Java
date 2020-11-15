package Objects;

public class ZombieDeportista extends Zombie{
	private static final int RESISTENCIA = 2;
	private static final int DANO = 1;
	private static final int CICLOS = 1;
	private static final String NOMBRE = "ZombieDeportista";
	private static final String NOMBRE_ACORTADO = "X";
	
	public ZombieDeportista() {
		super(RESISTENCIA, DANO, CICLOS, NOMBRE, NOMBRE_ACORTADO);
	}
	
}
