package topic1_colors_and_bitmaps;


import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;

public class ColorsAndBitmaps implements Drawing {

	public Image imgSolidColor() {
		// Svi pikseli su ljubicasti.
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.PURPLE);
			}
		}
		
		return image;
	}

	
	public Image imgLinearGradient() {
		// Linearni gradijent po x-osi od crne do plave.  
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, new Color(0, 0, x / image.getWidth(), 1));
			}
		}
		
		return image;
	}
	
	
	public Image imgLinearGradient2() {
		// Linearni gradijent crvene po x-osi i zelene po y-osi.  
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, new Color(x / image.getWidth(), y / image.getHeight(), 0, 1));
			}
		}
		
		return image;
	}
	
	

	public Image imgRadialGradient() {
		// Radijalni gradijent sive.
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1; // 0-1   0-2   -1-1
				double dy = (2.0 * y / h) - 1;

				double d = Math.sqrt(dx*dx + dy*dy);
				if (d > 1) d = 1;

				// pw.setColor(x, y, new Color(1-d, 1-d, 1-d, 1));
				pw.setColor(x, y, Color.gray(1-d));
			}
		}
		
		return image;
	}


	public Image imgRadialGradientOpacity() {
		// Radijalni gradijent providnosti.
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1; // 0-1   0-2   -1-1
				double dy = (2.0 * y / h) - 1;

				double d = Math.sqrt(dx*dx + dy*dy);
				if (d > 1) d = 1;

				pw.setColor(x, y, new Color(1, 1, 1, 1-d));
			}
		}
		
		return image;
	}

	
	public Image imgWave() {
		// Intenzitet boje je talasne funkcije po x osi. 
		
		WritableImage image = new WritableImage(500, 200);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double a = (Math.cos(2 * Math.PI * (x + 50) / 100) + 1) / 2;
				pw.setColor(x, y, Color.gray(a));
			}
		}
		
		return image;
	}

	
	public Image imgFixedHue() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(0, x / image.getWidth(), y / image.getHeight()));
			}
		}
		
		return image;
	}

	
	public Image imgFixedSaturation() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(x / image.getWidth() * 360, 1, y / image.getHeight()));
			}
		}
		
		return image;
	}
	
	
	public Image imgFixedBrightness() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(x / image.getWidth() * 360, y / image.getHeight(), 1));
			}
		}
		
		return image;
	}
	
	
	public Image imgDisk1() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;
				double dy = (2.0 * y / h) - 1;

				double d = Math.sqrt(dx*dx + dy*dy);
				// Math.atan2 ce izracunati ugao naspram y ose i nalegao na x osu, tj. ugao za hue
				// Posto se vraca rezultat u radijanima od -pi do pi, potrebno je da ga prebacimo u
				// stepene i tu se onda rezultat deli sa 2pi. Onda se dobija vrednost od -1/2 do 1/2
				// i da bi se dobio ugao pomnozi se sa 360.
				// Ukoliko je potrebno nekoako drugacije orijentisati disk, preporuka je da se smao
				// pomera interval sabiranjem konstantni na rezultat kolicnika.
				double alpha = Math.atan2(dy, dx) / (2 * Math.PI) * 360;

				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(alpha, 1, 1));
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgDisk2() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;
				double dy = (2.0 * y / h) - 1;

				double d = Math.sqrt(dx*dx + dy*dy);
				double alpha = Math.atan2(dy, dx) / (2 * Math.PI) * 360;

				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(alpha, 1, d));
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgDisk3() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;
				double dy = (2.0 * y / h) - 1;

				double d = Math.sqrt(dx*dx + dy*dy);
				double alpha = Math.atan2(dy, dx) / (2 * Math.PI) * 360;

				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(alpha, d, 1));
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgRainbow() {
		int w = 500;
		int h = 500;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		double r0 = 0.5;
		double r1 = 0.75;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;
				double dy = (2.0 * y / h) - 1;

				double d = Math.sqrt(dx*dx + dy*dy);

				if (dy > 0) {
					pw.setColor(x, y, Color.hsb(120, 1, dy / 2 + 0.25));
				} else {
					if (d >= r0 && d <= r1) {
						double c = 1 - (d - r0) * 4;
						pw.setColor(x, y, Color.hsb(-20 + c * 340, 1, 1));
					} else {
						pw.setColor(x, y, Color.hsb(240, 1 + dy, 1));
					}
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgTablecloth() {
		// Stolnjak u hipsterskim kafanama
		
		int w = 410;
		int h = 410;
		
		int d = 10;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int xx = x % (d * 2);
				int yy = y % (d * 2);
				if (xx < 10) {
					if (yy < 10) pw.setColor(x, y, Color.RED);
					else pw.setColor(x, y, (xx + yy) % 2 == 0 ? Color.RED : Color.WHITE);
				} else {
					if (yy < 10) pw.setColor(x, y, (xx + yy) % 2 == 0 ? Color.WHITE : Color.RED);
					else pw.setColor(x, y, Color.WHITE);
				}
			}
		}
		
		return image;
	}

	
	// ============================================================================================
	
	
	Image[] images;
	

	@Override
	public void init(View view) {
		images = new Image[] {
			imgSolidColor(),
			imgLinearGradient(),
			imgLinearGradient2(),
			imgRadialGradient(),
			imgRadialGradientOpacity(),
			imgFixedHue(),
			imgFixedSaturation(),
			imgFixedBrightness(),
			imgDisk1(),
			imgDisk2(),
			imgDisk3(),
			imgRainbow(),
			imgWave(),
			imgTablecloth()
		};
		
		view.setImageSmoothing(false);
	}
	
	
	@GadgetColorPicker
	Color colorBackground = new Color(0.2, 0.2, 0.2, 1);
	
	@GadgetInteger(min = 0, max = 13)
	int imageIndex = 0;


	CameraSimple camera = new CameraSimple();
	
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		
		DrawingUtils.clear(view, colorBackground);
		view.drawImageCentered(Vector.ZERO, images[imageIndex]);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}


	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
	}
	
}
