package org.codeandmagic.timeline;

/**
 * An {@link EventVerticalLocator} which displays all events diagonally no matter the horizontal distance between them. </b>
 * Suitable for large number of events where the distance between them is approximately equal, otherwise it will probably look a
 * bit weird.
 * 
 * @author cristi
 * 
 */
public class DiagonalVerticalLocator extends PrecachedEventVerticalLocator {

	private float verticalStep = 40;

	@Override
	public void reCache(final TimelineView timelineView) {
		final int len = timelineView.getEvents().size();
		final float min = 5;
		final float max = timelineView.getEventsMaxY();
		float y = min;
		cache = new float[len];
		final float r = max - min - 20;
		for (int i = 0; i < len; ++i) {
			cache[i] = y;
			y = min + (y + verticalStep) % r;
		}
	}

	public void setVerticalStep(final float verticalStep) {
		this.verticalStep = verticalStep;
	}

	public float getVerticalStep() {
		return verticalStep;
	}

}
