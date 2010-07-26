package org.codeandmagic.timeline;

/**
 * Interface implemented by the class responsible of calculating the vertical positioning of a set of {@link Event}s on a
 * timeline.
 * 
 * @author cristi
 * 
 */
public interface EventVerticalLayout {
	/**
	 * Computes the verical position (Y axis) for all events in the provided set.
	 * 
	 * @param context
	 */
	public void computeY(TimelineRenderingContext context);
}
