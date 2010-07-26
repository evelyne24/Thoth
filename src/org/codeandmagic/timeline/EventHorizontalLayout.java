package org.codeandmagic.timeline;

/**
 * Interface implemented by the class responsible of calculating the horizontal positioning of a set of {@link Event}s on a
 * timeline.
 * 
 * @author cristi
 * 
 */
public interface EventHorizontalLayout {
	/**
	 * Computes the list of events and their horizontal (X) position that fit in the current visible "frame":
	 * {@link TimelineRenderingContext#getViewStartX()} to {@link TimelineRenderingContext#getViewEndX()}.
	 * <p style="color:red">
	 * The contract of this function guarantees that this method calls {@link TimelineRenderingContext#setFirtEventIndex(int)} ,
	 * {@link TimelineRenderingContext#setLastEventIndex(int)} , and
	 * {@link TimelineRenderingContext#setEventsX(org.codeandmagic.util.FakeArrayFloat)}
	 * </p>
	 * 
	 * @param context
	 */
	public void computeX(final TimelineRenderingContext context);
}
