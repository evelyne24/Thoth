package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.sax.Element;
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

	public TimelineView(Context context, EventHorizontalLayout horizontalLayout, EventVerticalLayout verticalLayout, EventIconRenderer iconRenderer) {
		super(context);
		events = new Events();
		events.addChangeListener(this);
		setHorizontalLayout(horizontalLayout);
		setVerticalLayout(verticalLayout);
		setIconRenderer(iconRenderer);
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

	public void eventsChanged(Events events, Collection<Event> added, Collection<Event> removed) {
		Log.d("Timeline","Events changed");
		this.renderFromScratch();
	}

	private float[] eventsX;
	private float[] eventsY;
	public void renderFromScratch() {
		Log.d("Timeline","Recalculating everything");
		SortedSet<Event> es = events.getEvents();
		eventsX = this.horizontalLayout.computeX(es);
		eventsY = this.verticalLayout.computeY(es, eventsX);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(eventsY == null){
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
		while(iter.hasNext() && eventsX[i] < maxX){
			drawElement(canvas, iter.next(), i++);
		}	
	}
	
	protected void drawElement(Canvas canvas, Event event, int indx){
		//Log.d("Timeline","Drawing event "+event+" at "+eventsX[indx]+"."+eventsY[indx]);
		Bitmap b = this.iconRenderer.getIconForEvent(event);
		canvas.drawBitmap(b, eventsX[indx], eventsY[indx], null);
	}

	protected void drawAxis(Canvas canvas) {
		/*int y = getHeight() - getAxisHeight();
		//draw a while line to separate the actual timeline from axis labels
		Paint horLinePaint = new Paint();
		horLinePaint.setColor(Color.LTGRAY);
		canvas.drawLine(0, y, getWidth(), y, horLinePaint);
		
		//draw vertical lines
		Paint verLinePaint = new Paint();
		verLinePaint.setPathEffect(new DashPathEffect(new float[]{3f,3f}, 0));
		verLinePaint.setColor(Color.LTGRAY);
		SimpleDateFormat df = new SimpleDateFormat("H:m");
		
		int pixelsGridInterval = (int)(getGridInterval()/getScale());
		for(int x = pixelsGridInterval; x < getWidth(); x += pixelsGridInterval){
			canvas.drawLine(x, 0, x, y, verLinePaint);
			//draw labels under the grid lines
			Date d = new Date(startDate.getTime()+(long)(x*scale*1000));
			canvas.drawText(df.format(d), x-15, y+15, horLinePaint);
		}*/
	}

	protected void drawBackground(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY);
	}
}
