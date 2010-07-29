package org.codeandmagic.timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.codeandmagic.util.TimeUtils;

public class LinearAxisHorizontalLayout implements AxisHorizontalLayout {

	private int timeStep;
	private LinearEventHorizontalLayout eventHorizontalLayout;

	public LinearAxisHorizontalLayout(final LinearEventHorizontalLayout ehl) {
		// defaults
		setTimeStep(6); // 6 HOURS
		setEventHorizontalLayout(ehl);
	}

	public void computeX(final TimelineRenderingContext context) {
		final float scale = eventHorizontalLayout.getScale();
		final long t0 = context.getTimeline().getZeroDate().getTime();

		final long leftTime = t0 + (long) (context.getViewStartX() * scale);
		final long rightTime = leftTime + (long) (context.getViewWidth() * scale);

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

	public void setTimeStep(final int timeStep) {
		this.timeStep = timeStep;
	}

	public long getTimeStep() {
		return timeStep;
	}

	public void setEventHorizontalLayout(final LinearEventHorizontalLayout eventHorizontalLayout) {
		this.eventHorizontalLayout = eventHorizontalLayout;
	}

	public LinearEventHorizontalLayout getEventHorizontalLayout() {
		return eventHorizontalLayout;
	}

}
