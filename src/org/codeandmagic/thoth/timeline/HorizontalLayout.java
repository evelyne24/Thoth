package org.codeandmagic.thoth.timeline;

import java.util.SortedSet;

import org.codeandmagic.thoth.data.Thoth;

/**
 * Interface implemented by the class responsible of calculating the horizontal positioning
 * of a set of {@link Thoth}s on a timeline.
 * @author cristi
 *
 */
public interface HorizontalLayout {
	/**
	 * Computes the horizontal position (X axis) for all thoths in the provided set.
	 * @param thots
	 * @return
	 */
	public float[] computeX(SortedSet<Thoth> thoths);
}
