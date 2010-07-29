package org.codeandmagic.timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codeandmagic.timeline.AxisHorizontalLayout.AxisHint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

public class DefaultAxisRenderer implements AxisRenderer {

	private int height;
	private Paint linePaint;
	private Paint labelPaint;
	private Paint label2Paint;
	private DateFormat hourFormat;
	private DateFormat dayFormat;

	public DefaultAxisRenderer() {
		super();

		// set defaults
		setHeight(20);

		final Paint separatorPaint = new Paint();
		separatorPaint.setColor(Color.WHITE);
		separatorPaint.setPathEffect(new DashPathEffect(new float[] { 3, 3 }, 0));
		setLinePaint(separatorPaint);

		final Paint labelPaint = new Paint();
		labelPaint.setColor(Color.WHITE);
		setLabelPaint(labelPaint);

		final Paint label2Paint = new Paint();
		label2Paint.setColor(Color.YELLOW);
		setLabel2Paint(label2Paint);

		setHourFormat("HH:mm");

		setDayFormat("dd MMM");
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public Paint getLinePaint() {
		return linePaint;
	}

	public void setLinePaint(final Paint paint) {
		linePaint = paint;
	}

	public DateFormat getHourFormat() {
		return hourFormat;
	}

	public void setHourFormat(final String format) {
		hourFormat = new SimpleDateFormat(format);
	}

	public void setDayFormat(final String format) {
		dayFormat = new SimpleDateFormat(format);
	}

	public DateFormat getDayFormat() {
		return dayFormat;
	}

	public void setLabelPaint(final Paint labelPaint) {
		this.labelPaint = labelPaint;
	}

	public Paint getLabelPaint() {
		return labelPaint;
	}

	public void renderAxis(final Canvas canvas, final TimelineRenderingContext context) {
		renderDecorations(canvas, context);
		renderSeparators(canvas, context);
	}

	private void renderDecorations(final Canvas canvas, final TimelineRenderingContext context) {
		final Paint p = new Paint();
		p.setColor(Color.WHITE);
		final float y = context.getViewHeight() - height;
		canvas.drawLine(0, y, context.getViewWidth(), y, p);
	}

	private void renderSeparators(final Canvas canvas, final TimelineRenderingContext context) {
		final float startX = context.getViewStartX();
		final float endX = context.getViewEndX();
		final AxisHint[] hints = context.getAxisX();

		if (hints.length == 0)
			return;

		final float separatorHeight = context.getViewHeight() - height;

		int i = 0;
		while (i < hints.length && hints[i].x <= endX) {
			renderSeparator(canvas, hints[i].date, hints[i].x - startX, separatorHeight, context);
			++i;
		}
	}

	private void renderSeparator(final Canvas canvas, final Date date, final float x, final float height,
			final TimelineRenderingContext context) {
		canvas.drawLine(x, 0, x, height, linePaint);
		if (date.getHours() == 0) {
			canvas.drawText(dayFormat.format(date), x - 15, height + 15, label2Paint);
		}
		else {
			canvas.drawText(hourFormat.format(date), x - 15, height + 15, labelPaint);
		}

	}

	public void setLabel2Paint(final Paint label2Paint) {
		this.label2Paint = label2Paint;
	}

	public Paint getLabel2Paint() {
		return label2Paint;
	}
}
