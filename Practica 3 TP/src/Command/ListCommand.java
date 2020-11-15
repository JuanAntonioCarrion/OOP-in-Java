package Command;

import Logic.Game;
import Managers.FactoryPlant;

public class ListCommand extends NoParamsCommand {
	
	public final static String commandLetter = "l";
	public final static String commandText = "List";
	public final static String commandInfo = "[L]ist";
	public final static String helpInfo = "print the list of available plants";
	
	public ListCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);
	}

	public boolean execute(Game game) {
		String stringPlantList;
		stringPlantList = FactoryPlant.listOfAvailablePlants();
		System.out.println(stringPlantList);
		return false;
	}
}