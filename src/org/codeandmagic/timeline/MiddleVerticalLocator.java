package org.codeandmagic.timeline;

import org.codeandmagic.util.FakeArrayFlatFloat;

/**
 * Basic implementation for {@link EventVerticalLocator} which positions all events on the vertical middle of the timeline view.
 * 
 * @author cristi
 * 
 */
public class MiddleVerticalLocator implements EventVerticalLocator {

	public void computeY(final TimelineRenderingContext context) {
		final int size = context.getEventsX().size();
		final float middle = getMiddle(context.getTimeline().getHeight());
		context.setEventsY(new FakeArrayFlatFloat(middle, size));
	}

	protected float getMiddle(final int viewHeight) {
		return viewHeight / 2;
	}

}
