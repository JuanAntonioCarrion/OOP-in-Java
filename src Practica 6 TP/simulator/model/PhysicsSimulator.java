package simulator.model;


import java.util.ArrayList;
import java.util.List;

import simulator.view.SimulatorObserver;

public class PhysicsSimulator {
	private List<SimulatorObserver> _observerList;
	private GravityLaws _gravityLaws;
	private List<Body> _bodies;
	private double _dt;
	private double _time;
	
	public PhysicsSimulator(GravityLaws gravityLaws, double dt) {
		_observerList = new ArrayList<>();
		_bodies = new ArrayList<>();
		_time = 0;
		setGravityLaws(gravityLaws);
		setDeltaTime(dt);
	}
	
	public void addBody(Body b) throws Exception{
		
		if (_bodies.contains(b)) {
			throw new Exception();
		}
		_bodies.add(b);

	/*	for (int i = 0; i < _bodies.size(); i++) {
			if(_bodies.get(i).getId().equals(b.getId())) {
				
			}
		}*/
		
		for (SimulatorObserver o: _observerList) {
			o.onBodyAdded(_bodies, b);
		}
		
	}
	
	public void advance() {
		_gravityLaws.apply(_bodies);
		for (Body b: _bodies) {//int i = 0; i < _bodies.size(); i++) {	
			b.move(_dt);
		}
		_time += _dt;
		
		for (SimulatorObserver o: _observerList) {
			o.onAdvance(_bodies, _time);
		}
		
	}
	
	public String toString() {
		String simulatedStep = "";
		simulatedStep += "{ \"time\": " + _time + ", ";
		simulatedStep += "\"bodies\": [ ";
		for (int i = 0; i < _bodies.size(); i++){
			simulatedStep += _bodies.get(i).toString();
			
			if (i == _bodies.size() - 1) {
				simulatedStep += " ] }";
			}
			else {
				simulatedStep += ", ";
			}
		}
		
		return simulatedStep;
	}
	
	public void addObserver(SimulatorObserver o) {
		if (!_observerList.contains(o)) {
			o.onRegister(_bodies, _time, _dt, _gravityLaws.getDescription());
			_observerList.add(o);
		}
	}
	
	public void reset() {
		_time = 0;
		_bodies = new ArrayList<>(); 
		for (SimulatorObserver o: _observerList) {
			o.onReset(_bodies, _time, _dt, _gravityLaws.getDescription());
		}
	}
	
	public void setDeltaTime(double dt) {
		_dt = dt;
		for (SimulatorObserver o: _observerList) {
			o.onDeltaTimeChanged(dt);
		}
	}
	
	public void setGravityLaws(GravityLaws gravityLaws) {
		_gravityLaws = gravityLaws;
		for (SimulatorObserver o: _observerList) {
			o.onGravityLawChanged(_gravityLaws.getDescription());
		}
	}
	
}
