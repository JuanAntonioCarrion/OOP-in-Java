package Command;

import Logic.Game;

public class UpdateCommand extends NoParamsCommand {
	public final static String commandText = "None";
	public final static String commandLetter = "";
	public final static String commandTextMsg = "none";
	public final static String helpInfo = "Skips cycle";
	
	public UpdateCommand() {
		super(commandLetter, commandText, commandTextMsg , helpInfo);
	}
	
	public boolean execute(Game game) {
		return true;
	}
}
