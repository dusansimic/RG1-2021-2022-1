package topic7_bezier_curves;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class Flower implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	Vector o  = new Vector( 0, -200);  // Koren cveta
	
	// levi list
	Vector ll1 = new Vector(-150, -100);
	Vector ll2 = new Vector(-100, 0);
	Vector l = new Vector(-150, 100);
	Vector lr1 = new Vector(-100, 50);
	Vector lr2 = new Vector(0, -100);
	
	// stabljika
	Vector s1 = new Vector(0, 0); // kontrolna tacka 1
	Vector s2 = new Vector(100, 100); // kontrolna tacka 2


	// desni list
	Vector rl1 = new Vector(0, -100);
	Vector rl2 = new Vector(200, -100);
	Vector r = new Vector(250, 0);
	Vector rr1 = new Vector(250, -100);
	Vector rr2 = new Vector(100, -200);

	
	// cvet
	@GadgetDouble(min = 0, max = 100)
	double rDisk  = 35;                   // poluprecnik
	
	@GadgetDouble(min = 0, max = 200)
	double lPetal = 100;                  // duzina latice
	
	@GadgetInteger
	int nPetals = 5;                      // broj latica
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		Color green = Color.rgb(116, 156, 118);
		// levi list
		view.beginPath();
		view.moveTo(o);
		view.bezierCurveTo(ll1, ll2, l.add(Vector.polar(20, -0.3 * time)));
		view.bezierCurveTo(lr1, lr2, o);
		view.setFill(green);
		view.fill();


		// stabljika
		Vector c = s2.add(Vector.polar(30, 0.5 * time));
		view.beginPath();
		view.moveTo(o);
		view.quadraticCurveTo(s1, c);

		view.setLineWidth(12);
		view.setStroke(green);
		view.stroke();

		
		// desni list
		view.beginPath();
		view.moveTo(o);
		view.bezierCurveTo(rl1, rl2, r.add(Vector.polar(20, 0.3 * time)));
		view.bezierCurveTo(rr1, rr2, o);
		view.setFill(green);
		view.fill();



		// latice
		for (int i = 0; i < nPetals; i++) {
			Vector p1 = Vector.polar(rDisk, i * 1.0 / nPetals);
			Vector p2 = Vector.polar(rDisk + lPetal, i * 1.0 / nPetals);
			Vector p3 = Vector.polar(rDisk + lPetal, (i + 1) * 1.0 / nPetals);
			Vector p4 = Vector.polar(rDisk, (i + 1) * 1.0 / nPetals);
			view.beginPath();
			view.moveTo(c.add(p1));
			view.bezierCurveTo(c.add(p2), c.add(p3), c.add(p4));
			view.setFill(Color.rgb(156, 116, 153));
			view.fill();
		}
		

		// centar cveta
		view.setFill(Color.rgb(154, 156, 116));
		view.fillCircleCentered(c, rDisk);

	}
	
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
