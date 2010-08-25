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

import org.codeandmagic.util.ArrayUtils;
import org.codeandmagic.util.FakeArrayFlatFloat;
import org.codeandmagic.util.FakeArrayLinkFloat;

import android.util.Log;

/**
 * {@link EventHorizontalLocator} which pre-calculates the position of all event
 * 
 * @author cristi
 * 
 */
public abstract class PrecachedHorizontalLocator implements EventHorizontalLocator, TimelineViewAware {

	protected float[] cache;

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

	public float[] getCache() {
		return cache;
	}

	public void computeX(final TimelineRenderingContext context) {
		Log.d("Thoth", "Getting events in interval " + context.getViewStartX() + " - " + context.getViewEndX());
		final int[] pos = ArrayUtils.fuzzyIntervalSearch(cache, context.getViewStartX(), context.getViewEndX());
		if (pos == null) {
			context.setFirtEventIndex(-1);
			context.setLastEventIndex(-1);
			context.setEventsX(new FakeArrayFlatFloat(0, 0));
			return;
		}
		final int first = pos[0];
		final int last = pos[1];
		// set the visible events
		context.setFirtEventIndex(first);
		context.setLastEventIndex(last);
		// set the x position of the visible events
		context.setEventsX(new FakeArrayLinkFloat(cache, first, last));
	}

	public abstract void reCache(final TimelineView timelineView);
}
