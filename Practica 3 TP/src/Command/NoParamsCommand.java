package Command;

import Exceptions.CommandParseException;
import Logic.Game;

public abstract class NoParamsCommand extends Command {
	
	protected String commandLetter;
	protected String commandText;
	
	public NoParamsCommand(String commandLetter, String commandText, String commandInfo, String helpInfo) {
			super(commandText, commandInfo, helpInfo);
			this.commandLetter = commandLetter;
			this.commandText = commandText;
	}
	
	public abstract boolean execute(Game game);
	
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando = null;
		if (commandText.toLowerCase().equals(commandWords[0]) || commandLetter.toLowerCase().equals(commandWords[0])) {
			if (commandWords.length == 1) {
				comando = this;
			}
			else
				throw new CommandParseException(commandText + " command has no arguments\n");
		}
		return comando;
	}
}