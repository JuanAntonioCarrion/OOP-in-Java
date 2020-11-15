package tp.pr1.lists;
import tp.pr1.objects.Zombie;

public class ZombieList {
	
	private Zombie[] listaZombies;
	private int contZombies;
	
	public ZombieList() {
		contZombies = 0;
		listaZombies = new Zombie[10];
	}
	
	public int getContadorZombie() { //Devuelve el contador de la lista
		return contZombies;
	}
	
	public void crearZombie(Zombie zombie) { //crear un elemento en la lista
		listaZombies[contZombies] = zombie;
		contZombies++;
	}
	
	public void quitarVidaZ(int posXL, int posYL){
		boolean encontrado=false;
		int i = 0;
	    while(!encontrado && i < contZombies) {
	    	encontrado = listaZombies[i].coincide(posXL,posYL); //compara las coordenadas del zombie con las del peashooter para saber si hay un zombie delante y quitarle vida
	    	if (encontrado) {
	    		if (listaZombies[i].getResistenciaZombie() == 0) 
	    			matarZombies(i); //si la vida del zombie está a cero, se elimina del array => desplazar array hacia la izquierda
	    	}
	    	if (!encontrado) 
	    		i++;
	    }
	}
	public void matarZombies(int posZombieArray) {
		for (int i = posZombieArray + 1; i < contZombies; i++) // desplazar array hacia la izquierda
			listaZombies[i - 1] = listaZombies[i];
		contZombies--;	
	}
	
	public boolean coincide(int posXZ, int posYZSiguiente){
		boolean libre = false;
		int i = 0;
		while(!libre && i< contZombies) {
			libre = listaZombies[i].coincideZ(posXZ, posYZSiguiente); //Compara las coordenadas del zombie con => las del zombie (casilla siguiente libre)
			if(!libre)
				i++;		
		}
		
		return libre;
	}
	
	public boolean updateZ(){ //recorre la lista de zombie para que cada zombie ejecute su update
		boolean llegaFinal = false;
		int i = 0;
		while(i < contZombies && !llegaFinal) {
			llegaFinal = listaZombies[i].updateZombie(); //Si durante el update de algún zombie, llega al final del tablero, finaliza la partida y ganan los zombies
			i++;
		}
		
		return llegaFinal;
	}
	
	public String detectarElemento(int posX, int posY){ //Si al recorrer las listas para mostrar el tablero, coincide esa coordenada con las de un peashooter, devuelve "Z" y su resistencia
		boolean encontrado = false;
		String elemento = "";
		int i = 0;
		while(!encontrado && i < contZombies) {
			encontrado = listaZombies[i].coincideZ(posX, posY);
			if (encontrado) 
				elemento = "Z" + " [" + listaZombies[i].getResistenciaZombie()  + "]";
			else
				i++;
		}
		
		return elemento;
	}
}
