package org.codeandmagic.timeline;

import java.util.List;

import org.codeandmagic.util.TimeUtils;

import android.util.Log;

/**
 * {@link EventHorizontalLocator} which positions {@link Event}s linearly based on a provided scaling factor. Otherwise said the
 * position is equal with the time distance from the 0 time ( {@link TimelineView#getZeroDate()} ) to the date of the event
 * divided by the scaling factor ( {@link #getScale()} ).
 * 
 * @author cristi
 * 
 */
public class LinearHorizontalLocator extends PrecachedHorizontalLocator {

	/**
	 * The scale factor represents how many time units (milliseconds) are compacted into one pixel
	 */
	private float scale;
	public static final float DEFAULT_SCALE = TimeUtils.DAY * 3 / 600;

	public LinearHorizontalLocator() {
		setScale(DEFAULT_SCALE);
	}

	public float getScale() {
		return scale;
	}

	public void setScale(final float scale) {
		this.scale = scale;
	}

	@Override
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

}
