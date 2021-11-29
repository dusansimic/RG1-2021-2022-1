package topic2_image_processing;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetImageChooser;
import mars.geometry.Vector;
import topic2_image_processing.filters.BinaryFilter;
import topic2_image_processing.filters.binary.ChromaKey;


public class DemoChromaKey implements Drawing {
	
	@GadgetImageChooser
	Image originalImage1, originalImage2;

	@GadgetDouble(min = 0, max = 360)
	double hue = 120;

	@GadgetDouble(min = 0, max = 50)
	double delta = 20;
	
	
	@Override
	public void init(View view) {
		originalImage1 = new Image("images/meterologist.jpg");
		originalImage2 = new Image("images/forecast.jpg");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		BinaryFilter filter = new ChromaKey(hue, delta);
		try {
			Image filteredImage = filter.process(originalImage1, originalImage2);
			view.drawImageCentered(Vector.ZERO, filteredImage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DrawingUtils.drawInfoText(view, e.toString());
		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(Options.redrawOnEvents());
	}
}
