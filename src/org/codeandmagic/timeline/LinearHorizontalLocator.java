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

import java.util.List;

import org.codeandmagic.util.TimeUtils;

import android.util.Log;

/**
 * {@link EventHorizontalLocator} which positions {@link Event}s linearly based on a provided scaling factor. Otherwise said the
 * position is equal with the time distance from the 0 time ( {@link TimelineView#getZeroDate()} ) to the date of the event
 * divided by the scaling factor ( {@link #getScale()} ).
 * 
 * @author cristi
 * 
 */
public class LinearHorizontalLocator extends PrecachedHorizontalLocator {

	/**
	 * The scale factor represents how many time units (milliseconds) are compacted into one pixel
	 */
	private float scale;
	public static final float DEFAULT_SCALE = TimeUtils.DAY * 3 / 600;

	public LinearHorizontalLocator() {
		setScale(DEFAULT_SCALE);
	}

	public float getScale() {
		return scale;
	}

	public void setScale(final float scale) {
		this.scale = scale;
	}

	@Override
	public void reCache(final TimelineView timelineView) {
		Log.d("Thoth", "Recaching the x position of events!");
		final List<Event> events = timelineView.getEvents().getEvents();
		cache = new float[events.size()];

		final long t0 = timelineView.getZeroDate().getTime();

		int i = 0;
		for (final Event t : events) {
			cache[i++] = (t.getDate().getTime() - t0) / scale;
		}
	}

}
