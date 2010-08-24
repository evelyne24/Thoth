/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All rights reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify
	it under the terms of the GNU Lesser General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	Thoth is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.timeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Events {
	private final List<Event> events = new ArrayList<Event>();
	private final Collection<EventsChangeListener> changeListeners = new ArrayList<EventsChangeListener>();

	public List<Event> getEvents() {
		return events;
	}

	public void add(final Event... event) {
		for (int i = 0; i < event.length; i++) {
			events.add(event[i]);
		}
		Collections.sort(events);
		notifyChanged(Arrays.asList(event), null);
	}

	public void add(final Collection<Event> e) {
		events.addAll(e);
		Collections.sort(events);
		notifyChanged(e, null);
	}

	public int size() {
		return events.size();
	}

	public void notifyChanged(final Collection<Event> added, final Collection<Event> removed) {
		for (final EventsChangeListener lister : changeListeners) {
			lister.eventsChanged(this, added, removed);
		}
	}

	public void addChangeListener(final EventsChangeListener listener) {
		changeListeners.add(listener);
	}

	public void removeChangeListener(final EventsChangeListener listener) {
		changeListeners.remove(listener);
	}

	/**
	 * Returns the first {@link Event} that was recoded on the exact {@link Date} or after the passed parameter.
	 * 
	 * @param date
	 * @return
	 */
	public int getFirstAfter(final Date date) {
		// no point in even trying if we have no events
		if (events.size() == 0)
			return -1;
		// quick check to see if we have events > date
		final Event last = events.get(events.size() - 1);
		if (date.compareTo(last.getDate()) > 0)
			return -1;
		// iterate till we find what we need
		// TODO: we probably need a better algorithm
		for (int i = 0; i < events.size(); ++i) {
			if (events.get(i).getDate().compareTo(date) >= 0)
				return i;
		}
		// we should never get here because of the quick test
		return -1;
	}

	public int getLastBefore(final Date date) {
		// no point in even trying if we have no events
		if (events.size() == 0)
			return -1;
		// quick check to see if we have events < date
		final Event first = events.get(0);
		if (date.compareTo(first.getDate()) < 0)
			return -1;
		// iterate till we find what we need
		// TODO: we probably need a better algorithm
		for (int i = 0; i < events.size(); ++i) {
			if (events.get(i).getDate().compareTo(date) > 0)
				return i - 1;
		}
		// we should never get here because of the quick test
		return -1;
	}

	public List<Event> getInterval(final Date from, final Date to) {
		final int fromIndx = getFirstAfter(from);
		final int toIndx = getLastBefore(to);
		if (fromIndx == -1) {
			if (toIndx == -1)
				return null;
			else
				return events.subList(0, toIndx);
		}
		else {
			if (toIndx == -1)
				return events.subList(fromIndx, events.size() - 1);
			else
				return events.subList(fromIndx, toIndx);
		}
	}
}
