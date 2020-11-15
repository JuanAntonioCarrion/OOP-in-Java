package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body> {

	public static final String _typetag = "mlb";
	public static final String _desc = "massLosingBuilder";
	
	public MassLosingBodyBuilder() {
		super(_typetag, _desc);
	}
	
	@Override
	public MassLossingBody createTheInstance(JSONObject json) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String ID = json.getString("id");
		double mass = json.getDouble("mass");
		double freq = json.getDouble("freq");
		double factor = json.getDouble("factor");
		if ((0 >= factor) || (1 <= factor) || (freq <= 0)) {
			throw new IllegalArgumentException();
		}
		double[] positionArray = new double[MAX_SIZE];
		double[] velocityArray = new double[MAX_SIZE];
		double[] accelerationArray = new double[MAX_SIZE];
		positionArray = jsonArrayTodoubleArray(json.getJSONArray("pos"));
		velocityArray = jsonArrayTodoubleArray(json.getJSONArray("vel"));
		Vector position = new Vector(positionArray);
		Vector velocity = new Vector(velocityArray);
		Vector acceleration = new Vector(accelerationArray);
		MassLossingBody body = new MassLossingBody(ID, position, velocity, acceleration, mass, factor, freq);
			
		return body;
	}
	
	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		return data;
	}

}
