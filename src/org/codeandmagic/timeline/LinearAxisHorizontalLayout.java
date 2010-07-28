package org.codeandmagic.timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LinearAxisHorizontalLayout implements AxisHorizontalLayout {

	private int timeStep;
	private LinearEventHorizontalLayout eventHorizontalLayout;

	public LinearAxisHorizontalLayout(final int timeStep, final LinearEventHorizontalLayout eventHorizontalLayout) {
		setTimeStep(timeStep);
		setEventHorizontalLayout(eventHorizontalLayout);
	}

	public void computeX(final TimelineRenderingContext context) {
		final float scale = eventHorizontalLayout.getScale();
		final long t0 = context.getTimeline().getZeroDate().getTime();

		final long leftTime = t0 + (long) (context.getViewStartX() * scale);
		final long rightTime = leftTime + (long) (context.getViewWidth() * scale);

		final Date lt = new Date(leftTime);
		final int year = lt.getYear();
		final int month = lt.getMonth();
		final int day = lt.getDate();
		final int hour = lt.getHours();

		int h = hour < 23 ? hour + 1 : 0;
		final Calendar c = Calendar.getInstance();
		c.setTime(new Date(year, month, h < 23 ? day : day + 1, h, 0));

		while (!(h % timeStep == 0)) {
			c.add(Calendar.HOUR, 1);
			h += 1;
		}

		Date t = c.getTime();
		long time = t.getTime();

		// final long dif = firstTime - leftTime;

		final ArrayList<AxisHint> hints = new ArrayList<AxisHint>();
		do {
			hints.add(new AxisHint((time - t0) / scale, t));
			// increment time
			c.add(Calendar.HOUR, timeStep);
			t = c.getTime();
			time = t.getTime();
		} while (time < rightTime);

		/*final long firstTime = TimeUtils.ceiling(new Date(leftTime), Calendar.HOUR).getTime();
		final int nrAxis = (int) ((rightTime - leftTime) / timeStep) + 1;

		final AxisHint[] hints = new AxisHint[nrAxis];
		long time = firstTime;
		for (int i = 0; i < nrAxis; i++) {
			hints[i] = new AxisHint((time - t0) / scale, new Date(time));
			time += timeStep;
		}*/

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
