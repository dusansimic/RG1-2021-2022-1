package topic5_procedural_generation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;


public class IFSPoly implements Drawing {
	
	@GadgetInteger(min = 0)
	int nLevels = 5;
	
	@GadgetInteger(min = 1)
	int n = 5;
	
	double r = 100;
	
	
	
	private void drawSymbol(View view, int level) {
		view.setStroke(Color.hsb(0, 1, Math.pow(0.8, level)));
		view.setLineWidth(10);
		view.strokeCircle(Vector.ZERO, r);

		for (int i = 0; i < n; i++) {
			double firstAngle = i * 1.0 / n;
			double secondAngle = ((i + Math.floor(n / 2)) % n) / n;

			Vector firstPoint = Vector.polar(r, firstAngle + 0.25);
			Vector secondPoint = Vector.polar(r, secondAngle + 0.25);

			view.setLineCap(StrokeLineCap.BUTT);
			view.strokeLine(firstPoint, secondPoint);
		}

	}
	
	
	private void drawIFS(View view, int level) {
		if (level == nLevels)
			return;

		for (int i = 0; i < n; i++) {
			view.stateStore();

			Vector t = Vector.polar(2*r, i * 1.0 / n + 0.25);
			Transformation trans = Transformation.scaling(-0.5).translate(t);
			view.addTransformation(trans);

			drawIFS(view, level + 1);

			view.stateRestore();
		}
		
		drawSymbol(view, level);
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0));
		drawIFS(view, 0);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
