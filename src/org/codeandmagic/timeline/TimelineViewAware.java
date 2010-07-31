package org.codeandmagic.timeline;

import java.util.Collection;

public interface TimelineViewAware {
	public void timelineViewContructed(TimelineView timelineView);

	public void timelineViewSizeChanged(TimelineView timelineView, int w, int h, int oldw, int oldh);

	public void timelineViewEventsChanged(TimelineView timelineView, Events events, Collection<Event> added,
			Collection<Event> removed);

	public void timelineViewEventSelected(TimelineView timelineView, Event event, float eventX, float eventY);
}
