package exercises;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;
import topic2_image_processing.filters.Filter;


class FilterSwapLightenCheckerboard extends Filter {
	double k;
	
	
	public FilterSwapLightenCheckerboard(double k) {
		this.k = k;
	}

	@Override
	public Image process(Image input) {
		int w = (int) input.getWidth();
		int h = (int) input.getHeight();
		
		WritableImage output = new WritableImage(w, h);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int sy = 0; sy < h; sy++) {
			for (int sx = 0; sx < w; sx++) {
				int dy = sy;
				int dx = (sx + w/2) % w;

				Color inputColor = pr.getColor(sx, sy);
				Color outputColor; 

				if ((dx % 64 < 32) ^ (dy % 64 < 32)) {
					outputColor = inputColor;
				} else {
					outputColor = Color.hsb(
							inputColor.getHue(),
							(1-k) * inputColor.getSaturation(),
							k + (1-k) * inputColor.getBrightness(),
							inputColor.getOpacity()
					);
				}
				
				pw.setColor(dx, dy, outputColor);
			}
		}
		
		return output;
	}
		
}


public class SwapLightenCheckerboard implements Drawing {
	
	@GadgetDouble(min = 0, max = 1)
	double k = 0.5;
	
	@GadgetBoolean
	Boolean applyFilter = false;


	Image originalImage;
	
	@Override
	public void init(View view) {
		originalImage = new Image("images/skirts.jpg");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		Filter filter = new FilterSwapLightenCheckerboard(k);
		Image filteredImage = filter.process(originalImage);
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : originalImage);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(Options.redrawOnEvents());
	}
}
