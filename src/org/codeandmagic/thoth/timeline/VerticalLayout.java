package org.codeandmagic.thoth.timeline;

import java.util.SortedSet;

import org.codeandmagic.thoth.data.Thoth;

/**
 * Interface implemented by the class responsible of calculating the vertical positioning
 * of a set of {@link Thoth}s on a timeline.
 * @author cristi
 *
 */
public interface VerticalLayout {
	/**
	 * Computes the verical position (Y axis) for all thoths in the provided set.
	 * @param thoths
	 * @param x The x positions for all thoths in the set.
	 * @return
	 */
	public float[] computeY(SortedSet<Thoth> thoths, float[] x);
}
