package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	
	
	private List<Builder<T>> builders;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders = builders;
	}
	

	public T createInstance(JSONObject json) throws IllegalArgumentException {
		T object = null;
		for (Builder<T> builder: builders) {
			if (object == null)
				object = builder.createInstance(json);
		}

		if (object == null) {
			throw new IllegalArgumentException();
		}
		return object;
	}

	public List<JSONObject> getInfo(){
		ArrayList<JSONObject> JSONArray = new ArrayList<JSONObject>();
		for (Builder<T> Builder: builders) {
			JSONArray.add(Builder.getBuilderInfo());
		}

		return JSONArray;
	}
}
