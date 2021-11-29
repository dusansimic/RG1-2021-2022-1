package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class Building implements Drawing {
	
	@GadgetInteger(min = 1, max = 10)
	int n = 2;                           // Broj vrsta

	@GadgetInteger(min = 1, max = 10)
	int m = 3;                           // Broj kolona
	
	
	Vector w = new Vector(30, 20);  // Dimenzije prozora
	Vector g = new Vector(10, 10);  // Razmak izmedju prozora

	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		// Žuti zid
		double wallw = m * (w.x + g.x) + g.x;
		double wallh = n * (w.y + g.y) + g.y;
		view.setFill(Color.YELLOW);
		view.fillRectCentered(Vector.ZERO, new Vector(wallw / 2, wallh / 2));
		
		// Prozori
		view.setFill(Color.BLUE);
		Vector corner = new Vector(- (wallw / 2), - (wallh / 2));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Vector center = corner.add(new Vector(g.x + (g.x + w.x) * j, g.y + (g.y + w.y) * i)).add(new Vector(w.x / 2, w.y / 2));
				view.fillRectCentered(center, new Vector(w.x / 2, w.y / 2));
			}
		}
		
		// Krov
		view.setFill(Color.RED);
		Vector r = new Vector(wallw / 2, wallh / 2);
		view.fillPolygon(r.mul(new Vector(-1, 1)), r.mul(new Vector(1, 1)), new Vector(0, wallh / 2 + 40));

	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
