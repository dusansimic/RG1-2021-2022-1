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

public class Zadatak2 implements Drawing {

	@GadgetAnimation
	double time;

	Vector pt0 = new Vector(-250, 0);
	Vector pt1 = new Vector(120, 290);
	Vector pt2 = new Vector(-25, -130);
	Vector pt3 = new Vector(250, 0);

	int grain = (int) (Math.abs(pt0.x) + Math.abs(pt3.x));

	private void drawTerrain(View view) {
		view.beginPath();
		view.moveTo(pt0);
		view.bezierCurveTo(pt1, pt2, pt3);

		view.setStroke(Color.WHITESMOKE);
		view.stroke();
	}

	private void drawCar(View view) {
		view.setFill(Color.BLUE);
		view.fillRect(new Vector(-20, 0), new Vector(40, 10));
		view.fillRect(new Vector(-10, 10), new Vector(20, 10));
		view.setFill(Color.RED);
		view.fillCircleCentered(new Vector(-10, 0), 5);
		view.fillCircleCentered(new Vector(10, 0), 5);
	}

	private Vector interp(double time) {
		double u = grain * time / grain;
		Vector p01 = Vector.lerp(pt0, pt1, u);
		Vector p12 = Vector.lerp(pt1, pt2, u);
		Vector p23 = Vector.lerp(pt2, pt3, u);
		
		Vector p012 = Vector.lerp(p01, p12, u);
		Vector p123 = Vector.lerp(p12, p23, u);
		
		Vector p0123 = Vector.lerp(p012, p123, u);

		return p0123;
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));

		drawTerrain(view);

		Vector current = interp(Numeric.mod1(time));
		Vector next = interp(Numeric.mod1(time + 0.001));

		Vector toNext = current.mul(-1).add(next);

		view.stateStore();
		view.addTransformation(Transformation.rotation(toNext.angle()).translate(current.add(new Vector(0, 5))));
		drawCar(view);
		view.stateRestore();
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
