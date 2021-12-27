package kolokvijum02_2020;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;

public class Zadatak1 implements Drawing {

	@GadgetInteger(min = 0)
	Integer nLevels = 1;

	private void drawRect(View view) {
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineWidth(10);
		view.setStroke(Color.GREEN);
		view.setFill(Color.GREEN.darker());

		view.fillRect(Vector.ZERO, new Vector(200, 50));
		view.beginPath();
		view.moveTo(Vector.ZERO);
		view.quadraticCurveTo(new Vector(300, 25), new Vector(0, 50));
		view.stroke();
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0));

		for (int i = 0; i < nLevels; i++) {
			view.stateStore();

			double d = 0;
			for (int j = 0; j < i; j++) {
				d += 50 * Math.pow(0.9, j);
			}

			double k = Math.pow(0.9, i);

			Vector shift = new Vector(0, -350);
			Vector t = new Vector(0, d);
			view.addTransformation(Transformation.scaling(Math.pow(-1, i % 2) * k, k).translate(shift).translate(t));
			drawRect(view);

			view.stateRestore();
		}
	}

	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
