package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private final int width;
	private final int height;

	public final int[] pixel;

	public SpriteSheet(final String route, final int width, final int height) {
		this.width = width;
		this.height = height;

		pixel = new int[width * height];

		BufferedImage image;
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(route));

			image.getRGB(0, 0, width, height, pixel, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getWidth() {

		return width;
	}
}
