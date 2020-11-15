package Printer;

import Logic.Game;
import Utils.MyStringUtils;

public class ReleasePrinter extends BoardPrinter{
	
	public static final String NOMBRE = "Release";

	
	public ReleasePrinter() {
		super(NOMBRE);
	}
	
	private void encodeGame(Game game) {
		board = new String[dimX][dimY];
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {

				board[i][j] =  space;
				
				board[i][j] = game.elementoTablero(i, j); //Compara cada coordenada del tablero con las posiciones de todas las listas
				
			}
		}
	}
	
	public void printGame(Game game) {
		encodeGame(game);
		String tablero = toString();
		System.out.println(tablero);
		
	}
	
	public String toString() {
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		
		return str.toString();
	}
	
	
}