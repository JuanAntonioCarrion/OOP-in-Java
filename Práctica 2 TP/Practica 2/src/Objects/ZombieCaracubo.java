package Objects;

public class ZombieCaracubo extends Zombie{
	private static final int RESISTENCIA = 8;
	private static final int DANO = 1;
	private static final int CICLOS = 4;
	private static final String NOMBRE = "ZombieCaracubo";
	private static final String NOMBRE_ACORTADO = "W";
	
	public ZombieCaracubo() {
		super(RESISTENCIA, DANO, CICLOS, NOMBRE, NOMBRE_ACORTADO);
	}
	
}
