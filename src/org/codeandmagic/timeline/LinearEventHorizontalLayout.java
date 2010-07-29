package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.List;

import org.codeandmagic.util.ArrayUtils;
import org.codeandmagic.util.FakeArrayFlatFloat;
import org.codeandmagic.util.FakeArrayLinkFloat;
import org.codeandmagic.util.TimeUtils;

import android.util.Log;

/**
 * {@link EventHorizontalLayout} which positions {@link Event}s linearly based on a provided scaling factor.
 * 
 * @author cristi
 * 
 */
public class LinearEventHorizontalLayout implements EventHorizontalLayout, TimelineViewAware {

	/**
	 * The scale factor represents how many time units (milliseconds) are compacted into one pixel
	 */
	private float scale;
	private float[] cache;

	public LinearEventHorizontalLayout() {
		setScale(TimeUtils.DAY * 3 / 600);
	}

	public float getScale() {
		return scale;
	}

	public void setScale(final float scale) {
		this.scale = scale;
	}

	public void reCache(final TimelineView timelineView) {
		Log.d("Thoth", "Recaching the x position of events!");
		final List<Event> events = timelineView.getEvents().getEvents();
		cache = new float[events.size()];

		final long t0 = timelineView.getZeroDate().getTime();

		int i = 0;
		for (final Event t : events) {
			cache[i++] = (t.getDate().getTime() - t0) / scale;
		}
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

}
