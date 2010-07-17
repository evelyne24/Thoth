package org.codeandmagic.timeline;

import java.util.SortedSet;

/**
 * Basic implementation for {@link EventVerticalLayout} which positions all events 
 * on the middle of the timeline (vertically).
 * @author cristi
 *
 */
public class MiddleVerticalLayout implements EventVerticalLayout{
	
	public float[] computeY(SortedSet<Event> thoths, TimelineRenderingContext context) {
		float[] x = context.getEventsX();
		float[] y = new float[x.length];
		float middle = context.getViewHeight()/2;
		for(int i=0;i<x.length;i++){
			y[i] = middle;
		}
		return y;
	}

}
