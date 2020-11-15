package Command;

import Exceptions.CommandParseException;


public class CommandParser{

	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand(),
		new ListCommand(),
		new PrintCommand(),
		new ZombieListCommand(),
		new SaveCommand(),
		new LoadCommand(),
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException{
		Command command = null;
		
		for (Command c : availableCommands) {
			if (command == null)
				command = c.parse(commandWords);
		}
		return command;
	}
	
	public static String commandHelp() {
		String textoAyuda = "The available commands are: \n";
		for (Command c : availableCommands) 
			 textoAyuda = textoAyuda + c.helpText() + "\n";
		
		return textoAyuda;
	}
}