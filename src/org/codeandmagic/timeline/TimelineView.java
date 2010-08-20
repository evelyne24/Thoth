package org.codeandmagic.timeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

	private final List<TimelineViewAware> listeners;

	private EventHorizontalLocator horizontalLocator;
	private EventVerticalLocator verticalLocator;
	private AxisHorizontalLocator axisHorizontalLocator;
	private BackgroundRenderer backgroundRenderer;
	private AxisRenderer axisRenderer;
	private EventIconRenderer iconRenderer;
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

		listeners = new ArrayList<TimelineViewAware>();
		// add 1 null entry for each locator and renderer so each has a reserved spot
		for (int i = 0; i < 7; i++) {
			listeners.add(null);
		}

		events = new Events();
		events.addChangeListener(this);

		setHorizontalLocator(horizontalLayout);
		setVerticalLocator(verticalLayout);
		setIconRenderer(iconRenderer);
		setAxisHorizontalLocator(axisHorizontalLayout);
		setAxisRenderer(xAxisRenderer);
		setBackgroundRenderer(backgroundRenderer);
		setEventRenderer(eventRenderer);

		notifyContructed();

		innerView = new TimelineInnerView(context);
		addView(innerView);
	}

	protected void notifyContructed() {
		for (final TimelineViewAware listener : listeners) {
			if (listener != null) {
				listener.timelineViewContructed(this);
			}
		}
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
		notifySizeChanged(w, h, oldw, oldh);
		invalidate();
	}

	protected void notifySizeChanged(final int w, final int h, final int oldw, final int oldh) {
		for (final TimelineViewAware listener : listeners) {
			if (listener != null) {
				listener.timelineViewSizeChanged(this, w, h, oldw, oldh);
			}
		}
	}

	public void eventsChanged(final Events events, final Collection<Event> added, final Collection<Event> removed) {
		notifyEventsChanged(events, added, removed);
		invalidate();
	}

	protected void notifyEventsChanged(final Events events2, final Collection<Event> added, final Collection<Event> removed) {
		for (final TimelineViewAware listener : listeners) {
			if (listener != null) {
				listener.timelineViewEventsChanged(this, events, added, removed);
			}
		}
	}

	private TimelineRenderingContext recalculate() {
		Log.d("Timeline", "Recalculating/rendering everything");
		final TimelineRenderingContext renderingContext = new TimelineRenderingContext();

		renderingContext.setTimeline(this);
		renderingContext.setViewStartX(currentX);
		renderingContext.setViewEndX(currentX + getWidth());

		horizontalLocator.computeX(renderingContext);
		verticalLocator.computeY(renderingContext);
		axisHorizontalLocator.computeX(renderingContext);

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
		notifyEventSelected(event, eventX, eventY);
	}

	protected void notifyEventSelected(final Event event, final float eventX, final float eventY) {
		for (final TimelineViewAware listener : listeners) {
			if (listener != null) {
				listener.timelineViewEventSelected(this, event, eventX, eventY);
			}
		}
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

	public EventHorizontalLocator getHorizontalLocator() {
		return horizontalLocator;
	}

	public void setHorizontalLocator(final EventHorizontalLocator horizontalLocator) {
		this.horizontalLocator = horizontalLocator;
		if (horizontalLocator instanceof TimelineViewAware) {
			listeners.set(0, (TimelineViewAware) horizontalLocator);
		}
		else {
			listeners.set(0, null);
		}
	}

	public EventVerticalLocator getVerticalLocator() {
		return verticalLocator;
	}

	public void setVerticalLocator(final EventVerticalLocator verticalLocator) {
		this.verticalLocator = verticalLocator;
		if (verticalLocator instanceof TimelineViewAware) {
			listeners.set(1, (TimelineViewAware) verticalLocator);
		}
		else {
			listeners.set(1, null);
		}
	}

	public AxisHorizontalLocator getAxisHorizontalLocator() {
		return axisHorizontalLocator;
	}

	public void setAxisHorizontalLocator(final AxisHorizontalLocator axisHorizontalLocator) {
		this.axisHorizontalLocator = axisHorizontalLocator;
		if (axisHorizontalLocator instanceof TimelineViewAware) {
			listeners.set(2, (TimelineViewAware) axisHorizontalLocator);
		}
		else {
			listeners.set(2, null);
		}
	}

	public BackgroundRenderer getBackgroundRenderer() {
		return backgroundRenderer;
	}

	public void setBackgroundRenderer(final BackgroundRenderer backgroundRenderer) {
		this.backgroundRenderer = backgroundRenderer;
		if (backgroundRenderer instanceof TimelineViewAware) {
			listeners.set(3, (TimelineViewAware) backgroundRenderer);
		}
		else {
			listeners.set(3, null);
		}
	}

	public AxisRenderer getAxisRenderer() {
		return axisRenderer;
	}

	public void setAxisRenderer(final AxisRenderer axisRenderer) {
		this.axisRenderer = axisRenderer;
		if (axisRenderer instanceof TimelineViewAware) {
			listeners.set(4, (TimelineViewAware) axisRenderer);
		}
		else {
			listeners.set(4, null);
		}
	}

	public EventIconRenderer getIconRenderer() {
		return iconRenderer;
	}

	public void setIconRenderer(final EventIconRenderer iconRenderer) {
		this.iconRenderer = iconRenderer;
		if (iconRenderer instanceof TimelineViewAware) {
			listeners.set(5, (TimelineViewAware) iconRenderer);
		}
		else {
			listeners.set(5, null);
		}
	}

	public EventDetailsRenderer getEventRenderer() {
		return eventRenderer;
	}

	public void setEventRenderer(final EventDetailsRenderer eventRenderer) {
		this.eventRenderer = eventRenderer;
		if (eventRenderer instanceof TimelineViewAware) {
			listeners.set(6, (TimelineViewAware) eventRenderer);
		}
		else {
			listeners.set(6, null);
		}
	}

	public float getEventsMaxY() {
		return getHeight() - axisRenderer.getLabelAreaHeight();
	}
}
