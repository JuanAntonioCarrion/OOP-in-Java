package Exceptions;

public class CommandParseException  extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandParseException() { 
		super(); 
	}
	
	public CommandParseException(String mensaje) {
		super(mensaje);
	}
	
	public CommandParseException(String message, Throwable cause){ super(message, cause); }
	
	public CommandParseException(Throwable cause){ super(cause); }
}
