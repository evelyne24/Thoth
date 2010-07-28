package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

public class TimelineView extends View implements EventsChangeListener {
	/**
	 * The list of events managed/displayed by this timeline
	 */
	private final Events events;

	private final Collection<TimelineViewAware> listeners;

	private EventHorizontalLayout horizontalLayout;
	private EventVerticalLayout verticalLayout;
	private EventIconRenderer iconRenderer;
	private AxisHorizontalLayout axisHorizontalLayout;
	private AxisRenderer axisRenderer;
	private BackgroundRenderer backgroundRenderer;

	private final Date zeroDate = new Date();
	private float currentX = -577f;

	public TimelineView(final Context context, final EventHorizontalLayout horizontalLayout,
			final EventVerticalLayout verticalLayout, final EventIconRenderer iconRenderer,
			final AxisHorizontalLayout axisHorizontalLayout, final AxisRenderer xAxisRenderer,
			final BackgroundRenderer backgroundRenderer) {

		super(context);

		listeners = new HashSet<TimelineViewAware>();

		events = new Events();
		events.addChangeListener(this);

		setHorizontalLayout(horizontalLayout);
		setVerticalLayout(verticalLayout);
		setIconRenderer(iconRenderer);
		setAxisHorizontalLayout(axisHorizontalLayout);
		setAxisRenderer(xAxisRenderer);
		setBackgroundRenderer(backgroundRenderer);

		for (final TimelineViewAware listener : listeners) {
			listener.timelineViewContructed(this);
		}
	}

	public Events getEvents() {
		return events;
	}

	public Date getZeroDate() {
		return zeroDate;
	}

	public void addListener(final TimelineViewAware listener) {
		listeners.add(listener);
	}

	public void removeListener(final TimelineViewAware listener) {
		listeners.remove(listener);
	}

	@Override
	protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.d("Timeline", "View size changed!");
		for (final TimelineViewAware listener : listeners) {
			listener.timelineViewSizeChanged(this, w, h, oldw, oldh);
		}
		invalidate();
	}

	public EventHorizontalLayout getHorizontalLayout() {
		return horizontalLayout;
	}

	public void setHorizontalLayout(final EventHorizontalLayout horizontalLayout) {
		this.horizontalLayout = horizontalLayout;
		if (horizontalLayout instanceof TimelineViewAware) {
			addListener((TimelineViewAware) horizontalLayout);
		}
	}

	public EventVerticalLayout getVerticalLayout() {
		return verticalLayout;
	}

	public void setVerticalLayout(final EventVerticalLayout verticalLayout) {
		this.verticalLayout = verticalLayout;
		if (verticalLayout instanceof TimelineViewAware) {
			addListener((TimelineViewAware) verticalLayout);
		}
	}

	public EventIconRenderer getIconRenderer() {
		return iconRenderer;
	}

	public void setIconRenderer(final EventIconRenderer iconRenderer) {
		this.iconRenderer = iconRenderer;
		if (iconRenderer instanceof TimelineViewAware) {
			addListener((TimelineViewAware) iconRenderer);
		}
	}

	public AxisHorizontalLayout getAxisHorizontalLayout() {
		return axisHorizontalLayout;
	}

	public void setAxisHorizontalLayout(final AxisHorizontalLayout axisHorizontalLayout) {
		this.axisHorizontalLayout = axisHorizontalLayout;
		if (axisHorizontalLayout instanceof TimelineViewAware) {
			addListener((TimelineViewAware) axisHorizontalLayout);
		}
	}

	public AxisRenderer getAxisRenderer() {
		return axisRenderer;
	}

	public void setAxisRenderer(final AxisRenderer axisRenderer) {
		this.axisRenderer = axisRenderer;
		if (axisRenderer instanceof TimelineViewAware) {
			addListener((TimelineViewAware) axisRenderer);
		}
	}

	public BackgroundRenderer getBackgroundRenderer() {
		return backgroundRenderer;
	}

	public void setBackgroundRenderer(final BackgroundRenderer backgroundRenderer) {
		this.backgroundRenderer = backgroundRenderer;
		if (axisRenderer instanceof TimelineViewAware) {
			addListener((TimelineViewAware) backgroundRenderer);
		}
	}

	public void eventsChanged(final Events events, final Collection<Event> added, final Collection<Event> removed) {
		for (final TimelineViewAware listener : listeners) {
			listener.timelineViewEventsChanged(this, events, added, removed);
		}
		invalidate();
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		Log.d("Timeline", "Drawing");
		super.onDraw(canvas);

		final TimelineRenderingContext context = recalculate();

		drawBackground(canvas, context);
		drawAxis(canvas, context);
		drawEvents(canvas, context);
	}

	private TimelineRenderingContext recalculate() {
		Log.d("Timeline", "Recalculating/rendering everything");
		final TimelineRenderingContext renderingContext = new TimelineRenderingContext();
		renderingContext.setTimeline(this);
		renderingContext.setViewWidth(getWidth());
		renderingContext.setViewHeight(getHeight());
		renderingContext.setViewStartX(currentX);
		renderingContext.setViewEndX(currentX + getWidth());

		horizontalLayout.computeX(renderingContext);
		verticalLayout.computeY(renderingContext);
		axisHorizontalLayout.computeX(renderingContext);

		return renderingContext;
	}

	protected void drawEvents(final Canvas canvas, final TimelineRenderingContext renderingContext) {
		iconRenderer.renderIcons(canvas, renderingContext);
	}

	protected void drawAxis(final Canvas canvas, final TimelineRenderingContext renderingContext) {
		axisRenderer.renderAxis(canvas, renderingContext);
	}

	protected void drawBackground(final Canvas canvas, final TimelineRenderingContext renderingContext) {
		backgroundRenderer.renderBackground(canvas, renderingContext);
	}

	public void scroll(final float distanceX) {
		currentX += distanceX;
		invalidate();
	}
}
