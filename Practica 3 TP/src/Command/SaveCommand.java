package Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Logic.Game;
import Command.Command;
import Utils.MyStringUtils;
import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;

public class SaveCommand extends Command {
	
	private String filename;
	public final static String commandLetter = "s";
	public final static String commandText = "save";
	public final static String helpTextMsg = "[S]ave";
	public final static String helpInfo = "Terminates the program";
	
	public SaveCommand() {
		super(commandText, helpTextMsg, helpInfo);
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public SaveCommand(String commandText, String commandTextMsg, String helpTextMsg){
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public boolean execute(Game game) throws CommandExecuteException {
	
		    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".dat"))){
		    game.insertarAlFichero(writer);
		    writer.close();
		    System.out.println("Game successfully saved to file " + filename +", use the load command to reload it\n");
		    }
		    catch(IOException ex){
		    	throw new CommandExecuteException("Failed to read file\n");
		    }
	     
	     return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		
		Command command = null;

		if(commandWords.length > 0){

			String commandName = commandWords[0];

			if(commandName.equals("s") || commandName.equals("save")){
				if(commandWords.length!= 2){
					throw new CommandParseException("Incorrect number of arguments for save command: [S]ave FileName\n");
				}
				else{
					if (MyStringUtils.isValidFilename(commandWords[1])) {
						command = this;
						filename = commandWords[1];
					}
					else
						throw new CommandParseException("Unable to use that file name\n");				
				}
			}
		}
		return command;	
	}
}
