package Logic;

import java.util.Scanner;
import Command.CommandParser;
import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Command.Command;

public class Controller {
	
	private Game game;
	private Scanner in; 
	private String prompt = "Command > ";
	private String unknownCommandMsg = "Unknown command. Use ’help’ to see the available commands";
	
	public Controller(Game game){
		this.game = game;
		
		in = new Scanner(System.in);
	}

	public void run(){
		game.inicializarJuego();
		game.printGame();
		while(!game.isFinished()) {
			//retrasarMilesimasDeSegundo();
			System.out.print(prompt);
			String[] words = in.nextLine().toLowerCase().trim().split ("\\s+"); 
			try {
				Command command = CommandParser.parseCommand(words); 
				if (command != null) { 
					if (command.execute(game)) {
						game.update();
						game.printGame(); 
					}	
				} 
				else 
					System.out.println(unknownCommandMsg);
				}
			catch (CommandParseException | CommandExecuteException ex) {
				System.err.format(ex.getMessage(), "%n%n");
			}
		}		
	}
	
	/*public void retrasarMilesimasDeSegundo() {
		try {
			Thread.sleep(30);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}*/
}