package Objects;

public class Zombie extends GameObject implements Cloneable {
	
	private int ciclos;
	
	public Zombie(int resistencia, int dano, int ciclos, String nombre, String nombre_acort) {
		super(resistencia, dano, ciclos, nombre, nombre_acort);
		this.ciclos = ciclos;
	}
	
	public void update() {
		if (poderAvanzarCasilla() && !game.casillaOcupada(posX, posY - 1)) { //comprueba que la casilla siguiente este vacía para poder avanzar(siempre y cuando haya cumplido dos ciclos, por supuesto)
			posY -= 1; //Si la casilla está vacia, el zombie avanza
			if (posY == 0)
				game.gananZombies();
		}	
		else 	//si el zombie no puede avanzar porque la casilla delante está ocupada, comprueba que sea una planta para quitarle vida
			game.quitarVida(posX, posY, dano); 
	}
	
	public boolean poderAvanzarCasilla() {
		
		boolean avanzarCiclo = false;
		
		if (ciclosRestantes == 1) { //si la velocidad del zombie == 2 => avanza una casilla
			avanzarCiclo = true;
			ciclosRestantes = ciclos;
		}
	
		else 
			ciclosRestantes--;
		
		return avanzarCiclo;
	}
	
	public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
}
