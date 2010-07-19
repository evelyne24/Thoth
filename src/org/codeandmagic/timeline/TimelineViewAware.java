package org.codeandmagic.timeline;

import java.util.Collection;

public interface TimelineViewAware {
	public void timelineViewContructed(TimelineView timelineView);

	public void timelineViewSizeChanged(TimelineView timelineView, int w, int h, int oldw, int oldh);

	public void timelineViewEventsChanged(TimelineView timelineView, Events events, Collection<Event> added, Collection<Event> removed);

	public void timelineViewDrawBegin(TimelineView timelineView);

	public void timelineViewDrawEnd(TimelineView timelineView);
}
