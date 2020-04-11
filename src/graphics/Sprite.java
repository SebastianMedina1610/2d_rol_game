package graphics;

public final class Sprite {
	private final int side;

	private int x;
	private int y;

	public int[] pixel;
	private final SpriteSheet sheet;

	public Sprite(final int side, final int col, final int row, final SpriteSheet sheet) {
		this.side = side;

		pixel = new int[side * side];

		this.x = col * side;
		this.y = row * side;
		this.sheet = sheet;

		for (int y = 0; y < side; y++) {
			for (int x = 0; x < side; x++) {
				pixel[x + y * side] = sheet.pixel[(x + this.x) + (y + this.y) * sheet.getWidth()];
			}
		}
	}
}