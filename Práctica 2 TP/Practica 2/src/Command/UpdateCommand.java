package Command;

import Logic.Controller;
import Logic.Game;

public class UpdateCommand extends NoParamsCommand {
	public final static String commandText = "none";
	public final static String commandLetter = "";
	public final static String commandTextMsg = "none";
	public final static String helpInfo = "Skips cycle";
	
	public UpdateCommand() {
		super(commandText, commandLetter, commandTextMsg , helpInfo);
	}
	
	public void execute(Game game, Controller controller) {
		controller.setPrintGameState();
	}
}
