package Logic;

public enum Level {
	EASY, HARD, INSANE;
	
	public int getNumeroZombies(){ //compara la dificultad del juego para seleccionar el numero de zombies
		
		int numZombies = 0;
		
		if (this == EASY)
			numZombies = 3;
		
		else if (this == HARD)
			numZombies = 5;
		
		else if (this == INSANE)
			numZombies = 10;
		
		return numZombies;
	}
	
	public double getFrecuenciaZombies(){ //compara la dificultad del juego para seleccionar la frecuancia con la que salen los zombies
		
		double frecuencia = 0;
		
		if (this == EASY)
			frecuencia = 0.1;
		
		else if (this == HARD)
			frecuencia = 0.2;
		
		else if (this == INSANE)
			frecuencia = 0.3;
		
		return frecuencia;
	}
}