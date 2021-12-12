package exercises;

import org.checkerframework.checker.units.qual.h;

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

		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				Color c = pr.getColor(i, j);
				Color current = c;
				if (i >= w/2) {
					pw.setColor(i, j, pr.getColor(i - w/2, j));
					current = pr.getColor(i - w/2, j);
					pw.setColor(i - w/2, j, c);
				}

				int dx = i % 64;
				int dy = j % 64;
				if ((dx < 32 && dy < 32) || (dx >= 32 && dy >= 32)) {
					double hue = current.getHue();
					double b = current.getBrightness();

					pw.setColor(i, j, Color.hsb(hue, k, b));
				}
			}
		}

		return output;
	}

}

public class MySwapLightenCheckerboard implements Drawing {
	
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
		DrawingApplication.launch(600, 600);
	}
	
}
