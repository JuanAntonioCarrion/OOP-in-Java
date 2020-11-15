package Command;

import Logic.Game;

public class ResetCommand extends NoParamsCommand{
	
	public final static String commandLetter = "r";
    public final static String commandText = "Reset";
	public final static String commandInfo = "[R]eset";
	public final static String helpInfo = "Starts a new game";
	
	public ResetCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		game.inicializarJuego();
		game.printGame();
		return false;
	}
}
