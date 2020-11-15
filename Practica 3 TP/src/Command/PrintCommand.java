package Command;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Logic.Game;
import Managers.PrinterManager;
import Printer.BoardPrinter;

public class PrintCommand extends Command {
	private String modo;
	public final static String commandLetter = "p";
	public final static String commandText = "Printmode";
	public final static String commandInfo = "[P]rintMode <mode>";
	public final static String helpInfo = "change print mode [Release|Debug].";
	
	public PrintCommand() {
		super(commandText, commandInfo, helpInfo);	
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		BoardPrinter tablero = PrinterManager.parsePrinter(modo);
		if (tablero == null) {
			throw new CommandExecuteException("Unknown print mode: " + modo +"\n");
		}
		else {
			game.modificarInterfaz(tablero);
			game.printGame();
		}
		
		return false;
	}
	
	public Command parse(String[] texto) throws CommandParseException {
		Command comando = null;
		if (texto[0].toLowerCase().equals(commandLetter) || texto[0].toLowerCase().equals(commandText.toLowerCase())) {
			if (texto.length != 2) {
				throw new CommandParseException("Incorrect number of arguments for printmode command: [P]rintMode release|debug\n");
			}
			else {
				comando = this;
				modo = texto[1];
			}
		}
		return comando;
	}
}