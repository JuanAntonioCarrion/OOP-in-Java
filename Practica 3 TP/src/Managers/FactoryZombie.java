package Managers;

import Objects.Zombie;
import Objects.ZombieCaracubo;
import Objects.ZombieComun;
import Objects.ZombieDeportista;


import java.util.Random;

import Exceptions.CommandExecuteException;

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
	
	public static String getZombieList() {
		String lista = "";
		for (Zombie z: ZombiesDisponibles) {
			lista += "[Z]ombie " + z.getNombre() + ": speed: " + z.getCiclo() + " Harm: " + z.getDamage() + " life: " + z.getResistencia() + "\n";
		}
		return lista;
	}
	
	public static Zombie getZombie (String zombieName) throws CommandExecuteException {
		
		Zombie zombie = null;
		Zombie copia = null;
		
		for (Zombie z: ZombiesDisponibles) {
			if (zombie == null) 
				zombie = (Zombie)z.parse(zombieName);
		}
		
		if (zombie == null) {
			throw new CommandExecuteException("Unknown zombie name: " + zombieName + "\n");
		}
		
		if (zombie != null)
		   copia = (Zombie)zombie.clone();

		return copia;
	}

}
