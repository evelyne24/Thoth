package org.codeandmagic.timeline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Interface for class responsible with rendering the Event icons onto the timeline.
 * 
 * @author cristi
 * 
 */
public interface EventIconRenderer {
	/**
	 * Pre-loads the {@link Bitmap}s for all {@link Event} types
	 * 
	 * @param context
	 */
	public void preloadIcons(Context context);

	public void renderIcons(Canvas canvas, TimelineRenderingContext renderingContext);
}
