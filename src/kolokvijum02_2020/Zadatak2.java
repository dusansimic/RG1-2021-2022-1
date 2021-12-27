package kolokvijum02_2020;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Zadatak2 implements Drawing {

	@GadgetAnimation
	Double time = 0.0;

	@GadgetDouble(min = 0, max = 400)
	Double r = 75.0;

	@GadgetDouble(min = 0, max = 400)
	Double rayLengthMin = 100.0;

	@GadgetDouble(min = 0, max = 400)
	Double rayLengthMax = 200.0;

	@GadgetInteger(min = 2)
	Integer nRaysDiv2 = 2;

	private void drawDisk(View view) {
		view.setFill(Color.YELLOW);
		view.fillCircleCentered(Vector.ZERO, r);
	}

	private void drawRay(View view, double angle, boolean odd) {
		// double k = Numeric.mod1(time);
		double k = (Math.sin(time * 4) + 1) / 2;
		if (odd) k = 1 - k;
		double l = (rayLengthMax - rayLengthMin) * k + rayLengthMin;
		Color color = Color.hsb(Color.YELLOW.getHue(), 1, 1, 0.2 + 0.8 * k);

		view.setStroke(color);
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineWidth(12);
		view.strokeLine(Vector.ZERO, Vector.polar(l, angle));
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0));

		drawDisk(view);
		for (int i = 0; i < nRaysDiv2; i++) {
			double angle = i * 1.0 / (nRaysDiv2 * 2);
			drawRay(view, angle, i % 2 != 0);
			drawRay(view, angle + 0.5, (i + nRaysDiv2) % 2 != 0);
		}
	}

	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
