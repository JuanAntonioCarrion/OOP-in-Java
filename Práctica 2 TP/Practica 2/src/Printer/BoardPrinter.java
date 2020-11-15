package Printer;

import Logic.Game;


public abstract class BoardPrinter implements GamePrinter {
	protected final int dimX = Game.MAX_FILAS;
	protected final int dimY = Game.MAX_COLUMNAS;
	protected final String space = " ";
	protected String[][] board;
	protected int cellSize;
	
	public String BoardToString() {
		String tablero = "";
		return tablero;
	}
	
	public abstract String toString();
	
}
