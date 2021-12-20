package topic8_simulation.bouncy_boxes;

import javafx.scene.paint.Color;
import mars.geometry.Vector;


public class Box_Ticks extends Box {

	static final double intervalTick = 0.001;
	
	double t;
	Vector p, v;
	Color color;
	Vector areaD;

	
	
	public Box_Ticks(Vector areaD, Vector p, Vector v, Color color) {
		this.areaD = areaD;
		this.p = p;
		this.v = v;
		this.color = color;
		
		this.t = 0;
	}		

	
	// TODO
	
	
	@Override
	public Vector getPosition(double time) {
		return p;
	}
	
	
	@Override
	public Vector getVelocity(double time) {
		return v;
	}


	@Override
	public Color getColor() {
		return color;
	}

}