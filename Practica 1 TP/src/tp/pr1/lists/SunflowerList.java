package tp.pr1.lists;
import tp.pr1.objects.Sunflower;

public class SunflowerList {
	public static final int MAX_GIRASOLES = 32; //4*8 casillas
	private Sunflower[] listaGirasoles;
	private int contGirasoles;
	
	public SunflowerList() {
		this.contGirasoles = 0;
		listaGirasoles = new Sunflower[MAX_GIRASOLES]; 
	}
	
	public void crearGirasol(Sunflower girasol){ //crear un elemento lista
		listaGirasoles[contGirasoles] = girasol;
		contGirasoles++;
	}
	
	public void updateListaG() { //recorre la lista de girasoles para que cada planta ejecute su update
		for (int i = 0; i < contGirasoles; i++) {
			 listaGirasoles[i].updateGirasol();			
		}	
	}
	
	public boolean coincide(int posXZ, int posYZSiguiente){ 
		
		boolean libre = false;
		int i = 0;
		while(!libre && i < contGirasoles) {
			libre = listaGirasoles[i].coincideG(posXZ, posYZSiguiente); //Compara las coordenadas de la planta con => las coordenadas de una nueva planta (comando add) , o => con las del zombie (casilla siguiente libre)
			if(!libre)
				i++;
		}
		return libre;
	}
	
	public void muerteGirasol(int posGirasolArray) { //Si el zombie ha atacado al girasol, al tener 1 de resistencia, lo elimina directamente
		for (int i = posGirasolArray + 1; i < contGirasoles; i++) //se elimina el girasol del array => desplazar posiciones array hacia la izquierda
			listaGirasoles[i - 1] = listaGirasoles[i];
		contGirasoles--;
	}
	
	public void buscarGirasol(int posX, int posY) { //Busca en la lista si hay alguna planta que esté delante de algún zombie, para que el zombie le quite vida
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contGirasoles) {
			encontrado = listaGirasoles[i].coincideG(posX, posY);
			if (!encontrado)
				i++;
			else
				muerteGirasol(i); //Si se ha encontrado una planta  con esas coordenadas, la elimina de la lista
		}			
	}
	
	public String detectarElemento(int posX, int posY){ //Si al recorrer las listas para mostrar el tablero, coincide esa coordenada con las de un girasol, devuelve "S" y su resistencia
		boolean encontrado = false;
		String elemento = "";
		int i = 0;
		while(!encontrado && i < contGirasoles) {
			encontrado = listaGirasoles[i].coincideG(posX, posY);
			if (encontrado) 
				elemento = "S" + " [" + listaGirasoles[i].getResistenciaSunflower()  + "]";
			else
				i++;
		}
		return elemento;
	}
}
