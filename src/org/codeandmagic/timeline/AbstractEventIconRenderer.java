package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codeandmagic.util.FakeArrayFloat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
public abstract class AbstractEventIconRenderer implements EventIconRenderer, TimelineViewAware {
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

	private Bitmap loadBitmap(final Drawable drawable, final int width, final int height) {
		final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

	public void renderIcons(final Canvas canvas, final TimelineRenderingContext context) {
		final float startX = context.getViewStartX();
		final float endX = context.getViewEndX();

		final int startIndx = context.getFirtEventIndex();
		final int endIndx = context.getLastEventIndex();

		if (startIndx < 0 || endIndx < 0 || endIndx < startIndx)
			return;

		final List<Event> events = context.getTimeline().getEvents().getEvents();
		final FakeArrayFloat eventsX = context.getEventsX();
		final FakeArrayFloat eventsY = context.getEventsY();

		final int nrEvents = eventsX.size();

		for (int i = 0, j = startIndx; i < nrEvents; ++i, ++j) {
			final float x = eventsX.get(i);
			final float y = eventsY.get(i);
			final Event event = events.get(j);
			if (x > endX) {
				break;
			}
			renderIcon(canvas, event, x - startX, y);
		}
	}

	public void renderIcon(final Canvas canvas, final Event event, final float x, final float y) {
		final Bitmap b = getIconForEvent(event);
		canvas.drawBitmap(b, x, y, null);
	}

	public boolean isHit(final Event event, final float eventX, final float eventY, final float clickX, final float clickY) {
		return clickX > eventX && clickX < eventX + iconWidth && clickY > eventY && clickY < eventY + iconHeight;
	}

	public Bitmap getIconForEvent(final Event event) {
		return icons[getKeyForEvent(event)];
	}

	public void preloadIcons(final Context context) {
		final Resources res = context.getResources();
		final Map<Integer, Integer> keys = getAllDrawables();
		icons = new Bitmap[keys.size()];
		for (final Map.Entry<Integer, Integer> entry : keys.entrySet()) {
			loadIcon(entry.getKey(), res.getDrawable(entry.getValue()));
		}
	}

	public void timelineViewContructed(final TimelineView timelineView) {
		preloadIcons(timelineView.getContext());
	}

	public void timelineViewEventsChanged(final TimelineView timelineView, final Events events, final Collection<Event> added,
			final Collection<Event> removed) {
	}

	public void timelineViewSizeChanged(final TimelineView timelineView, final int w, final int h, final int oldw, final int oldh) {
	}

	public void timelineViewEventSelected(final TimelineView timelineView, final Event event, final float eventX,
			final float eventY) {
	}

	public abstract int getKeyForEvent(Event event);

	public abstract Map<Integer, Integer> getAllDrawables();

}
