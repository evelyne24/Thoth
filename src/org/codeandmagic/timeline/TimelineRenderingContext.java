package org.codeandmagic.timeline;

import org.codeandmagic.timeline.AxisHorizontalLayout.AxisHint;
import org.codeandmagic.util.FakeArrayFloat;

public class TimelineRenderingContext {
	private TimelineView timeline;

	private float viewStartX;
	private float viewEndX;
	private int viewWidth;
	private int viewHeight;

	private int firtEventIndex;
	private int lastEventIndex;
	private FakeArrayFloat eventsX;
	private FakeArrayFloat eventsY;
	private AxisHint[] axisX;

	public FakeArrayFloat getEventsX() {
		return eventsX;
	}

	public void setEventsX(final FakeArrayFloat eventsX) {
		this.eventsX = eventsX;
	}

	public FakeArrayFloat getEventsY() {
		return eventsY;
	}

	public void setEventsY(final FakeArrayFloat eventsY) {
		this.eventsY = eventsY;
	}

	public void setTimeline(final TimelineView timeline) {
		this.timeline = timeline;
	}

	public TimelineView getTimeline() {
		return timeline;
	}

	public void setAxisX(final AxisHint[] axisX) {
		this.axisX = axisX;
	}

	public AxisHint[] getAxisX() {
		return axisX;
	}

	public void setViewStartX(final float startX) {
		viewStartX = startX;
	}

	public float getViewStartX() {
		return viewStartX;
	}

	public void setViewEndX(final float endX) {
		viewEndX = endX;
	}

	public float getViewEndX() {
		return viewEndX;
	}

	public void setViewWidth(final int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewHeight(final int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setFirtEventIndex(final int firtEventIndex) {
		this.firtEventIndex = firtEventIndex;
	}

	public int getFirtEventIndex() {
		return firtEventIndex;
	}

	public void setLastEventIndex(final int lastEventIndex) {
		this.lastEventIndex = lastEventIndex;
	}

	public int getLastEventIndex() {
		return lastEventIndex;
	}
}
