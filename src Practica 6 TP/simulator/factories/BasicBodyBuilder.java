package simulator.factories;


import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;


public class BasicBodyBuilder extends Builder<Body> {

	public static final String _typetag = "basic";
	public static final String _desc = "basicBodyBuilder";
	
	
	public BasicBodyBuilder() {
		super(_typetag, _desc);
	}
	
	@Override
	public Body createTheInstance(JSONObject json)  throws IllegalArgumentException {
		String ID = json.getString("id");
		double mass = json.getDouble("mass");
		if (mass <= 0) 
			throw new IllegalArgumentException();
		
		double[] positionArray = new double[MAX_SIZE];
		double[] velocityArray = new double[MAX_SIZE];
		double[] accelerationArray = new double[MAX_SIZE];
		positionArray = jsonArrayTodoubleArray(json.getJSONArray("pos"));
		velocityArray = jsonArrayTodoubleArray(json.getJSONArray("vel"));
		Vector position = new Vector(positionArray);
		Vector velocity = new Vector(velocityArray);
		Vector acceleration = new Vector(accelerationArray);
		Body body = new Body(ID, position, velocity, acceleration, mass);
		return body;
	}
	
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		return data;
	}
	
}
