package Logic;

import java.util.Scanner;
import Printer.DebugPrinter;
import Printer.ReleasePrinter;
import Printer.GamePrinter;
import Command.CommandParser;
import Command.Command;

public class Controller {
	
	private Game game;
	private Scanner in; 
	private String prompt = "Command > ";
	private String unknownCommandMsg = "Unknown command";
	private boolean exit;
	private boolean noPrint;
	private GamePrinter gp;
	private DebugPrinter tableroDebug;
	private ReleasePrinter tableroRelease;
	
	public Controller(Game game){
		this.game = game;
		tableroDebug = new DebugPrinter();
		tableroRelease = new ReleasePrinter();
		in = new Scanner(System.in);
	}

	public void run() {
		exit = false;
		game.inicializarJuego();
		printGame();
		while(!exit) {
				setPrintGameState();
				System.out.print(prompt);
				String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
				Command command = CommandParser.parseCommand(words);
				if (command != null) {
					command.execute(game, this);
				}
				else {
					System.err.println(unknownCommandMsg);
					setNoPrintGameState();
				}	
				
				if (!noPrint) {
					game.update();
					printGame();
				}
				if (!exit)
					exit = game.isFinished();
			}
		
	}
	
	public void setNoPrintGameState() {
		noPrint = true;
	}
	
	public void setPrintGameState() {
		noPrint = false;
	}
	
	public void salir() {
		exit = true;
	}
	
	public void printGame() {
		game.imprimirDatos();
	//	if (!debug)
		gp.printGame(game);
	//	else
	//		tableroDebug.printGame(game);
	}
	
	public void cambiarTablero() {
		if (gp == tableroDebug) 
			gp = tableroRelease;
		else
			gp = tableroDebug;
	}
	
	public void fijarDebug() {
		gp = tableroDebug;
	}
	
	public void fijarRelease() {
		gp = tableroRelease;
	}
}