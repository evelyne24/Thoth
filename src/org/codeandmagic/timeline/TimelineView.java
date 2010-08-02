package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.codeandmagic.util.FakeArrayFloat;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.widget.RelativeLayout;

public class TimelineView extends RelativeLayout implements EventsChangeListener, OnGestureListener {

	private final TimelineInnerView innerView;
	private final GestureDetector gestureDetector;

	private final Events events;
	private Event selectedEvent;

	private final Collection<TimelineViewAware> listeners;

	private EventHorizontalLocator horizontalLayout;
	private EventVerticalLocator verticalLayout;
	private EventIconRenderer iconRenderer;
	private AxisHorizontalLocator axisHorizontalLayout;
	private AxisRenderer axisRenderer;
	private BackgroundRenderer backgroundRenderer;
	private EventDetailsRenderer eventRenderer;

	private final Date zeroDate = new Date();
	private float currentX = -577f;
	private TimelineRenderingContext context;

	public class TimelineInnerView extends View {

		public TimelineInnerView(final Context context) {
			super(context);
		}

		@Override
		protected void onDraw(final Canvas canvas) {
			Log.d("Timeline", "Drawing");
			super.onDraw(canvas);

			context = recalculate();

			drawBackground(canvas, context);
			drawAxis(canvas, context);
			drawEvents(canvas, context);
		}
	}

	public TimelineView(final Context context, final EventHorizontalLocator horizontalLayout,
			final EventVerticalLocator verticalLayout, final EventIconRenderer iconRenderer,
			final AxisHorizontalLocator axisHorizontalLayout, final AxisRenderer xAxisRenderer,
			final BackgroundRenderer backgroundRenderer, final EventDetailsRenderer eventRenderer) {

		super(context);
		gestureDetector = new GestureDetector(this);

		listeners = new HashSet<TimelineViewAware>();

		events = new Events();
		events.addChangeListener(this);

		setHorizontalLayout(horizontalLayout);
		setVerticalLayout(verticalLayout);
		setIconRenderer(iconRenderer);
		setAxisHorizontalLayout(axisHorizontalLayout);
		setAxisRenderer(xAxisRenderer);
		setBackgroundRenderer(backgroundRenderer);
		setEventRenderer(eventRenderer);

		for (final TimelineViewAware listener : listeners) {
			listener.timelineViewContructed(this);
		}

		innerView = new TimelineInnerView(context);
		addView(innerView);
	}

	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	public boolean onDown(final MotionEvent e) {
		click(e.getX(), e.getY());
		return true;
	}

	public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
		return false;
	}

	public void onLongPress(final MotionEvent e) {

	}

	private final static float SCROLL_RESPONSIVENESS = 4;

	public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
		// Log.d("Thoth", "Scroll -> dx:" + distanceX);
		scroll(distanceX / SCROLL_RESPONSIVENESS);
		return true;
	}

	public void onShowPress(final MotionEvent e) {

	}

	public boolean onSingleTapUp(final MotionEvent e) {
		return false;
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

	public void eventsChanged(final Events events, final Collection<Event> added, final Collection<Event> removed) {
		for (final TimelineViewAware listener : listeners) {
			listener.timelineViewEventsChanged(this, events, added, removed);
		}
		invalidate();
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

	public void click(final float x, final float y) {
		final FakeArrayFloat eventsX = context.getEventsX();
		final FakeArrayFloat eventsY = context.getEventsY();
		final int size = eventsX.size();
		final float absClickX = x + currentX;
		final int firstEventIndex = context.getFirtEventIndex();

		float eventX = 0;
		float eventY = 0;
		Event event = null;
		for (int i = 0; i < size; ++i) {
			eventX = eventsX.get(i);
			eventY = eventsY.get(i);
			event = events.getEvents().get(firstEventIndex + i);
			if (iconRenderer.isHit(event, eventX, eventY, absClickX, y)) {
				eventClicked(event, eventX, eventY, x, y);
			}
		}
	}

	public void eventClicked(final Event event, final float eventX, final float eventY, final float clickX, final float clickY) {
		Log.d("Timeline", "Event Clicked! e:" + event.toString());
		setSelectedEvent(event);
		for (final TimelineViewAware listener : listeners) {
			listener.timelineViewEventSelected(this, event, eventX, eventY);
		}
		eventRenderer.eventSelected(this, event, eventX, eventY);
	}

	public void setSelectedEvent(final Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
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

	public EventHorizontalLocator getHorizontalLayout() {
		return horizontalLayout;
	}

	public void setHorizontalLayout(final EventHorizontalLocator horizontalLayout) {
		this.horizontalLayout = horizontalLayout;
		if (horizontalLayout instanceof TimelineViewAware) {
			addListener((TimelineViewAware) horizontalLayout);
		}
	}

	public EventVerticalLocator getVerticalLayout() {
		return verticalLayout;
	}

	public void setVerticalLayout(final EventVerticalLocator verticalLayout) {
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

	public AxisHorizontalLocator getAxisHorizontalLayout() {
		return axisHorizontalLayout;
	}

	public void setAxisHorizontalLayout(final AxisHorizontalLocator axisHorizontalLayout) {
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

	public void setEventRenderer(final EventDetailsRenderer eventRenderer) {
		this.eventRenderer = eventRenderer;
	}

	public EventDetailsRenderer getEventRenderer() {
		return eventRenderer;
	}
}
