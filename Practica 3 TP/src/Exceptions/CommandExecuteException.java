package Exceptions;

public class CommandExecuteException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandExecuteException() { 
		super(); 
	}
	
	public CommandExecuteException(String mensaje) {
		super(mensaje);
	}
	
	public CommandExecuteException(String message, Throwable cause){ super(message, cause); }
	
	public CommandExecuteException(Throwable cause){ super(cause); }
}
