package Logic;

import java.util.Random;

public class PlantsVsZombies {
	public static void main(String[]args) {
		
		Level level;
		Random rand;
		long seed;
		Game game;
		Controller controller;
		
		try {
			
			if ((args.length < 1) || (args.length > 2)) { //Si no hay argumentos, o hay mas de dos, da error y no se puede jugar
				throw new RuntimeException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
			}
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
				    
				else if(args[0].toLowerCase().equals("insane")){
				    
					level = Level.INSANE;
				    level.getNumeroZombies();
				    level.getFrecuenciaZombies();
				}
				
				else {
					throw new RuntimeException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
				}
				
					try {
						if (args.length == 2) {
							seed = Long.parseLong(args[1]);
						}
						
						else
							seed = new Random().nextInt(1000);
						
						System.out.println("Random seed used: " + seed);
						rand = new Random(seed);
						game = new Game(level, rand);
						game.fijarRelease();
						controller = new Controller(game);
						controller.run();
			        } 
					catch (NumberFormatException excep) {
			            System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number");
			        }
			    }				    	
		}
		catch(RuntimeException excepcion) {
			System.err.println(excepcion.getMessage());
		}
	}
}