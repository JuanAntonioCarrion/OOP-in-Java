package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

	public static final String _typetag = "nlug";
	public static final String _desc = "newtonUniversalGravitationBuilder";
	
	public NewtonUniversalGravitationBuilder() {
		super(_typetag, _desc);
	}
	
	@Override
	public GravityLaws createTheInstance(JSONObject json) {
		// TODO Auto-generated method stub
		GravityLaws laws = new NewtonUniversalGravitation();
		return laws;
	}
	
}