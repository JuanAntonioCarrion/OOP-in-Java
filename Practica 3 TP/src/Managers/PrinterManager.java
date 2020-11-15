package Managers;
import Printer.BoardPrinter;
import Printer.DebugPrinter;
import Printer.ReleasePrinter;

public class PrinterManager {
	public static BoardPrinter[] availableBoards = {
		new DebugPrinter(),
		new ReleasePrinter()
	};
	
	public static BoardPrinter parsePrinter(String boardName){
		BoardPrinter tablero = null;
		for(BoardPrinter t: availableBoards) {
			if (tablero == null) {
				tablero = t.parsePrint(boardName);
			}
		}
		
		return tablero;
	}
}
