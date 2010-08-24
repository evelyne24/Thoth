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

import java.util.Date;

/**
 * Interface implemented by classes responsible with computing the axis positions in the visible "frame". Both the horizontal
 * position of the axis and the time representing it needs to be calculated.
 * <p style="color:red">
 * The contract of this methods guarantees that the {@link TimelineRenderingContext#setAxisX(AxisHint[])} will be called during
 * the execution of this function.
 * </p>
 * 
 * @author cristi
 * 
 */
public interface AxisLocator {
	public void computeX(TimelineRenderingContext context);

	public static class AxisHint {
		public AxisHint(final float x, final Date date) {
			this.x = x;
			this.date = date;
		}

		public final float x;
		public final Date date;

		@Override
		public String toString() {
			return x + "-" + date;
		}
	}
}
