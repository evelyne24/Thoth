package org.codeandmagic.timeline;

import java.util.Collection;

public interface EventsChangeListener {
	public void eventsChanged(Events events, Collection<Event> added, Collection<Event> removed);
}
