package org.codeandmagic.timeline;

import android.graphics.Canvas;
import android.graphics.Color;

public class ColorBackgroundRenderer implements BackgroundRenderer {
	
	private int color;
	
	public ColorBackgroundRenderer(int color){
		setColor(color);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void renderBackground(Canvas canvas, TimelineRenderingContext context) {
		canvas.drawColor(Color.DKGRAY);
	}

}
