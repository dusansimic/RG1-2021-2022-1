package topic5_procedural_generation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class IFSPoly implements Drawing {
	
	@GadgetInteger(min = 0)
	int nLevels = 5;
	
	@GadgetInteger(min = 1)
	int n = 5;
	
	double r = 100;
	
	
	
	private void drawSymbol(View view, int level) {
		// TODO
		view.setStroke(Color.hsb(0, 1, 1));
		view.strokeCircle(Vector.ZERO, r);
	}
	
	
	private void drawIFS(View view, int level) {
		// TODO
		
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
