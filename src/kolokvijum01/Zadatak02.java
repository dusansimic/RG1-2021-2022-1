package kolokvijum01;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Zadatak02 implements Drawing {

	@GadgetInteger
	Integer n = 5;

	int r = 200;

	Color dark = Color.hsb(240, 0.6, 0.4);
	Color light = Color.hsb(240, 0.6, 0.6);

	public void drawCircle(View view, int k) {
		if (k == 0) return;

		int rr = r * k/n;
		Vector center = Vector.ZERO.add(Vector.polar(r, 0.5)).add(Vector.polar(rr, 0));

		view.setFill((n-k) % 2 == 0 ? dark : light);
		view.fillCircleCentered(center, rr);
		
		view.setFill((n-k) % 2 == 0 ? light : dark);
		view.fillCircleCentered(center.add(Vector.polar(rr, 0)).add(Vector.polar(r/n, 0.5)), r/(2*n));

		drawCircle(view, k-1);
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		// Ovo je staro iterativno resenje

		// Vector center = Vector.ZERO;
		// for (int i = n; i >= 1; i--) {
		// 	view.setFill((n-i) % 2 == 0 ? dark : light);
		// 	int rr = r*i/n;
		// 	view.fillCircleCentered(center, rr);
		// 	center = center.add(Vector.polar(rr - r * (i-1)/n, 0.5));

		// 	view.setFill((n-i) % 2 == 0 ? light : dark);
		// 	view.fillCircleCentered(center.add(Vector.polar(rr, 0)), (r/n)/2);
		// }

		// Ovo je rekurzivno
		drawCircle(view, n);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
