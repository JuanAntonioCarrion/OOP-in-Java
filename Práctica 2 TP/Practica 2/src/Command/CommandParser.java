package Command;

import Logic.Controller;


public class CommandParser{

	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand(),
		new ListCommand(),
		new PrintCommand(),
	};
	
	public static Command parseCommand(String[] commandWords) {
		Command command = null;
		
		if (commandWords.length == 0 || commandWords.length > 4) {
			command = null;
		}
		
		else {
			for (Command c : availableCommands) {
				if (command == null)
					command = c.parse(commandWords);
			}
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