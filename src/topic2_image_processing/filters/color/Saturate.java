package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Cini boje na slici zasicenijim u zavisnosti od parametra zadatog u konstruktoru.
 */
public class Saturate extends ColorFilter {
	final double k;
	
	
	public Saturate(double k) {
		this.k = k;
	}


	@Override
	public Color processColor(Color input) {
		double h = input.getHue();
		double s = input.getSaturation();
		double b = input.getBrightness();

		return Color.hsb(h, s + (1 - s) * this.k, b);
	}
	
}	
