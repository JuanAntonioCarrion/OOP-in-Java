package Command;

import Logic.Controller;
import Logic.Game;

public class ResetCommand extends NoParamsCommand{
	
	public final static String commandLetter = "r";
    public final static String commandText = "reset";
	public final static String commandInfo = "[R]eset";
	public final static String helpInfo = "Starts a new game";
	
	public ResetCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);
	}
	
	public void execute(Game game, Controller controller) {
		game.inicializarJuego();
		controller.setPrintGameState();
	}
}
