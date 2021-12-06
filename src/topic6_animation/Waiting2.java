package topic6_animation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;


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
		
		// TODO
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 300);
	}
	
}
