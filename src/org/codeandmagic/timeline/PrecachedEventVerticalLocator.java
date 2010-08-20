package org.codeandmagic.timeline;

import java.util.Collection;

import org.codeandmagic.util.FakeArrayFlatFloat;
import org.codeandmagic.util.FakeArrayLinkFloat;

public abstract class PrecachedEventVerticalLocator implements EventVerticalLocator, TimelineViewAware {
	protected float[] cache;

	public void computeY(final TimelineRenderingContext context) {
		final int firstEventIndex = context.getFirtEventIndex();
		final int lastEventIndex = context.getLastEventIndex();
		if (firstEventIndex == -1 && lastEventIndex == 1) {
			context.setEventsX(new FakeArrayFlatFloat(0, 0));
			return;
		}
		context.setEventsY(new FakeArrayLinkFloat(cache, firstEventIndex, lastEventIndex));
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

	public abstract void reCache(final TimelineView timelineView);
}
