package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {
	
	protected String _typetag;
	protected String _desc;
	public static final int MAX_SIZE = 2; //Final attribute that gives us the size of the array
	
	public Builder(String _typetag, String _desc) {
		this._typetag = _typetag;
		this._desc = _desc;
	}
	
	protected double[] jsonArrayTodoubleArray(JSONArray ja) {
		 double[] da = new double[ja.length()];
		 for (int i = 0; i < da.length; i++)
		 da[i] = ja.getDouble(i);
		 return da;
	}
	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		T instance = null;
		
		if (_typetag != null && _typetag.equals(info.getString("type"))) {
			instance = createTheInstance(info.getJSONObject("data"));
		}
		return instance;
	}
	
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		info.put("type", _typetag);
		info.put("data", createData());
		info.put("desc", _desc);
		return info;
	}
	
	protected JSONObject createData() {
		 return new JSONObject();
	}
	
	protected abstract T createTheInstance(JSONObject json);
}
