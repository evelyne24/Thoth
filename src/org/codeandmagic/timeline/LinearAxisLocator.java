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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.codeandmagic.util.TimeUtils;

/**
 * {@link AxisLocator} that positions the axes temporary linear, similar with how {@link LinearHorizontalLocator} positions
 * {@link Event}s. You can specify the number of axes through {@link #setTimeStep(int)}.<br />
 * <p style="color:red">
 * WARNING: This class should be used only together with a {@link LinearHorizontalLocator} or a {@link EventHorizontalLocator}
 * that uses a linear time formula and doesn't have gaps. <br>
 * YOU NEED TO SYNCHRONIZE THE SCALE SET IN THIS CLASS AND THE ONE SET ON THE {@link LinearHorizontalLocator}.
 * </p>
 * 
 * @author cristi
 * 
 */
public class LinearAxisLocator implements AxisLocator {

	private int timeStep;
	private float scale;

	public LinearAxisLocator(final LinearHorizontalLocator ehl) {
		// defaults
		setTimeStep(6); // 6 HOURS
		setScale(LinearHorizontalLocator.DEFAULT_SCALE);
	}

	public void computeX(final TimelineRenderingContext context) {
		final long t0 = context.getTimeline().getZeroDate().getTime();

		final long leftTime = t0 + (long) (context.getViewStartX() * scale);
		final long rightTime = leftTime + (long) (context.getTimeline().getWidth() * scale);

		final Date firstTime = TimeUtils.ceiling(new Date(leftTime), Calendar.HOUR);
		final int hour = firstTime.getHours();
		final Calendar c = Calendar.getInstance();
		c.setTime(firstTime);
		c.add(Calendar.HOUR, timeStep - hour % timeStep);

		Date t = c.getTime();
		long time = t.getTime();

		final ArrayList<AxisHint> hints = new ArrayList<AxisHint>();
		do {
			hints.add(new AxisHint((time - t0) / scale, t));
			// increment time
			c.add(Calendar.HOUR, timeStep);
			t = c.getTime();
			time = t.getTime();
		} while (time < rightTime);

		context.setAxisX(hints.toArray(new AxisHint[0]));
	}

	/**
	 * Sets the distance between the axes in hours. One axis will be position at the 0 hour of one day then at all the hours of
	 * the day that have the time step as divisor: hour(time) mod timeStep == 0.
	 * 
	 * @param timeStep
	 */
	public void setTimeStep(final int timeStep) {
		this.timeStep = timeStep;
	}

	public long getTimeStep() {
		return timeStep;
	}

	public float getScale() {
		return scale;
	}

	/**
	 * Sets the time scale. The time scale represents the number of milliseconds that are compressed into one pixel. WARNING: This
	 * class should be used only together with a {@link LinearHorizontalLocator} or a
	 * <p style="color:red">
	 * YOU NEED TO SYNCHRONIZE THIS WITH THE SCALE SET ON THE {@link LinearHorizontalLocator}.
	 * </p>
	 * 
	 * @param scale
	 */
	public void setScale(final float scale) {
		this.scale = scale;
	}

}
