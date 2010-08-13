package org.codeandmagic.timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.codeandmagic.thoth.R;
import org.codeandmagic.util.FakeArrayFloat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Implementation of {@link EventIconRenderer} that draws an image for the event and optionally a text.
 * 
 * @author cristi
 * 
 */
public class StaticEventIconRenderer implements EventIconRenderer, TimelineViewAware {
	private int iconWidth;
	private int iconHeight;
	private int defaultIconDrawable;
	private Bitmap defaultIcon;
	private EventLabelFormat labelFormat;
	private int labelLeft;
	private int labelTop;
	private Paint labelPaint;

	public StaticEventIconRenderer() {
		iconWidth = 16;
		iconHeight = 16;
		defaultIconDrawable = R.drawable.event;
		labelFormat = new NameEventLabelFormat();
		labelLeft = 20;
		labelTop = 11;
		labelPaint = new Paint();
		labelPaint.setColor(Color.WHITE);
	}

	protected Bitmap loadBitmap(final Drawable drawable, final int width, final int height) {
		final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

	public void renderIcon(final Canvas canvas, final Event event, final float x, final float y) {
		final Bitmap b = getIconForEvent(event);
		canvas.drawBitmap(b, x, y, null);
		canvas.drawText(labelFormat.getLabel(event), x + labelLeft, y + labelTop, labelPaint);
	}

	public boolean isHit(final Event event, final float eventX, final float eventY, final float clickX, final float clickY) {
		return clickX > eventX && clickX < eventX + iconWidth && clickY > eventY && clickY < eventY + iconHeight;
	}

	public void renderIcons(final Canvas canvas, final TimelineRenderingContext context) {
		final float startX = context.getViewStartX();
		final float endX = context.getViewEndX();

		final int startIndx = context.getFirtEventIndex();
		final int endIndx = context.getLastEventIndex();

		if (startIndx < 0 || endIndx < 0 || endIndx < startIndx)
			return;

		final List<Event> events = context.getTimeline().getEvents().getEvents();
		final FakeArrayFloat eventsX = context.getEventsX();
		final FakeArrayFloat eventsY = context.getEventsY();

		final int nrEvents = eventsX.size();

		for (int i = 0, j = startIndx; i < nrEvents; ++i, ++j) {
			final float x = eventsX.get(i);
			final float y = eventsY.get(i);
			final Event event = events.get(j);
			if (x > endX) {
				break;
			}
			renderIcon(canvas, event, x - startX, y);
		}
	}

	public void preloadIcons(final Context context) {
		final Resources res = context.getResources();
		defaultIcon = loadBitmap(res.getDrawable(defaultIconDrawable), iconWidth, iconHeight);
	}

	public Bitmap getIconForEvent(final Event event) {
		return defaultIcon;
	}

	public void timelineViewContructed(final TimelineView timelineView) {
		preloadIcons(timelineView.getContext());
	}

	public void timelineViewEventsChanged(final TimelineView timelineView, final Events events, final Collection<Event> added,
			final Collection<Event> removed) {
	}

	public void timelineViewSizeChanged(final TimelineView timelineView, final int w, final int h, final int oldw, final int oldh) {
	}

	public void timelineViewEventSelected(final TimelineView timelineView, final Event event, final float eventX,
			final float eventY) {
	}

	public int getIconWidth() {
		return iconWidth;
	}

	public int getIconHeight() {
		return iconHeight;
	}

	public int getDefaultIconDrawable() {
		return defaultIconDrawable;
	}

	public void setIconWidth(final int iconWidth) {
		this.iconWidth = iconWidth;
	}

	public void setIconHeight(final int iconHeight) {
		this.iconHeight = iconHeight;
	}

	public void setDefaultIconDrawable(final int defaultIconId) {
		defaultIconDrawable = defaultIconId;
	}

	public EventLabelFormat getLabelFormat() {
		return labelFormat;
	}

	public void setLabelFormat(final EventLabelFormat labelFormat) {
		this.labelFormat = labelFormat;
	}

	public int getLabelLeft() {
		return labelLeft;
	}

	public void setLabelLeft(final int labelHorizontalMargin) {
		labelLeft = labelHorizontalMargin;
	}

	public int getLabelTop() {
		return labelTop;
	}

	public void setLabelTop(final int labelVerticalMargin) {
		labelTop = labelVerticalMargin;
	}

	public Paint getLabelPaint() {
		return labelPaint;
	}

	public void setLabelPaint(final Paint labelPaint) {
		this.labelPaint = labelPaint;
	}

	/**
	 * A {@link EventLabelFormat} can be injected into a {@link StaticEventIconRenderer} to determine what label to draw next to
	 * the event icon;
	 * 
	 * @author cristi
	 * 
	 */
	public static interface EventLabelFormat {
		public String getLabel(Event e);
	}

	/**
	 * An {@link EventLabelFormat} that return the event name. You can set a maximum number of characters to be returned by
	 * calling {@link #setMaxChars(int)}.
	 * 
	 * @author cristi
	 * @see #setMaxChars(int)
	 * 
	 */
	public static class NameEventLabelFormat implements EventLabelFormat {
		private int maxChars = -1;
		private final static String TAIL = "...";

		public String getLabel(final Event e) {
			final String fullName = e.getName();
			if (maxChars == -1 || maxChars <= fullName.length() - 3)
				return fullName;
			else
				return new StringBuilder(fullName).substring(0, maxChars - 3).concat(TAIL).toString();
		}

		public int getMaxChars() {
			return maxChars;
		}

		/**
		 * Sets the maximum number of characters to be returned. If the name is longer than {@link #getMaxChars()} the name will
		 * be trimmed and three dots will be added (...) <br/>
		 * Use -1 if you don't want the name to be trimmed (default).
		 * 
		 * @param maxChars
		 */
		public void setMaxChars(final int maxChars) {
			this.maxChars = maxChars;
		}
	}

	/**
	 * Subclass of {@link NameEventLabelFormat} that in addition to the {@link Event} name, it also displays the date of the event
	 * 
	 * @author cristi
	 * 
	 */
	public static class TimeAndNameEventLabelFormat extends NameEventLabelFormat {

		private String dateFormat = "MMM d";
		private DateFormat format = new SimpleDateFormat(dateFormat);
		private boolean dateInFront = true;

		@Override
		public String getLabel(final Event e) {
			if (dateInFront)
				return format.format(e.getDate()) + super.getLabel(e);
			else
				return super.getLabel(e) + format.format(e.getDate());
		}

		public String getDateFormat() {
			return dateFormat;
		}

		/**
		 * The pattern for {@link SimpleDateFormat} used to format the date of the {@link Event}
		 * 
		 * @param dateFormat
		 */
		public void setDateFormat(final String dateFormat) {
			this.dateFormat = dateFormat;
			format = new SimpleDateFormat(dateFormat);
		}

		public boolean isDateInFront() {
			return dateInFront;
		}

		/**
		 * If set to true, the date will be appended before the name of the event, otherwise will be appended after the name of
		 * the event.
		 * 
		 * @param head
		 */
		public void setDateInFront(final boolean head) {
			dateInFront = head;
		}
	}
}
