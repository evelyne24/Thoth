package org.codeandmagic.timeline;

import java.util.Calendar;
import java.util.Date;

import org.codeandmagic.util.TimeUtils;

public class LinearAxisHorizontalLayout implements AxisHorizontalLayout {

	private long timeStep;
	private LinearEventHorizontalLayout eventHorizontalLayout;

	public LinearAxisHorizontalLayout(final long timeStep, final LinearEventHorizontalLayout eventHorizontalLayout) {
		setTimeStep(timeStep);
		setEventHorizontalLayout(eventHorizontalLayout);
	}

	public void computeX(final TimelineRenderingContext context) {
		final float scale = eventHorizontalLayout.getScale();
		final long t0 = context.getTimeline().getZeroDate().getTime();

		final long leftTime = t0 - (long) (context.getViewStartX() * scale);
		final long rightTime = leftTime + (long) (context.getViewWidth() * scale);
		final long firstTime = TimeUtils.ceiling(new Date(leftTime), Calendar.HOUR).getTime();
		final int nrAxis = (int) ((rightTime - leftTime) / timeStep) + 1;

		final AxisHint[] hints = new AxisHint[nrAxis];
		long time = firstTime;
		for (int i = 0; i < nrAxis; i++) {
			hints[i] = new AxisHint((time - t0) / scale, new Date(time));
			time += timeStep;
		}

		context.setAxisX(hints);
	}

	public void setTimeStep(final long timeStep) {
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
