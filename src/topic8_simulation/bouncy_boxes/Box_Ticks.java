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

	private void update() {
		if (p.x < 0 || p.x > areaD.x) {
			v = v.mul(new Vector(-1, 1));
		} else if (p.y < 0 || p.y > areaD.y) {
			v = v.mul(new Vector(1, -1));
		}

		p = p.add(v.mul(intervalTick));
		t += intervalTick;
	}
	
	private void updateTo(double time) {
		while (t + intervalTick <= time) {
			update();
		}
	}
	
	
	@Override
	public Vector getPosition(double time) {
		updateTo(time);
		return p;
	}
	
	
	@Override
	public Vector getVelocity(double time) {
		updateTo(time);
		return v;
	}


	@Override
	public Color getColor() {
		return color;
	}

}