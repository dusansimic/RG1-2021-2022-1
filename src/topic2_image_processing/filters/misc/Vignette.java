package topic2_image_processing.filters.misc;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import topic2_image_processing.filters.Filter;

/**
 * Pravi efekat utapanja slike u crnu pozadinu tako sto cini da slika postaje tamnija sto je piksel dalji od centra.
 */
public class Vignette extends Filter {

	@Override
	public Image process(Image input) {
		final int w = (int) input.getWidth();
		final int h = (int) input.getHeight();
		
		WritableImage output = new WritableImage(w, h);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = 2.0 * x / w - 1;
				double dy = 2.0 * y / h - 1;

				double d = Math.sqrt(dx*dx + dy*dy);
				if (d > 1) d = 1;

				Color c = pr.getColor(x, y);

				pw.setColor(x, y, Color.hsb(c.getHue(), c.getSaturation(), c.getBrightness() * (1 - d)));
			}
		}
		
		return output;
	}
	
}
