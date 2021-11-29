package topic2_image_processing.filters.displacement;

import java.util.Random;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


/**
 * Svaki piksel rezultujuce slike se dobija kopiranjem nasumicno odabranog piksela iz njegove okoline.
 * Maksimalna udaljenost sa koje moze da se kopira je zadata kao parametar konstruktora.
 */
public class Jitter extends DisplacementFilter {
	final double r;
	Random random = new Random();
	
	
	public Jitter(double r) {
		this.r = r;
	}
	
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		double phi = random.nextDouble();
		double d = random.nextDouble() * this.r;

		return dst.add(Vector.polar(d, phi));
		// double alpha = Math.random() * 2 * Math.PI;
		// double d = random.nextDouble() * this.r;
		// double dx = Math.cos(alpha) * d;
		// double dy = Math.sin(alpha) * d;

		// return new Vector(dst.x + dx, dst.y + dy);
	}
	
}
