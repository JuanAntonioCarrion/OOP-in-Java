package Command;

import Logic.Controller;
import Logic.Game;

public class HelpCommand extends NoParamsCommand {
	public final static String commandLetter = "h";
	public final static String commandText = "help";
	public final static String commandInfo = "[H]elp";
	public final static String helpInfo = "Print this help message";
	
	public HelpCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);	
	}
	
	public void execute(Game game, Controller controller) {
		String texto = CommandParser.commandHelp();
		System.out.println(texto);
		controller.setNoPrintGameState();
	}
}
