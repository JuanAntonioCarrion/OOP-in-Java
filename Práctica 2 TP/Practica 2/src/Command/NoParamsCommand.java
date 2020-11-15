package Command;

import Logic.Controller;
import Logic.Game;

public abstract class NoParamsCommand extends Command {
	
	protected String commandLetter;
	protected String commandText;
	
	public NoParamsCommand(String commandLetter, String commandText, String commandInfo, String helpInfo) {
			super(commandText, commandInfo, helpInfo);
			this.commandLetter = commandLetter;
			this.commandText = commandText;
	}
	
	public abstract void execute(Game game, Controller controller);
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 1) {
			if (commandText.equals(commandWords[0]) || commandLetter.equals(commandWords[0])) {
				comando = this;
			}
		}
		
		return comando;
	}
}