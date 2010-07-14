package org.codeandmagic.timeline;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Interface for class responsible for providing a {@link Bitmap} for an
 *  {@link Event} representing the icon being placed on the timeline.
 * @author cristi
 *
 */
public interface EventIconRenderer {
	/**
	 * Gets the {@link Bitmap} representing the passed event
	 * @param event
	 * @return
	 */
	public Bitmap getIconForEvent(Event event);
	/**
	 * Pre-loads the {@link Bitmap}s for all {@link Event} types
	 * @param context
	 */
	public void preloadIcons(Context context);
}
