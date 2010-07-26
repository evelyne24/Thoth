package org.codeandmagic.timeline;

import android.graphics.Canvas;

/**
 * Interface for class responsible with rendering the Event icons onto the timeline.
 * 
 * @author cristi
 * 
 */
public interface EventIconRenderer {
	public void renderIcons(Canvas canvas, TimelineRenderingContext renderingContext);
}
