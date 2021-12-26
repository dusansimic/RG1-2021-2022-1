package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class Triangle implements Drawing {
	
	@GadgetAnimation
	double time = 0.0;

	@GadgetVector
	Vector p0 = new Vector(-200, -200);

	@GadgetVector
	Vector p1 = new Vector(300, -100);
	
	@GadgetVector
	Vector p2 = new Vector(100, 200);
	
	@GadgetDouble(min = 0, max = 1000)
	double speed = 300;                                 // Brzina kretanja loptice (u pikselima po sekundi).
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setLineJoin(StrokeLineJoin.ROUND);         // Spojevi linija su zaobljeni.
		view.setStroke(Color.DODGERBLUE);
		view.setLineWidth(4);
		view.strokePolygon(p0, p1, p2);
		
		double l0 = p0.distanceTo(p1);
		double l1 = p1.distanceTo(p2);
		double l2 = p2.distanceTo(p0);
		double l = l0 + l1 + l2;

		double dt0 = l0 / l;
		double dt1 = l1 / l;
		double dt2 = l2 / l;

		double s = speed * time;
		double t = Numeric.mod(s / l, 1);

		double t0 = 0;
		double t1 = t0 + dt0;
		double t2 = t1 + dt1;

		Vector p;
		Vector k;
		double keof;
		if (t < t1) {
			p = p0;
			k = p1;
			keof = (t - t0) / dt0;
		} else if (t < t2) {
			p = p1;
			k = p2;
			keof = (t - t1) / dt1;
		} else {
			p = p2;
			k = p0;
			keof = (t - t2) / dt2;
		}

		Vector krug = Vector.lerp(p, k, keof);
		view.setFill(Color.ORANGERED);
		view.fillCircleCentered(krug, 16);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
