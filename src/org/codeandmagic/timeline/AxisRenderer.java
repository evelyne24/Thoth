package org.codeandmagic.timeline;

import android.graphics.Canvas;

public interface AxisRenderer {
	public void renderAxis(Canvas canvas, TimelineRenderingContext context);

	public float getLabelAreaHeight();
}
