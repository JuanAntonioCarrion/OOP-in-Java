package Exceptions;

public class FileContentsException  extends CommandExecuteException{

	private static final long serialVersionUID = 1L;

	public FileContentsException() { 
		super(); 
	}
	
	public FileContentsException(String mensaje) {
		super(mensaje);
	}
	
	public FileContentsException(String message, Throwable cause){ super(message, cause); }
	
	public FileContentsException(Throwable cause){ super(cause); }
}
