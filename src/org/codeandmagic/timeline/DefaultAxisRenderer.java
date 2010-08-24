/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All rights reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify
	it under the terms of the GNU Lesser General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	Thoth is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codeandmagic.timeline.AxisLocator.AxisHint;

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

	public void renderAxis(final Canvas canvas, final TimelineRenderingContext context) {
		renderDecorations(canvas, context);
		renderSeparators(canvas, context);
	}

	private void renderDecorations(final Canvas canvas, final TimelineRenderingContext context) {
		final Paint p = new Paint();
		p.setColor(Color.WHITE);
		final float y = context.getTimeline().getHeight() - height;
		canvas.drawLine(0, y, context.getTimeline().getWidth(), y, p);
	}

	private void renderSeparators(final Canvas canvas, final TimelineRenderingContext context) {
		final float startX = context.getViewStartX();
		final float endX = context.getViewEndX();
		final AxisHint[] hints = context.getAxisX();

		if (hints.length == 0)
			return;

		final float separatorHeight = context.getTimeline().getHeight() - height;

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

	public void setLabel2Paint(final Paint label2Paint) {
		this.label2Paint = label2Paint;
	}

	public Paint getLabel2Paint() {
		return label2Paint;
	}

	public float getLabelAreaHeight() {
		return getHeight();
	}
}
