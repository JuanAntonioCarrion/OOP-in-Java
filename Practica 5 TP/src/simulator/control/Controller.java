package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.PhysicsSimulator;
import simulator.view.SimulatorObserver;
import simulator.model.Body;
import simulator.model.GravityLaws;

public class Controller {
	
	private Factory<Body> _bodiesFactory;
	private PhysicsSimulator _simulator;
	private Factory<GravityLaws> _glFactory;
	
	public Controller(PhysicsSimulator simulator, Factory<Body> factory, Factory<GravityLaws> glFactory) {
		_simulator = simulator;
		_bodiesFactory = factory;
		_glFactory = glFactory;
	}
	
	public void run(int n, OutputStream out) {
		PrintStream p = (out == null) ? System.out : new PrintStream(out);
		String export;
		p.println("{");
		p.println("\"states\": [");
		export = (_simulator.toString() + ",");
		p.println(export);
		export = _simulator.toString() +"/n";
		for (int i = 0; i < n; i++) {
			_simulator.advance();
			export = _simulator.toString();
			if (i != n-1) {
				export += ",";
			}
			else {
				export += "\n]\n}";
			}
			p.println(export);
		}
		p.close();
		
	}
	
	public void loadBodies(InputStream in) throws Exception {
		 JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		 JSONArray bodies = jsonInput.getJSONArray("bodies");
		 for (int i = 0; i < bodies.length(); i++) {
			 _simulator.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		 }
	}
	
	public void reset() {
		_simulator.reset();
	}
	
	public void setDeltaTime(double dt) {
		_simulator.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o) {
		_simulator.addObserver(o);
	}
	
	public void run(int n) {
		for (int i = 0; i < n; i++) {
			_simulator.advance();
		}
	}
	
	public Factory<GravityLaws> getGravityLawsFactory() {
		return _glFactory;
	}
	
	public void setGravityLaws(JSONObject info) {
		_simulator.setGravityLaws(_glFactory.createInstance(info));
	}
}
