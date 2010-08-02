package org.codeandmagic.timeline;

/**
 * Interface implemented by the class responsible of calculating the horizontal positioning of a set of {@link Event}s on a
 * timeline.
 * 
 * @author cristi
 * 
 */
public interface EventHorizontalLocator {
	/**
	 * Computes the list of events and their horizontal (X axis) position that fit in the current visible "frame":
	 * {@link TimelineRenderingContext#getViewStartX()} to {@link TimelineRenderingContext#getViewEndX()}.
	 * <p style="color:red">
	 * The contract of this method guarantees {@link TimelineRenderingContext#setFirtEventIndex(int)} ,
	 * {@link TimelineRenderingContext#setLastEventIndex(int)} , and
	 * {@link TimelineRenderingContext#setEventsX(org.codeandmagic.util.FakeArrayFloat)} will be called during the execution of
	 * this function.
	 * </p>
	 * 
	 * @param context
	 */
	public void computeX(final TimelineRenderingContext context);
}
