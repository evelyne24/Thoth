package org.codeandmagic.timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codeandmagic.timeline.AxisHorizontalLayout.AxisHint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DefaultAxisRenderer implements AxisRenderer {

	private int height;
	private Paint linePaint;
	private Paint labelPaint;
	private String format;
	private DateFormat df;

	public DefaultAxisRenderer(final int height, final Paint linePaint, final Paint labelPaint, final String format) {
		super();
		setHeight(height);
		setLinePaint(linePaint);
		setLabelPaint(labelPaint);
		setFormat(format);
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

	public String getFormat() {
		return format;
	}

	public void setFormat(final String format) {
		this.format = format;
		df = new SimpleDateFormat(format);
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

		final float diff = hints[0].x - startX;

		int i = 0;
		final float separatorHeight = context.getViewHeight() - height;
		while (i < hints.length && hints[i].x <= endX) {
			renderSeparator(canvas, hints[i].date, hints[i].x + diff, separatorHeight, context);
			++i;
		}
	}

	private void renderSeparator(final Canvas canvas, final Date date, final float x, final float height,
			final TimelineRenderingContext context) {
		canvas.drawLine(x, 0, x, height, linePaint);
		canvas.drawText(df.format(date), x, height + 15, labelPaint);
	}
}
