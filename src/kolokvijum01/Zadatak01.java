package kolokvijum01;

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
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class Zadatak01 implements Drawing {

	@GadgetBoolean
	boolean applyFilter = true;

	@GadgetColorPicker
	Color color = Color.hsb(240, 0.4, 1);

	@GadgetDouble(min = 0, max = 1)
	Double threshold = 0.5;

	private Image originalImage;

	public Image process(Image input) {
		final int w = (int) input.getWidth();
		final int h = (int) input.getHeight();
		
		WritableImage output = new WritableImage(w, h);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color c = pr.getColor(x, y);

				if (c.getBrightness() < threshold && y % 4 < 2) {
					pw.setColor(x, y, Color.BLACK);
				} else {
					double hue = color.getHue();
					double sat = color.getSaturation();
					double dy = 1.0 * y / h;
					pw.setColor(x, y, Color.hsb(hue, sat, 1 - dy));
				}
			}
		}

		return output;
	}

	@Override
	public void init(View view) {
		originalImage = new Image("images/fall.jpg");
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));

		Image filteredImage = process(originalImage);
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : originalImage);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}

}
