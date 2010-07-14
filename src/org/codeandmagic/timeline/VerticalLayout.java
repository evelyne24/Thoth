package org.codeandmagic.timeline;

import java.util.SortedSet;

/**
 * Interface implemented by the class responsible of calculating the vertical positioning
 * of a set of {@link Event}s on a timeline.
 * @author cristi
 *
 */
public interface VerticalLayout {
	/**
	 * Computes the verical position (Y axis) for all events in the provided set.
	 * @param thoths
	 * @param x The x positions for all events in the set.
	 * @return
	 */
	public float[] computeY(SortedSet<Event> thoths, float[] x);
}
