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

import java.util.Collection;

import org.codeandmagic.util.FakeArrayFlatFloat;
import org.codeandmagic.util.FakeArrayLinkFloat;

public abstract class PrecachedVerticalLocator implements EventVerticalLocator, TimelineViewAware {
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
