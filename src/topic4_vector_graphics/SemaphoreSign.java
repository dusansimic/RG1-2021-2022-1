package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class SemaphoreSign implements Drawing {
	
	private double a = 200;
	private Vector[] corners = new Vector[] {
		new Vector(a, 0),
		new Vector(0, a),
		new Vector(-a, 0),
		new Vector(0, -a),
	};
	
	private double r = 32; // Poluprecnik svetla
	private double d = 12; // Vertikalni razmak izmedju svetla

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));
		
		// Kvadrat
		view.setFill(Color.YELLOW);
		view.fillPolygon(corners);
		
		// Ivica kvadrata
		view.setStroke(Color.BLACK);
		view.setLineWidth(20);
		view.strokePolygon(corners);
				
		// Pravougaonik
		view.setFill(Color.BLACK);
		view.fillRectCentered(Vector.ZERO, new Vector(r + d, 3 * r + 2 * d));
		
		// Crveno svetlo
		Vector redCenter = new Vector(0, 2 * r + d);
		view.setFill(Color.RED);
		view.fillCircleCentered(redCenter, r);

		// Žuto svetlo
		Vector yellowCenter = Vector.ZERO;
		view.setFill(Color.YELLOW);
		view.fillCircleCentered(yellowCenter, r);
		
		// Zeleno svetlo
		Vector greenCenter = new Vector(0, - (2 * r + d));
		view.setFill(Color.GREEN);
		view.fillCircleCentered(greenCenter, r);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
