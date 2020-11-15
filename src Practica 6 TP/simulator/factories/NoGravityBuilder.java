package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {

	public static final String _typetag = "ng";
	public static final String _desc = "noGravityBuilder";
	
	public NoGravityBuilder(){
		super(_typetag, _desc);
	}
	@Override
	public GravityLaws createTheInstance(JSONObject json) {
		// TODO Auto-generated method stub
		GravityLaws laws = new NoGravity();
		return laws;
	}

}
