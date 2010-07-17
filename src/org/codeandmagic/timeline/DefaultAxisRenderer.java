package org.codeandmagic.timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codeandmagic.timeline.AxisHorizontalLayout.AxisHint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DefaultAxisRenderer implements AxisRenderer{
	
	private int height;
	private Paint linePaint;
	private Paint labelPaint;
	private String format;
	private DateFormat df;

	public DefaultAxisRenderer(int height, Paint linePaint, Paint labelPaint, String format) {
		super();
		setHeight(height);
		setLinePaint(linePaint);
		setLabelPaint(labelPaint);
		setFormat(format);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Paint getLinePaint() {
		return linePaint;
	}

	public void setLinePaint(Paint paint) {
		this.linePaint = paint;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		this.df = new SimpleDateFormat(format);
	}

	public void setLabelPaint(Paint labelPaint) {
		this.labelPaint = labelPaint;
	}

	public Paint getLabelPaint() {
		return labelPaint;
	}

	public void renderAxis(Canvas canvas, TimelineRenderingContext context) {
		renderDecorations(canvas,context);
		renderSeparators(canvas,context);
	}

	private void renderDecorations(Canvas canvas, TimelineRenderingContext context) {
		Paint p =  new Paint();
		p.setColor(Color.WHITE);
		float y = context.getViewHeight()-height;
		canvas.drawLine(0, y, context.getViewWidth(), y, p);
	}

	private void renderSeparators(Canvas canvas, TimelineRenderingContext context) {
		float startX = context.getStartX();
		float endX = context.getEndX();
		AxisHint[] hints = context.getAxisX();
		
		int i0 = 0;
		while(hints[i0].x < startX){
			++i0;
		}
		float diff = hints[i0].x - startX;
		
		int i = 0;
		float separatorHeight = context.getViewHeight() - height;
		while(i < hints.length && hints[i].x <= endX){
			renderSeparator(canvas, hints[i].date, hints[i].x+diff, separatorHeight, context);
			++i;
		}	
	}

	private void renderSeparator(Canvas canvas, Date date, float x, float height, TimelineRenderingContext context) {
		canvas.drawLine(x, 0, x, height, linePaint);
		canvas.drawText(df.format(date), x, height+15, labelPaint);
	}
}
