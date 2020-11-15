package simulator.model;

import java.util.List;

public class NoGravity implements GravityLaws{

	static private final String description = "No gravity (ng)";

	@Override
	public void apply(List<Body> List) {
		//The acceleration vector does not change, the method is empty
	}

	@Override
	public String getDescription() {
		return description;
	}

}
