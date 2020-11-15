package simulator.model;

import simulator.misc.Vector;

public class Body {
	
	protected String id;
	protected Vector position;
	protected Vector velocity;
	protected Vector acceleration;
	protected double mass;
	
	public Body(String id, Vector position, Vector velocity, Vector acceleration, double mass) {
		this.id = id;
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.mass = mass;
	}
	
	public String getId() {
		return id;
	}
	
	public double getMass() {
		return mass;
	}
	
	public Vector getPosition() {
		return new Vector(position);
	}
	
	public Vector getVelocity() {
		return new Vector(velocity);
	}
	
	public Vector getAcceleration() {
		return new Vector(acceleration);
	}
	
	void setVelocity(Vector velocity) {
		this.velocity = new Vector(velocity);
	}
	
	void setAcceleration(Vector acceleration) {
		this.acceleration = new Vector(acceleration);
	}
	
	void move(double deltaTime) {
		position = position.plus(velocity.scale(deltaTime)).plus(acceleration.scale(deltaTime*deltaTime*0.5));
		velocity = velocity.plus(acceleration.scale(deltaTime));
	}
	
	public String toString() {
		String simulatedBody = "";
		simulatedBody += "{  ";
		simulatedBody += ("\"id\": \"" + id + "\", ");
		simulatedBody += ("\"mass\": " + mass + ", ");
		simulatedBody += ("\"pos\": " + position + ", ");
		simulatedBody += ("\"vel\": " + velocity + ", ");
		simulatedBody += ("\"acc\": " + acceleration);
		simulatedBody += (" }");
		return simulatedBody;
	}
	
	
}
