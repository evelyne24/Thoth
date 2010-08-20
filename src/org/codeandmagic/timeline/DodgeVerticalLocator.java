package org.codeandmagic.timeline;

import java.util.Collection;

import org.codeandmagic.util.FakeArrayFlatFloat;
import org.codeandmagic.util.FakeArrayLinkFloat;

import android.util.Log;

/**
 * Implementation of {@link EventVerticalLocator} in which events tend to be positioned on the middle of the available vertical
 * space. However if two events are close on the horizontal axis, they are moved up or down so that their icon and text don't
 * overlap.
 * 
 * @author cristi
 * 
 */
public class DodgeVerticalLocator extends MiddleVerticalLocator implements TimelineViewAware {

	private float[] cache;
	private final PrecachedEventHorizontalLocator horizontalLocator;
	private float maxInterferingDistance = 200;
	private float verticalStep = 30;

	public DodgeVerticalLocator(final PrecachedEventHorizontalLocator horizontalLocator) {
		this.horizontalLocator = horizontalLocator;
	}

	@Override
	public void computeY(final TimelineRenderingContext context) {
		final int firstEventIndex = context.getFirtEventIndex();
		final int lastEventIndex = context.getLastEventIndex();
		if (firstEventIndex == -1 && lastEventIndex == 1) {
			context.setEventsX(new FakeArrayFlatFloat(0, 0));
			return;
		}
		context.setEventsY(new FakeArrayLinkFloat(cache, firstEventIndex, lastEventIndex));
	}

	public void reCache(final TimelineView timelineView) {
		Log.d("Thoth", "Recaching the y position of events!");
		final float[] xCache = horizontalLocator.getCache();
		final int len = xCache.length; // ////////////////////////////////// MAKE SURE THIS IS CALLED AFTER HORIZONTAL_LOCATOR IS
		// CALLED
		cache = new float[len];
		final float middle = getMiddle(timelineView.getHeight()) - 20;
		final float max = timelineView.getEventsMaxY();
		final int nrLanes = (int) (max / verticalStep);

		int firstNeighbor = 0;
		float x = 0, y = 0;
		int i = 0, j = 0, z = 0;
		int sign = 0;

		// for all events
		eachEvent: for (i = 0; i < len; ++i) {
			x = xCache[i];
			// remove neighbors that are too far away
			while (firstNeighbor < i && x - xCache[firstNeighbor] > maxInterferingDistance) {
				++firstNeighbor;
			}
			// if no nearby neighbors
			if (firstNeighbor == i) {
				// vertical position is middle
				cache[i] = middle;
			}
			else {
				// check all possible positions if they're free. middle is skipped because of the previous check. we check
				// middle-1step, then middle+1step, then 2 steps up, 2 steps down...
				eachPossiblePosition: for (z = 1, sign = -1; z < nrLanes; ++z, sign *= -1) {
					y = middle + sign * verticalStep * ((z + 1) / 2);
					// for all valid neighbors check distance
					for (j = firstNeighbor; j < i; ++j) {
						if (y == cache[j]) {
							// already a neighbor there
							continue eachPossiblePosition;
						}
					}
					// if we got to this point this position is unused
					cache[i] = y;
					continue eachEvent;
				}
				// if we got to this point there's no free position which means we'll pick the position of the farthest neighbor
				cache[i] = cache[firstNeighbor];
				continue eachEvent;
			}

		}
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

	public void timelineViewEventSelected(final TimelineView timelineView, final Event event, final float eventX,
			final float eventY) {
	}

	public void setMaxInterferingDistance(final float maxInterferingDistance) {
		this.maxInterferingDistance = maxInterferingDistance;
	}

	public float getMaxInterferingDistance() {
		return maxInterferingDistance;
	}

	public void setVerticalStep(final float verticalStep) {
		this.verticalStep = verticalStep;
	}

	public float getVerticalStep() {
		return verticalStep;
	}

}
