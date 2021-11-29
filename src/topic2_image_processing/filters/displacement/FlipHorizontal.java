package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;

/**
 * Obrce sliku po x-osi.
 */
public class FlipHorizontal extends DisplacementFilter {
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		return new Vector(dst.x, dim.y - dst.y);
	}
	
}
