package kolokvijum02;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class Zadatak1 implements Drawing {
	
	@GadgetAnimation
	double time;

	double doorWidth = 100;
	double doorHeight = 200;

	double rHead = 100.0;
	double xEye = 35.0;
	double yEye = 35.0;
	double rEye = 15.0;
	double rMouth = 65.0;
	double phiMouth = 1.0/3;

	double dt0 = 1; // Otvaranje prvih vrata
	double dt1 = 1; // Otvaranje drugih vrata
	double dt2 = 1; // Dolazak smajlija

	double time0 = 0;
	double time1 = time0 + dt0;
	double time2 = time1 + dt1;
	double time3 = time2 + dt2;
	
	double smoothStep(double x) {
		return x * x * (3 - 2 * x);
	}
	
	double bounceOut(double x) {
		if        (x < 1.00 / 2.75) {
			return 7.5625 * x * x;
		} else if (x < 2.00 / 2.75) {
			return 7.5625 * (x -= (1.5   / 2.75)) * x + 0.75;
		} else if (x < 2.50 / 2.75) {
			return 7.5625 * (x -= (2.25  / 2.75)) * x + 0.9375;
		} else {
			return 7.5625 * (x -= (2.625 / 2.75)) * x + 0.984375;
		}
	}
	
	double easeOutBack(double x) {
		return 1 - ((x = 1 - x) * x * (2.70158 * x - 1.70158));
	}

	private void firstDoor(View view, double time) {
		view.setFill(Color.RED);
		view.setStroke(Color.RED.darker());
		view.setLineWidth(5);

		double x = 1 - time;

		Vector pl = new Vector(-doorWidth, -(doorHeight / 2));
		Vector pr = new Vector(doorWidth, -(doorHeight / 2));
		Vector dl = new Vector(doorWidth * x, doorHeight);
		Vector dr = new Vector(-doorWidth * x, doorHeight);

		view.fillRect(pl, dl);
		view.fillRect(pr, dr);

		view.strokeRect(pl, dl);
		view.strokeRect(pr, dr);
	}

	private double tFirstDoor(double t) {
		if (t < time0) return 0;
		if (t < time1) return t;
		return time1;
	}

	private void secondDoor(View view, double time) {
		view.setFill(Color.BLUE);
		view.setStroke(Color.BLUE.darker());
		view.setLineWidth(5);

		double x = 1 - time;

		Vector pt = new Vector(-doorWidth, doorHeight / 2);
		Vector pb = new Vector(-doorWidth, -(doorHeight / 2));
		Vector dt = new Vector(doorHeight, -doorWidth * x);
		Vector db = new Vector(doorHeight, doorWidth * x);

		view.fillRect(pt, dt);
		view.fillRect(pb, db);

		view.strokeRect(pt, dt);
		view.strokeRect(pb, db);
	}

	private double tSecondDoor(double t) {
		if (t < time1) return 0;
		if (t < time2) return t - time1;
		return time2 - time1;
	}

	private void smile(View view) {
		// Glava
		view.setFill(Color.hsb(60, 0.9, 0.9));
		view.fillCircleCentered(new Vector(0, 0), rHead);
		
		// Oci
		view.setFill(Color.hsb(0, 0, 0));
		view.fillCircleCentered(new Vector(-xEye, yEye), rEye);
		view.fillCircleCentered(new Vector(xEye, yEye), rEye);
		
		// Usta
		view.setLineWidth(15);
		view.setStroke(Color.hsb(0, 0, 0));
		view.strokeArcCentered(new Vector(0, 0), new Vector(rMouth), 0.75 - phiMouth/2, phiMouth);
	}

	private void drawSmile(View view, double time) {
		view.stateStore();

		view.addTransformation(Transformation.scaling(0.8).scale(1 * bounceOut(time)).rotate(easeOutBack(time)));
		smile(view);

		view.stateRestore();
	}

	private double tSmile(double time) {
		if (time < time2) return 0;
		if (time < time3) return (time - time2) / dt2;
		return 1;
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.125));

		drawSmile(view, tSmile(time));
		secondDoor(view, tSecondDoor(time));
		firstDoor(view, tFirstDoor(time));
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
