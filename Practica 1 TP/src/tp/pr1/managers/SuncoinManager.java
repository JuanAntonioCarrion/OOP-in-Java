package tp.pr1.managers;

public class SuncoinManager {
	
	private int numeroSoles;
	
	public SuncoinManager() {
		numeroSoles = 50;
	}
	public void setSoles(int numSoles) { //suma al número de soles los que han generado los girasoles en ese ciclo
		numeroSoles += numSoles;
	}	
	
	public int getNumeroSoles() { //devuelve el numero de soles
		return numeroSoles;
	}
	
	public void restarSoles(int precio) { // Esta funcion es llamada cuando se compra una planta => resta el coste de la planta al total de soles
		numeroSoles -= precio;
	}
}
