package Command;

import Logic.Controller;
import Logic.Game;

public abstract class Command {
	private String helpText;
	private String helpInfo;
	protected final String commandName;
	public Command(String commandText, String commandTextMsg, String helpTextMsg){
		commandName = commandText;
		helpInfo = helpTextMsg;
		helpText = commandTextMsg;
		
	}

	public abstract Command parse(String[] commandWords);
	
	public abstract void execute(Game game, Controller controller);
	
	public String helpText(){return " " + helpText + ": " + helpInfo;}
}