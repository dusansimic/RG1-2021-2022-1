package topic5_procedural_generation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;


public class IFSTree implements Drawing {

	Vector l = new Vector(0, 200);
	
	@GadgetInteger(min = 0)
	int nLevels = 12;
	
	@GadgetDouble(min = -1, max = 1)
	double k1 = 0.66;
	
	@GadgetDouble(min = -0.5, max = 0.5)
	double phi1 = 0.09;
	
	@GadgetDouble(min = -1, max = 1)
	double k2 = 0.72;
	
	@GadgetDouble(min = -0.5, max = 0.5)
	double phi2 = -0.19;
	
	Transformation[] transformations;
	
	
	
	void drawTrunk(View view, int level) {
		view.setLineWidth(30);
		view.setStroke(Color.hsb(110, 1 - Math.pow(0.88, level), 1.0));
		view.strokeLine(Vector.ZERO, l);
	}

	
	void drawTree(View view, int level) {
		if (level >= nLevels) {
			return;
		}
		
		for (Transformation transformation : transformations) {
			view.stateStore();
			view.addTransformation(transformation);
			drawTree(view, level + 1);
			view.stateRestore();
		}
		
		drawTrunk(view, level);
	}
	
	
	@Override
	public void draw(View view) {
		view.stateStore();

		view.setTransformation(Transformation.translation(new Vector(0, -300)));
		DrawingUtils.clear(view, Color.gray(0.125));
		
		transformations = new Transformation[] {
			new Transformation().scale(k1).rotate(phi1).translate(l),
			new Transformation().scale(k2).rotate(phi2).translate(l),
		};
		
		drawTree(view, 0);
		
		view.stateRestore();
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(1200, 800);
	}
	
}
