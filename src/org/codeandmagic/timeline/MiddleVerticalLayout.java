package org.codeandmagic.timeline;

import org.codeandmagic.util.FakeArrayFlatFloat;

/**
 * Basic implementation for {@link EventVerticalLayout} which positions all events on the middle of the timeline (vertically).
 * 
 * @author cristi
 * 
 */
public class MiddleVerticalLayout implements EventVerticalLayout {

	public void computeY(final TimelineRenderingContext context) {
		final int size = context.getEventsX().size();
		final float middle = context.getViewHeight() / 2;
		context.setEventsY(new FakeArrayFlatFloat(middle, size));
	}

}
