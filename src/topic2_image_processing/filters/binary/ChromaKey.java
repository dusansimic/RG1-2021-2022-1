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
	final double delta = 20;     // Koliko odstupanje dozvoljavamo


	public ChromaKey(double hue) {
		this.hue = hue;
	}



	@Override
	public Image process(Image input1, Image input2) {
		// TODO
		return input1;
	}
	
}
