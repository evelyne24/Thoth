package org.codeandmagic.timeline;

import java.util.SortedSet;

/**
 * {@link EventHorizontalLayout} which positions {@link Event}s linearly based on a provided scaling factor.
 * @author cristi
 *
 */
public class LiniarHorizontalLayout implements EventHorizontalLayout {
	
	/**
	 * The scale factor represents how many time units (milliseconds) are compacted into one pixel  
	 */
	private float scale;

	public LiniarHorizontalLayout(){
	}
	
	public LiniarHorizontalLayout(float scale){
		this.scale = scale;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float[] computeX(SortedSet<Event> events, TimelineRenderingContext context) {
		float[] x = new float[events.size()];
		
		Event first = events.first();
		long t0 = first.getDate().getTime();
		
		int i = 0;
		for(Event t : events){
			x[i++] = (t.getDate().getTime() - t0) / scale;
		}
		
		return x;
	}
}
