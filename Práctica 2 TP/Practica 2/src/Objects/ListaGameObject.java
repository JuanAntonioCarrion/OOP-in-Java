package Objects;

public class ListaGameObject {
	
	public static final int MAX = 28;
	private GameObject[] lista;
	private int contador;
	
	public ListaGameObject() {
		
		lista = new GameObject[MAX];
		contador = 0;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void crear(GameObject objeto) {
		lista[contador] = objeto;
		contador++;
	}

	public String detectarElemento(int posX, int posY) {
		String elemento = "";
		int i = 0;
		boolean encontrado = false;
		
		while(!encontrado && i < contador) {
			encontrado = lista[i].coincide(posX, posY);
			if (!encontrado)
				i++;
		}
		
		if (encontrado) {
			elemento =  lista[i].getNombreAcort() + "[" + lista[i].getResistencia() + "]";
		}	
		
		return elemento;
	}
	
	public boolean coincide(int posX, int posY) {  //D
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < contador) {
			encontrado = lista[i].coincideElemento(posX, posY); 
			i++;
		}
		
		return encontrado;
	}
	
	public void buscarElementoPlanta(int posX, int posY, int dano) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contador) {
			encontrado = lista[i].coincideElemento(posX, posY);
			if (!encontrado)
				i++;
			else	
				quitarVidaPlanta(i, dano); //Si se ha encontrado una planta con esas coordenadas, llama a esta función para restar su resistencia y comprobar si hay que eliminarla de la lista o no
		}
	}
		
	public void buscarElementoZombie(int posX, int posY, int dano) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contador) {
			encontrado = lista[i].buscarZombieEnFila(posX, posY);
			if (!encontrado)
				i++;
			else
				quitarVidaZombie(i, dano);
		}	
	}
	
	public void dispararCasilla(int posX, int posY, int dano) { //solo para zombie
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < contador) {
			
			encontrado = lista[i].coincide(posX, posY);
			
			if (!encontrado)
				i++;
			else
				quitarVidaZombie(i, dano);
		}	
	}
	
     public void quitarVidaPlanta(int posPlantaArray, int dano) {
		
		boolean resistenciaCero = false;
			
		resistenciaCero = lista[posPlantaArray].quitarVidaElemento(dano);
			
		if(resistenciaCero == true) { //si la vida de la planta está a cero, se elimina del array => desplazar array hacia la izquierda
				
			matar(posPlantaArray);

    	}
	}
     
     private void matar(int posArray) {
    	for (int i = posArray + 1; i < contador; i++) {
 			lista[i - 1] = lista[i];
 		}
    	contador--;
     }

     public void quitarVidaZombie(int posZombieArray, int dano) {
 		
			boolean resistenciaCero = false;
			
			resistenciaCero = lista[posZombieArray].quitarVidaElemento(dano);
			
			if(resistenciaCero == true) {
				
			 matarZombie(posZombieArray);
			
			contador--;
		}
	 }
     
     private void matarZombie(int posZombieArray) {
    	for (int i = posZombieArray + 1; i < contador; i++) {
				lista[i - 1] = lista[i];
		}
     }
     
    public void updateElemento(){
		
    	for (int i = 0; i < contador; i++) {
			lista[i].update();
		
			if (lista[i].resistencia == 0) {
				matar(i);
				i--;
			}
	    }	
    }
    
    public String devolverLista(int contadorTotal) {
		String elemento = "";
		
		for (int i = 0; i < contadorTotal; i++) {
			
			if (contador <= i)
				elemento = lista[i].infoCompleta();
			
			else
				elemento = lista[i].infoCompleta();
		}
		
		return elemento;
	}
    
    public int SumaTotalContador() { //Esta estaba duplicado el contadorPlantas y zombies
    	return contador;
    }
    
    public int detectarPosicion(int posX, int posY) {
    	int posicion = 0;
    	boolean encontrado = false;
    	
    	while (!encontrado && posicion < contador) {
    		encontrado = posX == lista[posicion].posX && lista[posicion].posY == posY;
    		if (!encontrado)
    			posicion++;
    	}
    	return posicion;
    }
    
    public boolean haLlegado() {
    	boolean llegado = false;
    	int i = 0;
    	while (!llegado && i < contador) {
    		llegado = lista[i].getY() == 0;
    		
    		i++;
    	}
    		
    	return llegado;
    }
    
    
    public String detectarElementoDebug(int posX, int posY) {
		String elemento = "";
		int i = 0;
		boolean encontrado = false;
		
		while(!encontrado && i < contador) {
			encontrado = lista[i].coincide(posX, posY);
			if (!encontrado)
				i++;
		}
		
		if (encontrado) {
			elemento =  lista[i].getNombreAcort().toUpperCase() + ","+"[" + "l:" + lista[i].getResistencia() + ","+ "x:" + lista[i].getX() +","+"y:" + lista[i].getY() +","+ "t:" + lista[i].getCiclo() + "]";
		}

		return elemento;
	}
}
