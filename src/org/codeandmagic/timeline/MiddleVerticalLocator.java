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

import org.codeandmagic.util.FakeArrayFlatFloat;

/**
 * Basic implementation for {@link EventVerticalLocator} which positions all events on the vertical middle of the timeline view.
 * Suitable for few events that are considerably far apart (horizontally) so that their icon and label doesn't overlap.
 * 
 * @author cristi
 * 
 */
public class MiddleVerticalLocator implements EventVerticalLocator {

	public void computeY(final TimelineRenderingContext context) {
		final int size = context.getEventsX().size();
		final float middle = getMiddle(context.getTimeline().getHeight());
		context.setEventsY(new FakeArrayFlatFloat(middle, size));
	}

	protected float getMiddle(final int viewHeight) {
		return viewHeight / 2;
	}

}
