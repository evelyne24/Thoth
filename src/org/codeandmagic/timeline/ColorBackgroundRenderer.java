package org.codeandmagic.timeline;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * {@link BackgroundRenderer} that draws a solid color onto the canvas.
 * 
 * @author cristi
 * 
 */
public class ColorBackgroundRenderer implements BackgroundRenderer {

	private int color;

	public ColorBackgroundRenderer() {
		// set defaults
		setColor(Color.LTGRAY);
	}

	public int getColor() {
		return color;
	}

	public void setColor(final int color) {
		this.color = color;
	}

	public void renderBackground(final Canvas canvas, final TimelineRenderingContext context) {
		canvas.drawColor(Color.DKGRAY);
	}

}
