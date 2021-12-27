package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class MorphingAndKeyframes implements Drawing {

	final Vector fieldP = new Vector(-400, -300);    // Donje levo teme pravougaonika u kome se svi poligoni nalaze.
	final Vector fieldD = new Vector( 800,  600);    // Dijagonala pravougaonika u kome se svi poligoni nalaz.
	
	final int maxNVertices = 100;                    // Maksimalan broj temena poligona.
	final int maxNKeyframes = 100;                   // Maksimalan broj keyframe-ova.

	final double stateDuration = 1.0;                // Trajanje jednog stanja (vreme izmedju dva keyframe-a).
	
	Vector[][] polygons;                             // polygons[k][i] = i-ti cvor k-tog poligona
	double[] hues;                                   // hues[k] = hue k-tog poligona

	

	@GadgetAnimation(start = true)
	double time = 0.0;

	@GadgetBoolean
	boolean showKeyframes = true;

	@GadgetInteger(min = 3, max = maxNVertices)
	int nVertices = 3;
	
	@GadgetInteger(min = 1, max = maxNKeyframes)
	int nStates = 3;
	

	
	double smootherstep(double x) {
		// 6x^5 - 15x^4 + 10x^3
		return x * x * x * (x * (x * 6 - 15) + 10);
	}

	
	@Override
	public void init(View view) {
		// Generisemo poligone na slucajan nacin unutar zadatih granica.

		polygons = new Vector[maxNKeyframes][maxNVertices];
		hues = new double[maxNKeyframes];

		for (int i = 0; i < maxNKeyframes; i++) {
			for (int j = 0; j < maxNVertices; j++) {
				polygons[i][j] = Vector.randomInBox(fieldP, fieldD);
			}

			hues[i] = 360 * Math.random();
		}
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));
	
		if (showKeyframes) {
			view.setLineDashes(4);
			view.setLineCap(StrokeLineCap.BUTT);
			view.setLineJoin(StrokeLineJoin.ROUND);
			view.setLineWidth(1);
			view.setStroke(Color.hsb(0, 0, 1, 0.2));
			
			for (int i = 0; i < nStates; i++) {
				view.strokePolygon(nVertices, polygons[i]);
			}
		}

		int current = (int) (time / stateDuration) % nStates;
		int next = (current + 1) % nStates;

		double t = (time % stateDuration) / stateDuration; // skalira se na [0, 1) posto nam to treba za interpolaciju
		t = smootherstep(t);

		Vector[] polygon = new Vector[nVertices];
		for (int i = 0; i < nVertices; i++) {
			polygon[i] = Vector.lerp(polygons[current][i], polygons[next][i], t);
		}

		Color currentColor = Color.hsb(hues[current], 1, 1);
		Color nextColor = Color.hsb(hues[next], 1, 1);

		view.setFill(currentColor.interpolate(nextColor, t));
		view.fillPolygon(polygon);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}
