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

/**
 * An {@link EventVerticalLocator} which displays all events diagonally no matter the horizontal distance between them. </b>
 * Suitable for large number of events where the distance between them is approximately equal, otherwise it will probably look a
 * bit weird.
 * 
 * @author cristi
 * 
 */
public class DiagonalVerticalLocator extends PrecachedVerticalLocator {

	private float verticalStep = 40;

	@Override
	public void reCache(final TimelineView timelineView) {
		final int len = timelineView.getEvents().size();
		final float min = 5;
		final float max = timelineView.getEventsMaxY();
		float y = min;
		cache = new float[len];
		final float r = max - min - 20;
		for (int i = 0; i < len; ++i) {
			cache[i] = y;
			y = min + (y + verticalStep) % r;
		}
	}

	public void setVerticalStep(final float verticalStep) {
		this.verticalStep = verticalStep;
	}

	public float getVerticalStep() {
		return verticalStep;
	}

}
