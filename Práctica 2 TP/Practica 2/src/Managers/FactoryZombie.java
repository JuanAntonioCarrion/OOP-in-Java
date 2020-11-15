package Managers;

import Objects.Zombie;
import Objects.ZombieCaracubo;
import Objects.ZombieComun;
import Objects.ZombieDeportista;


import java.util.Random;

public class FactoryZombie {
	private static Zombie[] ZombiesDisponibles = {
		new ZombieCaracubo(),
		new ZombieDeportista(),
		new ZombieComun(),
	};
	
	public static Zombie getZombie() {
		int eleccionAleatoria = new Random().nextInt(ZombiesDisponibles.length);
		return (Zombie)ZombiesDisponibles[eleccionAleatoria].clone();
	}
	

}
