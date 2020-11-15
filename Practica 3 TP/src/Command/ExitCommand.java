package Command;

import Logic.Game;

public class ExitCommand extends NoParamsCommand{
	
	public final static String commandLetter = "e";
	public final static String commandText = "exit";
	public final static String commandInfo = "[E]xit";
	public final static String helpInfo = "Terminates the program";
	
	public ExitCommand()  {
		super(commandLetter, commandText, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		game.salir();
		return false;
	}
}