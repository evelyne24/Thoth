package org.codeandmagic.timeline;

import org.codeandmagic.timeline.AxisHorizontalLayout.AxisHint;

public class TimelineRenderingContext {
	private TimelineView timeline;

	private float startX;
	private float endX;

	private float[] eventsX;
	private float[] eventsY;
	private AxisHint[] axisX;

	private int viewWidth;
	private int viewHeight;

	public float[] getEventsX() {
		return eventsX;
	}

	public void setEventsX(float[] eventsX) {
		this.eventsX = eventsX;
	}

	public float[] getEventsY() {
		return eventsY;
	}

	public void setEventsY(float[] eventsY) {
		this.eventsY = eventsY;
	}

	public void setTimeline(TimelineView timeline) {
		this.timeline = timeline;
	}

	public TimelineView getTimeline() {
		return timeline;
	}

	public void setAxisX(AxisHint[] axisX) {
		this.axisX = axisX;
	}

	public AxisHint[] getAxisX() {
		return axisX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	public float getStartX() {
		return startX;
	}

	public void setEndX(float endX) {
		this.endX = endX;
	}

	public float getEndX() {
		return endX;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public int getViewHeight() {
		return viewHeight;
	}
}
