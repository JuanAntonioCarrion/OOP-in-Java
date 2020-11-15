package Command;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Logic.Game;
import Objects.Plant;
import Managers.FactoryPlant;

public class AddCommand extends Command  {
	
	public final static String commandText = "Add";
	public final static String commandLetter = "a";
	public final static String commandTextMsg = "[A]dd <plant> <x> <y>";
	public final static String helpTextMsg = "adds a plant in position x, y";
	private int posX;
	private int posY;
	private String textoPlanta;
	
	public AddCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
		
	public boolean execute(Game game) throws CommandExecuteException {
		
		
		Plant planta = FactoryPlant.getPlant(textoPlanta);
		game.anadePlanta(planta, posX, posY);
		boolean imprimir = true;
		
		return imprimir;
	}
	
	public Command parse(String[] texto) throws CommandParseException{
		
		Command comando = null;
		
		if (texto.length > 0) {
			
			if(texto[0].equals(commandText.toLowerCase()) || texto[0].equals(commandLetter.toLowerCase())) {
				
				if (texto.length != 4) {
					throw new CommandParseException("Incorrect number of arguments for add command: " + commandTextMsg + "\n");
				}
				
				try {
					
					posX = Integer.parseInt(texto[2]);
					posY =  Integer.parseInt(texto[3]);
					comando = this;
		            textoPlanta = texto[1];
		        }
				catch (NumberFormatException excepcion) {
					throw new CommandParseException("Invalid argument for add command, number expected: " + commandTextMsg + "\n");
		        }
			}			
		}	
		return comando;
	}
}
