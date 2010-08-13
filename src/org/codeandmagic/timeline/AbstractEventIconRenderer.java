package org.codeandmagic.timeline;

import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Abstract implementation for {@link EventIconRenderer} that draws an image for the event and optionally a text with the name of
 * the event. This class performs the actual rendering and collision detection, delegating to subclasses only the defining of
 * image resources and matching event types to specific images. <br/>
 * For performance reasons this class preloads all the images by implementing
 * {@link TimelineViewAware#timelineViewContructed(TimelineView)}.
 * 
 * @author cristi
 * 
 */
public abstract class AbstractEventIconRenderer extends StaticEventIconRenderer {
	/**
	 * Bitmaps necessary for drawing icons
	 */
	private Bitmap[] icons;
	private final int iconWidth;
	private final int iconHeight;

	public AbstractEventIconRenderer(final int iconWidth, final int iconHeight) {
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	private void loadIcon(final int key, final Drawable drawable) {
		icons[key] = loadBitmap(drawable, iconWidth, iconHeight);
	}

	@Override
	public void preloadIcons(final Context context) {
		final Resources res = context.getResources();
		final Map<Integer, Integer> keys = getAllDrawables();
		icons = new Bitmap[keys.size()];
		for (final Map.Entry<Integer, Integer> entry : keys.entrySet()) {
			loadIcon(entry.getKey(), res.getDrawable(entry.getValue()));
		}
	}

	public abstract int getKeyForEvent(Event event);

	public abstract Map<Integer, Integer> getAllDrawables();

}
