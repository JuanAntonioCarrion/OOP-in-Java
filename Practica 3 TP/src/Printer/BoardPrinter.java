package Printer;

import Logic.Game;


public abstract class BoardPrinter implements GamePrinter {
	protected final int dimX = Game.MAX_FILAS;
	protected final int dimY = Game.MAX_COLUMNAS;
	protected final String space = " ";
	protected String[][] board;
	protected int cellSize;
	private String nombre;
	
	public BoardPrinter(String nombre) {
		this.nombre = nombre;
	}
	
	public String BoardToString() {
		String tablero = "";
		return tablero;
	}
	
	public abstract String toString();
	
	public BoardPrinter parsePrint(String boardName) {
		BoardPrinter tablero = null;
		if (boardName.toLowerCase().equals(nombre.toLowerCase())) {
			tablero = this;
		}
		return tablero;
	}
	
}
