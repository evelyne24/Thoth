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
 * Interface implemented by the class responsible of calculating the vertical positioning of a set of {@link Event}s on a
 * timeline.
 * 
 * @author cristi
 * 
 */
public interface EventVerticalLocator {
	/**
	 * Computes the vertical position (Y axis) for all events in the current visible "frame". The vertical limits are 0 and
	 * {@link TimelineView#getHeight()}
	 * <p style="color:red">
	 * The contract of this method guarantees {@link TimelineRenderingContext#setEventsY(org.codeandmagic.util.FakeArrayFloat)} is
	 * going to get called during the execution of this function.
	 * </p>
	 * 
	 * @param context
	 */
	public void computeY(TimelineRenderingContext context);
}
