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
		final float middle = context.getViewHeight() / 2;
		context.setEventsY(new FakeArrayFlatFloat(middle, size));
	}

}
