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

import org.codeandmagic.timeline.AxisLocator.AxisHint;
import org.codeandmagic.util.FakeArrayFloat;

public class TimelineRenderingContext {
	private TimelineView timeline;

	private float viewStartX;
	private float viewEndX;

	private int firtEventIndex;
	private int lastEventIndex;
	private FakeArrayFloat eventsX;
	private FakeArrayFloat eventsY;
	private AxisHint[] axisX;

	public FakeArrayFloat getEventsX() {
		return eventsX;
	}

	public void setEventsX(final FakeArrayFloat eventsX) {
		this.eventsX = eventsX;
	}

	public FakeArrayFloat getEventsY() {
		return eventsY;
	}

	public void setEventsY(final FakeArrayFloat eventsY) {
		this.eventsY = eventsY;
	}

	public void setTimeline(final TimelineView timeline) {
		this.timeline = timeline;
	}

	public TimelineView getTimeline() {
		return timeline;
	}

	public void setAxisX(final AxisHint[] axisX) {
		this.axisX = axisX;
	}

	public AxisHint[] getAxisX() {
		return axisX;
	}

	public void setViewStartX(final float startX) {
		viewStartX = startX;
	}

	public float getViewStartX() {
		return viewStartX;
	}

	public void setViewEndX(final float endX) {
		viewEndX = endX;
	}

	public float getViewEndX() {
		return viewEndX;
	}

	public void setFirtEventIndex(final int firtEventIndex) {
		this.firtEventIndex = firtEventIndex;
	}

	public int getFirtEventIndex() {
		return firtEventIndex;
	}

	public void setLastEventIndex(final int lastEventIndex) {
		this.lastEventIndex = lastEventIndex;
	}

	public int getLastEventIndex() {
		return lastEventIndex;
	}
}
