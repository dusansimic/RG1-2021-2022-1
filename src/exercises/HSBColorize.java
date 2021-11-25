package exercises;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.geometry.Vector;


public class HSBColorize implements Drawing {

	@GadgetBoolean
	boolean applyFilter = true;

	Image originalImage;
	

	
	public Image process(Image input) {
		final int w = (int) input.getWidth();
		final int h = (int) input.getHeight();
		
		WritableImage output = new WritableImage(w, h);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color outputColor;
				
				if ((x - y) % 16 == 0) {
					outputColor = Color.BLACK;
				} else {
					double dx = 1.0*x/w;             // x koordinata, normalizovana na interval [0,1).
					double dy = 1.0*y/h;             // y koordinata, normalizovana na interval [0,1).
					
					Color inputColor = pr.getColor(x, y);
					
					outputColor = Color.hsb(
							dy * 360,
							dx,
							inputColor.getBrightness(),
							inputColor.getOpacity()
							);
				}
				
				pw.setColor(x, y, outputColor);
			}
		}
		
		return output;
	}

	
	@Override
	public void init(View view) {
		originalImage = new Image("images/Mona Lisa.jpg");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));

		Image filteredImage = process(originalImage);
		view.setEffect(new DropShadow(60, Color.BLACK));
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : originalImage);
		view.setEffect(null);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 760);
	}
}
