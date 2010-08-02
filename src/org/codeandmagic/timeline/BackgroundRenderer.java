package org.codeandmagic.timeline;

import android.graphics.Canvas;

/**
 * Interface for classes that are responsible with rendering the background for the current "frame". The background will be the
 * first layer to be drawn on the {@link TimelineView} {@link Canvas}.
 * 
 * @author cristi
 * 
 */
public interface BackgroundRenderer {
	public void renderBackground(Canvas canvas, TimelineRenderingContext context);
}
