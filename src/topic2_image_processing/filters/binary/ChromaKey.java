package topic2_image_processing.filters.binary;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import topic2_image_processing.filters.BinaryFilter;

/**
 * Menja piksele prve slike koji imaju hue koji je blizak zadatom pikselu, sa pikselima iz druge slike.
 * Ostale piksele ostavlja u originalnoj boji. 
 */
public class ChromaKey extends BinaryFilter {
	final double hue;            // Koji hue menjamo
	final double delta;     // Koliko odstupanje dozvoljavamo


	public ChromaKey(double hue, double delta) {
		this.hue = hue;
		this.delta = delta;
	}



	@Override
	public Image process(Image input1, Image input2) {
		final int w = (int) input1.getWidth();
		final int h = (int) input1.getHeight();
		
		if (input2.getWidth() != w || input2.getHeight() != h) {
			throw new IllegalArgumentException("Input images must have the same size.");
		}

		WritableImage output = new WritableImage(w, h);
		
		PixelReader pr1 = input1.getPixelReader();
		PixelReader pr2 = input2.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color c1 = pr1.getColor(x, y);
				Color c2 = pr2.getColor(x, y);

				double d = Math.abs(c1.getHue() - this.hue);
				if (d > 180) d = 360 - d;

				pw.setColor(x, y, d > this.delta ? c1 : c2);
			}
		}
		
		return output;
	}
	
}
