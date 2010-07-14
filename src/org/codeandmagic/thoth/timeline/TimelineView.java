package org.codeandmagic.thoth.timeline;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.codeandmagic.thoth.R;
import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.Thoth;
import org.codeandmagic.thoth.data.Thoths;
import org.codeandmagic.thoth.data.VideoThoth;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

public class TimelineView extends View {
	
	private final static long HOUR = 60*60;
	private final static long DAY = HOUR*24;
	private final static long WEEK = DAY*7;
	private final static long MONTH = DAY*31;
	
	public final static long DEFAULT_VISIBLE_TIME = DAY * 3; //3 days 
	public final static int DEFAULT_AXIS_HEIGHT = 20; //px
	public final static long DEFAULT_GRID_INTERVAL = HOUR * 6;
	
	public final static int BMP_TEXT_THOTH = 0;
	public final static int BMP_PHOTO_THOTH = 1;
	public final static int BMP_VIDEO_THOTH = 2;
	
	private final static int THOTH_ICON_WIDTH = 33;
	private final static int THOTH_ICON_HEIGHT = 31;
	
	/**
	 * The leftmost visible date.  
	 */
	private Date startDate;
	/**
	 * The rightmost visibile date.
	 * Equal to {@link #startDate} + {@link #visibleTime}
	 */
	private Date endDate;
	/**
	 * The amount of time (seconds) that are "squeezed" into one pixel.
	 * This is calculated as {@link #visibleTime}/{@link #getWidth()} and can't be manually set. 
	 */
	private float scale;
	/**
	 * The amount of time (seconds) that is to be displayed by this timeline. 
	 */
	private long visibleTime =  DEFAULT_VISIBLE_TIME;
	/**
	 * The list of thoths displayed by this timeline
	 */
	private Thoths thoths;
	/**
	 * How often do we draw the separation lines (seconds)
	 */
	private long gridInterval = DEFAULT_GRID_INTERVAL;
	/**
	 * Height of the axis area in pixels
	 */
	private int axisHeight = DEFAULT_AXIS_HEIGHT;
	/**
	 * Bitmaps necessary for drawing icons
	 */
	private Bitmap[] icons;

	public TimelineView(Context context) {
		super(context);
		initResources();
		setTestData();
		Date now = new Date();
		Date startDate = new Date(now.getTime()-DEFAULT_VISIBLE_TIME*1000);
		setStartDate(startDate);
	}

	private void setTestData() {
		long now = (new Date()).getTime();
		Thoth t1 = new PictureThoth();
		t1.setDate(new Date(now-DAY*2*1000));
		Thoth t2 = new VideoThoth();
		t2.setDate(new Date(now-DAY*3*1000+HOUR*5*1000));
		Thoth t3 = new TextThoth();
		t3.setDate(new Date(now-DAY*1000-HOUR*16*1000));
		Thoth t4 = new TextThoth();
		t4.setDate(new Date(now-DAY*1000));
		this.getThoths().add(t1,t2,t3,t4);
	}

	private void initResources() {
		this.thoths = new Thoths();
		
		Resources res = this.getContext().getResources();
		
		icons = new Bitmap[3];
		loadIcon(BMP_TEXT_THOTH, res.getDrawable( R.drawable.text ) );
		loadIcon(BMP_PHOTO_THOTH, res.getDrawable( R.drawable.photo ) );
		loadIcon(BMP_VIDEO_THOTH, res.getDrawable( R.drawable.video ) );
	}

	private void loadIcon(int key, Drawable drawable) {
		icons[key] = loadBitmap(drawable,THOTH_ICON_WIDTH,THOTH_ICON_HEIGHT);
	}

	private Bitmap loadBitmap(Drawable drawable, int width, int height) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public void setStartDate(Date newStartDate) {
		startDate = newStartDate;
		endDate = new Date( startDate.getTime() + visibleTime*1000 );
		invalidate();
	}

	public float getScale() {
		return scale;
	}

	public long getVisibleTime() {
		return visibleTime;
	}

	public void setVisibleTime(long newVisibleTime) {
		visibleTime = newVisibleTime;
		scale = newVisibleTime / getWidth();
		setStartDate(startDate); //do this so the end date gets recalculated
		//no need to call invalidate() because setStartDate() will
	}

	public Thoths getThoths() {
		return thoths;
	}
	
	public void addThoths(Collection<Thoth> thoths){
		this.thoths.add(thoths);
		invalidate();
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

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		setVisibleTime(visibleTime); //do this so the scale gets recalculated
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBackground(canvas);
		drawAxis(canvas);
		drawThoths(canvas);
	}
	
	protected void drawThoths(Canvas canvas) {
		Set<Thoth> ts = this.thoths.getInterval(startDate, endDate);
		Random r = new Random();
		int maxY = getHeight() - axisHeight - THOTH_ICON_HEIGHT;
		
		for(Thoth t : ts){
			long diff = (t.getDate().getTime() - startDate.getTime())/1000;
			int x = (int)(diff / scale);
			int y = r.nextInt() % maxY;
			int iconId = getIconId(t);
			canvas.drawBitmap(icons[iconId],x,y,null);
		}
	}

	private int getIconId(Thoth t) {
		if(t instanceof PictureThoth){
			return BMP_PHOTO_THOTH;
		}else if(t instanceof VideoThoth){
			return BMP_VIDEO_THOTH;
		}else{
			return BMP_TEXT_THOTH;
		}
	}

	protected void drawAxis(Canvas canvas) {
		int y = getHeight() - getAxisHeight();
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
		}
	}

	protected void drawBackground(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY);
	}
	
	private long detectGridInterval() {
		long difTime = getEndDate().getTime() - getStartDate().getTime();
		if(difTime < DAY){
			return HOUR; //hourly
		}else if(difTime < WEEK){
			return DAY; //daily
		}else if(difTime < MONTH){
			return WEEK; //weekly
		}else{
			return MONTH;
		}
	}
}
