package tp.pr1.logic;

import java.util.Random;
import tp.pr1.logic.Level;

public class PlantsVsZombies {
	public static void main(String[]args){
		
		Level level;
		Random rand;
		long seed;
		Game game;
		Controller controller;
		
		if ((args.length < 1) || (args.length > 2)) //Si no hay argumentos, o hay mas de dos, da error y no se puede jugar
			System.out.println("ERROR, no hay suficientes argumentos.");
		
		else{ //Si hay almenos un argumento
			
			if (args[0].toLowerCase().equals("easy")) {
			  
				level = Level.EASY; //La estructura es objeto = clase.tipo
		     	level.getNumeroZombies();
		     	level.getFrecuenciaZombies();
			}
			
			else if(args[0].toLowerCase().equals("hard")) {

				level = Level.HARD;
			    level.getNumeroZombies();
			    level.getFrecuenciaZombies();
			}
			    
			else {
			    
				level = Level.INSANE;
			    level.getNumeroZombies();
			    level.getFrecuenciaZombies();
			}
			
		    if (args.length == 2) //Si hay dos argumentos, usa el random proporcionado
		    	seed = Long.parseLong(args[1]);
		 
		    else //Si solo tiene un argumento, crea un random
			    seed = new Random().nextInt(1000);
		    
		System.out.println("Random seed used: " + seed);
		rand = new Random(seed);
		game = new Game(level, rand);
		controller = new Controller(game);
		controller.run();
		}
	}
}
