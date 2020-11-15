package Command;

import Logic.Game;

public class HelpCommand extends NoParamsCommand {
	public final static String commandLetter = "h";
	public final static String commandText = "Help";
	public final static String commandInfo = "[H]elp";
	public final static String helpInfo = "Print this help message";
	
	public HelpCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);	
	}
	
	public boolean execute(Game game) {
		String texto = CommandParser.commandHelp();
		System.out.println(texto);
		return false;
	}
}
