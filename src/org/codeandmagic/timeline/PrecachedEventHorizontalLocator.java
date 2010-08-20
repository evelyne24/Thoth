package org.codeandmagic.timeline;

import java.util.Collection;

import org.codeandmagic.util.ArrayUtils;
import org.codeandmagic.util.FakeArrayFlatFloat;
import org.codeandmagic.util.FakeArrayLinkFloat;

import android.util.Log;

/**
 * {@link EventHorizontalLocator} which pre-calculates the position of all event
 * 
 * @author cristi
 * 
 */
public abstract class PrecachedEventHorizontalLocator implements EventHorizontalLocator, TimelineViewAware {

	protected float[] cache;

	public void timelineViewEventsChanged(final TimelineView timelineView, final Events events, final Collection<Event> added,
			final Collection<Event> removed) {
		if (cache != null || timelineView.getWidth() > 0) {
			reCache(timelineView);
		}
	}

	public void timelineViewSizeChanged(final TimelineView timelineView, final int w, final int h, final int oldw, final int oldh) {
		if (cache == null) {
			reCache(timelineView);
		}
	}

	public void timelineViewContructed(final TimelineView timelineView) {
	}

	public void timelineViewEventSelected(final TimelineView timelineView, final Event event, final float eventX,
			final float eventY) {
	}

	public float[] getCache() {
		return cache;
	}

	public void computeX(final TimelineRenderingContext context) {
		Log.d("Thoth", "Getting events in interval " + context.getViewStartX() + " - " + context.getViewEndX());
		final int[] pos = ArrayUtils.fuzzyIntervalSearch(cache, context.getViewStartX(), context.getViewEndX());
		if (pos == null) {
			context.setFirtEventIndex(-1);
			context.setLastEventIndex(-1);
			context.setEventsX(new FakeArrayFlatFloat(0, 0));
			return;
		}
		final int first = pos[0];
		final int last = pos[1];
		// set the visible events
		context.setFirtEventIndex(first);
		context.setLastEventIndex(last);
		// set the x position of the visible events
		context.setEventsX(new FakeArrayLinkFloat(cache, first, last));
	}

	public abstract void reCache(final TimelineView timelineView);
}
