package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body {

	private double lossFactor;
	private double lossFrequency;
	private double frequencyCounter;
	
	public MassLossingBody(String id, Vector position, Vector velocity, Vector acceleration, double mass, double lossFactor, double lossFrequency) {
		super(id, position, velocity, acceleration, mass);
		frequencyCounter = 0;
		this.lossFrequency = lossFrequency;
		this.lossFactor = lossFactor;
	}
	
	void move(double time) {
		super.move(time);	
		frequencyCounter += time;
		if (frequencyCounter >= lossFrequency) {
			mass = mass*(1 - lossFactor);
			frequencyCounter = 0;
		}
		
		
	}

}
