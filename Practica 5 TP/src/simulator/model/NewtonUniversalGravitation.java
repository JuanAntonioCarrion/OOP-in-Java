package simulator.model;

import java.util.List;

import simulator.factories.Builder;
import simulator.misc.Vector;


public class NewtonUniversalGravitation implements GravityLaws {
	
	static private final double G = 6.67E-11; // the gravitational constant
	static public final String description = "Newton's law of universal gravitation (nlug)";

	
	@Override
	public void apply(List<Body> List) {
		Vector Accij;
		for (Body a: List) {	
			if (a.getMass() > 0) {
				Accij = new Vector(Builder.MAX_SIZE);
				for (Body b: List) {
					if (a != b) {
						Accij = Accij.plus(force(a, b));
						
					}
				}
				a.setAcceleration(Accij);
			}
			else {
				a.setVelocity(new Vector(Builder.MAX_SIZE));
				a.setAcceleration(new Vector(Builder.MAX_SIZE));
			}
		}
	}
	
	
	
	private Vector force(Body a, Body b) {
		
		Vector position; 
		Vector accij;
		double mass;
		double distance;
		double sqrdistance;
		double forceij;
		

		position = b.getPosition().minus(a.getPosition()).direction();
		mass = b.getMass() * a.getMass();
		distance = b.getPosition().distanceTo(a.getPosition());
		sqrdistance = distance * distance;
		forceij = G * mass / sqrdistance;
		accij = position.scale(forceij).scale(1/a.getMass());
		
		
		return new Vector(accij);
	}



	
	public String getDescription() {
		return description;
	}

}
