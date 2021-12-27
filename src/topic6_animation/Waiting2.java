package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class Waiting2 implements Drawing {
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetDouble(min = 0, max = 100)
	double r = 30.0;
	
	@GadgetDouble(min = 0, max = 20)
	double width = 8.0;
	
	@GadgetDouble(min = -4, max = 4)
	double rotationalSpeed = 0.91;

	@GadgetDouble(min = 0, max = 4)
	double oscillationFrequency = 0.56;

	@GadgetDouble(min = 0, max = 1)
	double alpha = 0.84;
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));

		double angle = time * rotationalSpeed;
		double angleD = (1 + alpha * Numeric.sinT(angle)) * 0.5;

		double angleStart = angle - angleD/2;

		view.setStroke(Color.hsb(60 * time, 1, 1));
		view.setLineCap(StrokeLineCap.BUTT);
		view.setLineWidth(width);
		view.strokeArcCentered(Vector.ZERO, new Vector(r), angleStart, angleD);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 300);
	}
	
}
