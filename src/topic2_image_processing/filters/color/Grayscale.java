package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Filter koji sliku pretvara u "crno-belu" sliku, odnosno sliku iscrtanu samo nijansama sive. Radi tako sto racuna
 * prosecnu vrednost tri komponente (r, g, b), i vraca boju koja odgovara nijansi sive svetline jednake tom proseku.
 */
public class Grayscale extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		return input;
	}
	
}	
