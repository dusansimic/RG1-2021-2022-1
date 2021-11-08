package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;


public class SemaphoreSign implements Drawing {
	
	private double a = 200;
	
	private double r = 32; // Poluprecnik svetla
	private double d = 12; // Vertikalni razmak izmedju svetla

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));
		
		// Kvadrat
		
		// Ivica kvadrata
				
		// Pravougaonik
		
		// Crveno svetlo

		// Žuto svetlo
		
		// Zeleno svetlo
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
