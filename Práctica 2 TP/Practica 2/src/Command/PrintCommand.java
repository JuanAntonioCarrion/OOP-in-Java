package Command;

import Logic.Controller;
import Logic.Game;

public class PrintCommand extends NoParamsCommand {
	public final static String commandLetter = "p";
	public final static String commandText = "printmode";
	public final static String commandInfo = "[P]rintMode";
	public final static String helpInfo = "change print mode [Release|Debug].";
	
	public PrintCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);	
	}
	
	public void execute(Game game, Controller controller) {
		controller.cambiarTablero();
		controller.setNoPrintGameState();
		controller.printGame();
	}
}

