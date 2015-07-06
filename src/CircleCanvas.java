/**
 * Created by michal.ruzicka on 4/2/14.
 */

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class CircleCanvas extends ActiveCanvas {
	final protected Font smallFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
	protected Image backgroundImage;
	protected int centerDiameter;
	protected int currentColor;
	protected int color;
	protected String text;

	protected static void fillCenteredCircle(Graphics g, int w, int h, int d) {
		g.fillArc((w - d) / 2, (h - d) / 2, d, d, 0, 360);
	}

	protected Image getBackgroundImage() {
		int w = getWidth();
		int h = getHeight();

		if (backgroundImage == null || backgroundImage.getWidth() != w || backgroundImage.getHeight() != h) {
			backgroundImage = Image.createImage(w, h);

			Graphics g = backgroundImage.getGraphics();

			// erase the image background to black
			g.setColor(0x000000);
			g.fillRect(0, 0, w, h);

			centerDiameter = Math.min(w, h) - 10;

			// draw a white circle in the center of the image
			g.setColor(currentColor = 0xffffff);
			fillCenteredCircle(g, w, h, centerDiameter + 30);
		}

		int color = getColor();
		if (color != currentColor) {
			Graphics g = backgroundImage.getGraphics();

			// draw a circle of the desired color inside the white circle
			g.setColor(currentColor = color);
			fillCenteredCircle(g, w, h, centerDiameter);
		}

		return backgroundImage;
	}

	protected void paint(Graphics g) {
		// draw the background
		g.drawImage(getBackgroundImage(), 0, 0, Graphics.TOP | Graphics.LEFT);

		// draw the text (if any)
		if (getText() != null) {
			int w = getWidth();
			int h = getHeight();
			String header = getText();
			String body;
			int nl = header.indexOf('\n');

			if (nl >= 0) {
				body = header.substring(nl + 1);
				header = header.substring(0, nl);
			} else
				body = null;

			g.setColor(0x000000);
			g.setFont(smallFont);
			g.drawString(header, w / 2, h / 2, Graphics.BOTTOM | Graphics.HCENTER);
			if (body != null)
				g.drawString(body, w / 2, h / 2, Graphics.TOP | Graphics.HCENTER);
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		if (color != this.color) {
			this.color = color;
			repaint();
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (!Utils.safeEquals(text, this.text)) {
			this.text = text;
			repaint();
		}
	}
}