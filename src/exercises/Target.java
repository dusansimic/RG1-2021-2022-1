package exercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Target implements Drawing {
	
	@GadgetInteger(min = 1, max = 10)
	Integer n = 5;
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		double r = 250;
		
		view.setStroke(Color.BLACK);
		view.setLineWidth(3);
		
		for (int i = n-1; i >= 0; i--) {
			view.setFill(i % 2 == 0 ? Color.RED : Color.WHITE);
			view.fillCircleCentered(Vector.ZERO, r * (i+1)/n);
			view.strokeCircleCentered(Vector.ZERO, r * (i+1)/n);
		}
		
		view.setStroke(Color.WHITE);
		view.setLineWidth(12);
		double d = r/n * 0.4;
		view.strokeLine(new Vector(-d, -d), new Vector( d,  d));
		view.strokeLine(new Vector( d, -d), new Vector(-d,  d));
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
