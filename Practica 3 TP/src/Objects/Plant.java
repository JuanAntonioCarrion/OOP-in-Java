package Objects;

public abstract class Plant extends GameObject implements Cloneable {
	private int coste;
	private String nombrePrint;
	
	public Plant(int coste, int ciclos, int dano, int resistencia, String nombre, String nombreAcort, String nombrePrint) {
    	super(resistencia, dano, ciclos, nombre, nombreAcort);
		this.coste = coste;
		this.nombrePrint = nombrePrint;
	}
	
	public String getName() {
		return nombrePrint;
	}
	
	public int getCoste() {
		return coste;
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
	
	public abstract void update();
}
