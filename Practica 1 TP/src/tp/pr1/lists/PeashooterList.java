package tp.pr1.lists;
import tp.pr1.objects.Peashooter;

public class PeashooterList {
	
	public static final int MAX_LANZAGUISANTES = 32; //4*8 casillas
	private Peashooter[] listaLanzaguisantes;
	private int contLanzaguisantes;
	
	public PeashooterList() {
		contLanzaguisantes = 0;
		listaLanzaguisantes = new Peashooter[MAX_LANZAGUISANTES];
	}
	public void crearLanzaguisantes(Peashooter peashooter){ //crear un elemento en la lista
		listaLanzaguisantes[contLanzaguisantes] = peashooter;
		contLanzaguisantes++;
	}
	
	public void updateP(){ //recorre la lista de peashooter para que cada planta ejecute su update
		
		for(int i = 0; i < contLanzaguisantes; i++) {
			listaLanzaguisantes[i].updateLanz();		
	     }	
	}
	
	public boolean coincide(int posXZ,int posYZSiguiente){
		
		boolean libre = false;
		int i = 0;
		while(!libre && i < contLanzaguisantes) {
			
			libre = listaLanzaguisantes[i].coincideL(posXZ, posYZSiguiente); //Compara las coordenadas de la planta con => las coordenadas de una nueva planta (comando add) , o => con las del zombie (casilla siguiente libre)
			if(!libre)
				i++;
		}
		
		return libre;
	}
	
	public void matarLanzaguisantes(int posLanzaguisantesArray) {
		
		boolean resistenciaCero = false;
		
		resistenciaCero = listaLanzaguisantes[posLanzaguisantesArray].quitarVidaLanzaguisantes(); //Comprueba si la resistencia de la planta está a 0 o no => si está a 0, se elimina de la lista, si no, solo le resta un punto de resistencia
		
		if(resistenciaCero == true) { //si la vida de la planta está a cero, se elimina del array => desplazar array hacia la izquierda
			
		for (int i = posLanzaguisantesArray + 1; i < contLanzaguisantes; i++) {
			listaLanzaguisantes[i - 1] = listaLanzaguisantes[i];
		}
		
		contLanzaguisantes--;
		}
	}
	
	public void buscarPeashooter(int posX, int posY) { //Busca en la lista si hay alguna planta que esté delante de algún zombie, para que el zombie le quite vida
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contLanzaguisantes) {
			encontrado = listaLanzaguisantes[i].coincideL(posX, posY);
			if (!encontrado)
				i++;
			else
				matarLanzaguisantes(i); //Si se ha encontrado una planta con esas coordenadas, llama a esta función para restar su resistencia y comprobar si hay q eliminarla de la lista o no
		}			
	}
	
	public String detectarElemento(int posX, int posY){ //Si al recorrer las listas para mostrar el tablero, coincide esa coordenada con las de un peashooter, devuelve "P" y su resistencia
		boolean encontrado = false;
		String elemento = "";
		int i = 0;
		while(!encontrado && i < contLanzaguisantes) {
			encontrado = listaLanzaguisantes[i].coincideL(posX, posY);
		if(encontrado) {
			elemento = "P" + " [" + listaLanzaguisantes[i].getResistenciaPeashooter()  + "]";
		}
		else
			i++;
		}
		return elemento;
	}
}
