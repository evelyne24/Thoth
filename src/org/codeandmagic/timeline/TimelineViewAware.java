/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All Rights Reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify it under
	the terms of the GNU Lesser General Public License as published by the
	Free Software Foundation, either version 3 of the License, or (at your
	option) any later version.

	Thoth is distributed in the hope that it will be useful, but WITHOUT 
	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public 
	License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.timeline;

import java.util.Collection;

public interface TimelineViewAware {
	public void timelineViewContructed(TimelineView timelineView);

	public void timelineViewSizeChanged(TimelineView timelineView, int w, int h, int oldw, int oldh);

	public void timelineViewEventsChanged(TimelineView timelineView, Events events, Collection<Event> added,
			Collection<Event> removed);

	public void timelineViewEventSelected(TimelineView timelineView, Event event, float eventX, float eventY);
}
