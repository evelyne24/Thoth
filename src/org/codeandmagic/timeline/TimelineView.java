package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.SortedSet;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

public class TimelineView extends View implements EventsChangeListener {
	/**
	 * The list of events displayed by this timeline
	 */
	private final Events events;
	/**
	 * The rendering context of the last render
	 */
	private TimelineRenderingContext renderingContext;

	private EventHorizontalLayout horizontalLayout;
	private EventVerticalLayout verticalLayout;
	private EventIconRenderer iconRenderer;
	private AxisHorizontalLayout axisHorizontalLayout;
	private AxisRenderer axisRenderer;
	private BackgroundRenderer backgroundRenderer;

	private float currentX = 0;
	private boolean ready = false;

	public TimelineView(Context context, EventHorizontalLayout horizontalLayout, EventVerticalLayout verticalLayout, EventIconRenderer iconRenderer,
			AxisHorizontalLayout axisHorizontalLayout, AxisRenderer xAxisRenderer, BackgroundRenderer backgroundRenderer) {
		super(context);
		events = new Events();
		events.addChangeListener(this);
		setHorizontalLayout(horizontalLayout);
		setVerticalLayout(verticalLayout);
		setIconRenderer(iconRenderer);
		setAxisHorizontalLayout(axisHorizontalLayout);
		setAxisRenderer(xAxisRenderer);
		setBackgroundRenderer(backgroundRenderer);
		initResources();
	}

	private void initResources() {
		Log.d("Timeline", "Preloading resources");
		this.iconRenderer.preloadIcons(this.getContext());
	}

	public Events getEvents() {
		return events;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		ready = true;
		Log.d("Timeline", "View size changed! Need to re-render!");
		renderFromScratch();
	}

	public EventHorizontalLayout getHorizontalLayout() {
		return horizontalLayout;
	}

	public void setHorizontalLayout(EventHorizontalLayout horizontalLayout) {
		this.horizontalLayout = horizontalLayout;
	}

	public EventVerticalLayout getVerticalLayout() {
		return verticalLayout;
	}

	public void setVerticalLayout(EventVerticalLayout verticalLayout) {
		this.verticalLayout = verticalLayout;
	}

	public EventIconRenderer getIconRenderer() {
		return iconRenderer;
	}

	public void setIconRenderer(EventIconRenderer iconRenderer) {
		this.iconRenderer = iconRenderer;
	}

	public void setAxisHorizontalLayout(AxisHorizontalLayout axisHorizontalLayout) {
		this.axisHorizontalLayout = axisHorizontalLayout;
	}

	public AxisHorizontalLayout getAxisHorizontalLayout() {
		return axisHorizontalLayout;
	}

	public AxisRenderer getAxisRenderer() {
		return axisRenderer;
	}

	public void setAxisRenderer(AxisRenderer axisRenderer) {
		this.axisRenderer = axisRenderer;
	}

	public BackgroundRenderer getBackgroundRenderer() {
		return backgroundRenderer;
	}

	public void setBackgroundRenderer(BackgroundRenderer backgroundRenderer) {
		this.backgroundRenderer = backgroundRenderer;
	}

	public void eventsChanged(Events events, Collection<Event> added, Collection<Event> removed) {
		if (ready) {
			Log.d("Timeline", "Events changed! Ready to render!");
			this.renderFromScratch();
		} else {
			Log.d("Timeline", "Events changed but view not ready to render them!");
		}
	}

	public void renderFromScratch() {
		Log.d("Timeline", "Recalculating/rendering everything");
		renderingContext = new TimelineRenderingContext();
		renderingContext.setTimeline(this);
		renderingContext.setViewWidth(getWidth());
		renderingContext.setViewHeight(getHeight());
		renderingContext.setStartX(currentX);
		renderingContext.setEndX(currentX + getWidth());

		SortedSet<Event> es = events.getEvents();
		renderingContext.setEventsX(this.horizontalLayout.computeX(es, renderingContext));
		renderingContext.setEventsY(this.verticalLayout.computeY(es, renderingContext));
		renderingContext.setAxisX(this.axisHorizontalLayout.computeX(renderingContext));
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (renderingContext == null) {
			Log.d("Timeline", "Not ready for drawing!");
			return;
		}
		Log.d("Timeline", "Drawing");
		super.onDraw(canvas);
		drawBackground(canvas);
		drawAxis(canvas);
		drawEvents(canvas);
	}

	protected void drawEvents(Canvas canvas) {
		this.iconRenderer.renderIcons(canvas, renderingContext);
	}

	protected void drawAxis(Canvas canvas) {
		this.axisRenderer.renderAxis(canvas, renderingContext);
	}

	protected void drawBackground(Canvas canvas) {
		this.backgroundRenderer.renderBackground(canvas, renderingContext);
	}
}
