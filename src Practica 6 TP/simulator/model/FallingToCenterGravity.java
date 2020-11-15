package simulator.model;

import java.util.List;

import simulator.factories.Builder;
import simulator.misc.Vector;

public class FallingToCenterGravity implements GravityLaws {
	static private final double g = 9.81;
	static private final String description = "Falling to center gravity (ftcg)";

	@Override
	public void apply(List<Body> List) {
		Vector vector;
		double hypotenuse;
		double proportion;
		for (Body a: List) {
			vector = new Vector(a.getPosition());
			hypotenuse = vector.magnitude();
			if (hypotenuse > 0) {
				proportion = -g/hypotenuse;
				a.setAcceleration(vector.scale(proportion));
			}
			else {
				a.setAcceleration(new Vector(Builder.MAX_SIZE));
			}
		}
	}
	@Override
	public String getDescription() {
		return description;
	}

}
