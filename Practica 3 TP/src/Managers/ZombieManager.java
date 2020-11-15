package Managers;
import java.util.Random;
import Objects.Zombie;
import Logic.Level;
import Logic.Game;


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
	
	public int getNumZombiesQuedanSalir() { 							//devuelve el número de zombies que quedan por salir en el tablero
		return numZombiesQuedanSalir;
	}
	
	public boolean isZombieAdded() { 
		boolean zombie = false;
		double aleatorio = rand.nextDouble();
		
		if (aleatorio <= frecZombies && numZombiesQuedanSalir > 0)
			zombie = true;												//devuelve que se tiene que crear un zombie si el aleatorio esta en el intervalo de la frecuencia y 0 => ahora tiene que comprobar que la casilla esté vacía
		return zombie;
	}
	
	public Zombie generarZombie(int posX, int posY, Game game) {
		Zombie zombie = null;
		if (isZombieAdded()) {
			zombie = FactoryZombie.getZombie();
			setNumZombiesQuedanSalir(getNumZombiesQuedanSalir() - 1);
			zombie.setAtributos(posX, posY, game);
		}	
		return zombie;
	}
}
