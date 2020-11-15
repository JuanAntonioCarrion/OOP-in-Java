package Command;

import Logic.Controller;
import Logic.Game;
import Objects.Plant;
import Managers.FactoryPlant;

public class AddCommand extends Command  {
	
	public final static String commandText = "add";
	public final static String commandLetter = "a";
	public final static String commandTextMsg = "[A]dd <plant> <x> <y>";
	public final static String helpTextMsg = "adds a plant in position x, y";
	private int posX;
	private int posY;
	private String textoPlanta;
	
	public AddCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
		
	public void execute(Game game, Controller controller) {
		Plant planta = FactoryPlant.getPlant(textoPlanta);
		boolean dentro = false, dineroSuficiente = false, ocupada = false;
		
		if (planta != null) {
			dentro = game.comprobarDentro(posX, posY);
			
			if (dentro) {
				
				dineroSuficiente = game.compararPrecio(planta.getCoste());
				ocupada = game.casillaOcupada(posX, posY);
				
				if (!dineroSuficiente)
					System.out.println("Not enough suncoins");
				
				else if (ocupada)
					System.out.println("Occupied cell");
					
			}
			
			else
				System.out.println("Out of the board");
			
			if (dentro && dineroSuficiente && !ocupada) {
				
				planta.setAtributos(posX, posY, game);
				game.anadirPlanta(planta, posX, posY);
				game.gastarDinero(planta.getCoste());
				controller.setPrintGameState();
			}
		}
		
		else
			System.out.println("Invalid object");
		
		if (planta == null || !dineroSuficiente || !dentro || ocupada) {
			controller.setNoPrintGameState();
		}
	}
	
	public Command parse(String[] texto) {
		
		Command comando = null;
		boolean sonNumeros = false;
		
		if (texto.length == 4) {
			try {
	            Integer.parseInt(texto[2]);
	            Integer.parseInt(texto[3]);
	            sonNumeros = true;
	        } 
			catch (NumberFormatException excepcion) {
	            sonNumeros = false;
	        }
			if (sonNumeros) {
				if (texto[0].equals(commandText) || texto[0].equals(commandLetter)) {
					comando = this;
					posX = Integer.parseInt(texto[2]);
					posY = Integer.parseInt(texto[3]);
					textoPlanta = texto[1];
				}
			}
		}	
		return comando;
	}
}
