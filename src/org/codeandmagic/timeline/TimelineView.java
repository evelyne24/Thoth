package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

public class TimelineView extends View implements EventsChangeListener {
	
	public final static long HOUR = 60*60;
	public final static long DAY = HOUR*24;
	public final static long WEEK = DAY*7;
	public final static long MONTH = DAY*31;
	
	public final static long DEFAULT_VISIBLE_TIME = DAY * 3; //3 days 
	public final static int DEFAULT_AXIS_HEIGHT = 20; //px
	public final static long DEFAULT_GRID_INTERVAL = HOUR * 6;
	
	/**
	 * The list of events displayed by this timeline
	 */
	private final Events events;
	/**
	 * The rendering context of the last render
	 */
	private TimelineRenderingContext renderingContext;
	/**
	 * How often do we draw the separation lines (seconds)
	 */
	private long gridInterval = DEFAULT_GRID_INTERVAL;
	/**
	 * Height of the axis area in pixels
	 */
	private int axisHeight = DEFAULT_AXIS_HEIGHT;
	
	private EventHorizontalLayout horizontalLayout;
	private EventVerticalLayout verticalLayout;
	private EventIconRenderer iconRenderer;
	private XAxisRenderer xAxisRenderer;
	private BackgroundRenderer backgroundRenderer;

	public TimelineView(Context context, 
						EventHorizontalLayout horizontalLayout, 
						EventVerticalLayout verticalLayout, 
						EventIconRenderer iconRenderer,
						XAxisRenderer xAxisRenderer,
						BackgroundRenderer backgroundRenderer) {
		super(context);
		events = new Events();
		events.addChangeListener(this);
		setHorizontalLayout(horizontalLayout);
		setVerticalLayout(verticalLayout);
		setIconRenderer(iconRenderer);
		setxAxisRenderer(xAxisRenderer);
		setBackgroundRenderer(backgroundRenderer);
		initResources();
	}

	private void initResources() {
		Log.d("Timeline","Preloading resources");
		this.iconRenderer.preloadIcons(this.getContext());
	}

	public Events getEvents() {
		return events;
	}

	public long getGridInterval() {
		return gridInterval;
	}

	public void setGridInterval(long gridInterval) {
		this.gridInterval = gridInterval;
		invalidate();
	}

	public int getAxisHeight() {
		return axisHeight;
	}

	public void setAxisHeight(int axisHeight) {
		this.axisHeight = axisHeight;
		invalidate();
	}

	//@Override
	//protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	//	super.onSizeChanged(w, h, oldw, oldh);
	//}

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

	public XAxisRenderer getxAxisRenderer() {
		return xAxisRenderer;
	}

	public void setxAxisRenderer(XAxisRenderer xAxisRenderer) {
		this.xAxisRenderer = xAxisRenderer;
	}

	public BackgroundRenderer getBackgroundRenderer() {
		return backgroundRenderer;
	}

	public void setBackgroundRenderer(BackgroundRenderer backgroundRenderer) {
		this.backgroundRenderer = backgroundRenderer;
	}

	public void eventsChanged(Events events, Collection<Event> added, Collection<Event> removed) {
		Log.d("Timeline","Events changed");
		this.renderFromScratch();
	}

	public void renderFromScratch() {
		Log.d("Timeline","Recalculating/rendering everything");
		renderingContext = new TimelineRenderingContext();
		SortedSet<Event> es = events.getEvents();
		renderingContext.setEventsX(this.horizontalLayout.computeX(es, renderingContext));
		renderingContext.setEventsY(this.verticalLayout.computeY(es, renderingContext));
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(renderingContext == null){
			Log.d("Timeline","Not ready for drawing!");
			return;
		}
		Log.d("Timeline","Drawing");
		super.onDraw(canvas);
		drawBackground(canvas);
		drawAxis(canvas);
		drawEvents(canvas);
	}
	
	protected void drawEvents(Canvas canvas) {
		int maxX = getWidth();
		Iterator<Event> iter = events.getEvents().iterator();
		int i = 0;
		while(iter.hasNext() && renderingContext.getEventsX()[i] < maxX){
			drawElement(canvas, iter.next(), i++);
		}	
	}
	
	protected void drawElement(Canvas canvas, Event event, int indx){
		this.iconRenderer.renderIcon(event, indx, canvas, renderingContext);
	}

	protected void drawAxis(Canvas canvas) {
		this.xAxisRenderer.renderXAxis(canvas, renderingContext);
	}

	protected void drawBackground(Canvas canvas) {
		this.backgroundRenderer.renderBackground(canvas, renderingContext);
	}
}
