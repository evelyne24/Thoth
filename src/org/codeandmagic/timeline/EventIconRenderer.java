package org.codeandmagic.timeline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Interface for class responsible with rendering the Event icons onto the timeline.
 * @author cristi
 *
 */
public interface EventIconRenderer {
	/**
	 * Renders the icon representation of the passed event onto the provided canvas
	 * @param event
	 * @param indx index for the passed event in the rendering context
	 * @param canvas
	 * @param context
	 */
	public void renderIcon(Event event, int indx, Canvas canvas, TimelineRenderingContext context);
	/**
	 * Pre-loads the {@link Bitmap}s for all {@link Event} types
	 * @param context
	 */
	public void preloadIcons(Context context);
}
