package exercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class MyTarget implements Drawing {
	
	@GadgetInteger(min = 1, max = 10)
	Integer n = 5;

	@GadgetInteger(min = 100, max = 300)
	Integer r = 250;

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		view.setStroke(Color.BLACK);
		view.setLineWidth(4);
		for (int i = n; i > 0; i--) {
			view.setFill(i % 2 == 0 ? Color.WHITE : Color.RED);
			view.fillCircleCentered(Vector.ZERO, r * i/n);
			view.strokeCircleCentered(Vector.ZERO, r * i/n);
		}

		view.setLineWidth(12);
		view.setStroke(Color.WHITE);
		view.strokeLine(Vector.polar(r * 1/n * 0.6, 5.0/8), Vector.polar(r * 1/n * 0.6, 1.0/8));
		view.strokeLine(Vector.polar(r * 1/n * 0.6, -5.0/8), Vector.polar(r * 1/n * 0.6, -1.0/8));
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
