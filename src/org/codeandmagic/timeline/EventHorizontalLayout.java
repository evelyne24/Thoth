package org.codeandmagic.timeline;

import java.util.SortedSet;

/**
 * Interface implemented by the class responsible of calculating the horizontal positioning of a set of {@link Event}s on a timeline.
 * 
 * @author cristi
 * 
 */
public interface EventHorizontalLayout {
	/**
	 * Computes the horizontal position (X axis) for all events in the provided set.
	 * 
	 * @param thots
	 * @param context
	 * @return
	 */
	public float[] computeX(SortedSet<Event> events, TimelineRenderingContext context);
}
