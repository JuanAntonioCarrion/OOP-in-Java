package Command;

import Logic.Game;
import Managers.FactoryZombie;

public class ZombieListCommand extends NoParamsCommand {
	
	public final static String commandLetter = "z";
	public final static String commandText = "ZombieList";
	public final static String commandInfo = "[Z]ombieList";
	public final static String helpInfo = "print the list of zombies.";
	
	public ZombieListCommand() {
		super(commandLetter, commandText, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		System.out.println(FactoryZombie.getZombieList());
		
		return false;
	}
	
}