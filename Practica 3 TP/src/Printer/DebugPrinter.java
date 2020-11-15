package Printer;

import Logic.Game;
import Utils.MyStringUtils;

public class DebugPrinter extends BoardPrinter {

	int contadorElemTotal ;
	public static final String NOMBRE = "Debug";
	
	public DebugPrinter() {
		super(NOMBRE);
	}
	
	public void printGame(Game game) {
		encodeGame(game);
		String tablero = toString();
		System.out.println(tablero);
	}
	
	
	public String toString() {
		int cellSize = 19;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (contadorElemTotal*(cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		
				str.append(margin).append(vDelimiter);
				for(int i=0; i<contadorElemTotal; i++) 
					str.append( MyStringUtils.centre(board[i][0], cellSize)).append(vDelimiter);
				str.append(lineDelimiter);
		
		return str.toString();
	}
	
	private void encodeGame(Game game) {
		
		contadorElemTotal = game.getContadorTotal();
		int valorUnidimensional = 1;
		board = new String[contadorElemTotal][valorUnidimensional];
		String elem;
		int k=0;
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {
				
				elem = game.elementoTableroDebug(i, j); //Compara cada coordenada del tablero con las posiciones de todas las listas
				
				if(elem!="") {
				board[k][0] = elem;
				k++;
				}
			}
		}
	}
}

