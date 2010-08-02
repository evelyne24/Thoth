package org.codeandmagic.timeline;

/**
 * Interface implemented by the class responsible of calculating the vertical positioning of a set of {@link Event}s on a
 * timeline.
 * 
 * @author cristi
 * 
 */
public interface EventVerticalLocator {
	/**
	 * Computes the vertical position (Y axis) for all events in the current visible "frame". The vertical limits are 0 and
	 * {@link TimelineView#getHeight()}
	 * <p style="color:red">
	 * The contract of this method guarantees {@link TimelineRenderingContext#setEventsY(org.codeandmagic.util.FakeArrayFloat)} is
	 * going to get called during the execution of this function.
	 * </p>
	 * 
	 * @param context
	 */
	public void computeY(TimelineRenderingContext context);
}
