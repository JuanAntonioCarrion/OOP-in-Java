package Command;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
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

	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	public abstract boolean execute(Game game)throws CommandExecuteException;
	
	public String helpText(){return " " + helpText + ": " + helpInfo;}
}