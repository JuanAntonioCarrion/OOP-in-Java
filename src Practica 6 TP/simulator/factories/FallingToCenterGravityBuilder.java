package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws>{
	
	public static final String _typetag = "ftcg";
	public static final String _desc = "fallingToCenterGravityBuilder";
	
	public FallingToCenterGravityBuilder() {
		super(_typetag, _desc);
	}

	public GravityLaws createTheInstance(JSONObject json) {
		// TODO Auto-generated method stub
		GravityLaws laws = new FallingToCenterGravity();
		return laws;
	}
	
}
