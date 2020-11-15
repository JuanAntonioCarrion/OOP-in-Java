package Command;

import Logic.Game;
import Utils.MyStringUtils;
import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Command.Command;
import Exceptions.FileContentsException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadCommand extends Command{

	public final static String commandText = "Load";
	public final static String commandLetter = "lo";
	public final static String commandTextMsg = "[Lo]ad <filename>";
	public final static String helpTextMsg = "Load the state of the game from a file.";
	private String filename;

	public LoadCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public Command parse(String[] texto) throws CommandParseException{
		
		Command comando = null;
		
		if (texto.length > 0) {
			
			if(texto[0].equals(commandText.toLowerCase()) || texto[0].equals(commandLetter.toLowerCase())) {
				
				if (texto.length != 2) {
					throw new CommandParseException("Incorrect number of arguments for load command: " + commandTextMsg + "\n");
				}
				
				else {
					
				    if(!MyStringUtils.isValidFilename(texto[1])) {
				    	throw new CommandParseException("Load failed: invalid file contents\n");
			    	}
				    else {
						if(!MyStringUtils.fileExists(texto[1] + ".dat")){
							throw new CommandParseException("File not found\n");
						}
						else {
							if(!MyStringUtils.isReadable(texto[1] + ".dat")) {
								throw new CommandParseException("File not readable\n");
							}
							else {
								filename = texto[1];
								comando = this;
							}
						}
				    }
				}
			}			
		}	
		return comando;
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		
		String ficheroCabecera;
		
		if(MyStringUtils.isValidFilename(filename + ".dat") && MyStringUtils.isReadable(filename + ".dat")) {
			
			try (BufferedReader br = new BufferedReader(new FileReader(filename + ".dat"))) {
				ficheroCabecera = br.readLine();
				
				if(!ficheroCabecera.equals(Game.HEADER)) {
					
					throw new CommandExecuteException("Incorrect header\n");
				}
				
				else {
					
					br.readLine();
					game.load(br);
					br.close();
					 System.out.println("Game successfully loaded from file " + filename + "\n");
				}
			}
			catch(IOException ex){
				throw new CommandExecuteException("An error ocurred while loading the file\n");
	        }
			catch(FileContentsException ex){
				throw new CommandExecuteException(ex.getMessage());
	        }	
			game.printGame();
			
		 }
		
		else {
			
			throw new CommandExecuteException("The file "+ filename + " is not valid\n");
		}
		
		return false;
    }
}
