package topic5_procedural_generation;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.C;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Box;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;


public class Cheese implements Drawing {
	
	Box box = Box.createCentered(Vector.ZERO, new Vector(500, 500));
	
	double border = 4.0;
	double gap = 6.0;
	
	List<Hole> holes;
	
	
	@Override
	public void init(View view) {
		generateHoles();
	}
	
	double getMaxRadius(Vector c) {
		double minR = holes.stream().mapToDouble(C -> c.distanceTo(C.c) - C.r).min().orElse(Double.POSITIVE_INFINITY);
		return Math.min(minR - gap, Hole.R_MAX);
	}
	
	
	Vector getCenter() {
		return Vector.randomInBox(box);
	}
	
	
	void generateHoles() {
		holes = new ArrayList<>();
		int nSuccesiveFails = 0;

		do {
			Vector c = getCenter();
			double r = getMaxRadius(c);

			if (r >= Hole.R_MIN) {
				holes.add(new Hole(c, r));
				nSuccesiveFails = 0;
			} else {
				nSuccesiveFails++;
			}
		} while (nSuccesiveFails < 10_000);
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(50, 0.9, 0.9));
		
		for (Hole hole : holes) {
			// Spoljni krug
			view.setFill(Color.hsb(50, 0.5, 1));
			view.fillCircleCentered(hole.c, hole.r);

			// Unutrašnji krug sa senkom
			view.setFill(Color.hsb(50, 1, 0.5));
			view.setEffect(new InnerShadow(20, 10, -10, Color.BLACK));
			view.fillCircleCentered(hole.c, hole.r - border);
			view.setEffect(null);

		}
	}

	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isKeyPress(KeyCode.ENTER)) {
			generateHoles();
		}
	}
	
	
	private static final class Hole {
		
		public static final double R_MIN = 8.0;
		public static final double R_MAX = 120.0;
		
		Vector c;
		double r;
		
		public Hole(Vector c, double r) {
			this.c = c;
			this.r = r;
		}	
	}	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}	
}
