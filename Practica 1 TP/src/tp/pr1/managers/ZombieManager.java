package tp.pr1.managers;
import java.util.Random;
import tp.pr1.logic.Level;


public class ZombieManager {

	private int numZombiesQuedanSalir;
	private double frecZombies;
	private Random rand;
	
	public ZombieManager(Level level, Random rand){
		numZombiesQuedanSalir = level.getNumeroZombies();
		frecZombies = level.getFrecuenciaZombies();
		this.rand = rand;
	}
	
	public void setNumZombiesQuedanSalir(int numZombiesQuedanSalir) { //Modifica el número de zombies que quedan por salir => se le resta 1 (desde game)
		this.numZombiesQuedanSalir = numZombiesQuedanSalir;
	}
	
	public int getNumZombiesQuedanSalir() { //devuelve el número de zombies que quedan por salir en el tablero
		return numZombiesQuedanSalir;
	}
	
	public boolean isZombieAdded() { 
		boolean zombie = false;
		double aleatorio = rand.nextDouble();
		zombie = (aleatorio <= frecZombies && numZombiesQuedanSalir > 0); //devuelve que se tiene que crear un zombie si el aleatorio esta en el intervalo de la frecuencia y 0 => ahora tiene que comprobar que la casilla esté vacía
		return zombie;
	}
}
