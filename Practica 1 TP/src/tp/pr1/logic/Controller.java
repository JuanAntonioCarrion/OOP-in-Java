package tp.pr1.logic;

import java.util.Scanner;

public class Controller {
	
	private Game game;
	private Scanner in; 
	
	public Controller(Game game){
		this.game = game;
		in = new Scanner(System.in);
	}

	public void run() {
		boolean opcionCorrecta = false;
		boolean reset;
		boolean exit = false;
		String[] entrada;
		while(!exit) {
			game.inicializarJuego();
			game.reiniciarCiclos();
			game.draw();
			reset = false;
			while (!exit && !reset) {
				opcionCorrecta = false;
				while (!opcionCorrecta && !exit && !reset) {
					entrada = userCommand();
					if (entrada[0].equals("reset") || entrada[0].equals("r"))
						reset = true;
					else if (entrada[0].equals("exit") || entrada[0].equals("e")) {
						System.out.println("Game over");
						exit = true;
					}
					else
						opcionCorrecta = game.elegirOpcion(entrada);
				}	
				if(!exit && !reset) {
					game.computerAction();
					exit = game.update();
					game.draw();
				}
			}
		}
	}
	
	String[] userCommand() {
		System.out.print("Command > ");
		String[] entrada = in.nextLine().toLowerCase().split(" ");
		return entrada;
	}
}
