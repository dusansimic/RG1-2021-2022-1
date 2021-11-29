package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;

/**
 * Obrce sliku po y-osi.
 */
public class FlipVertical extends DisplacementFilter {
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		return new Vector(dim.x - dst.x, dst.y);
	}
	
}
