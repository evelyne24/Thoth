package org.codeandmagic.timeline;

import java.util.Date;

/**
 * Interface implemented by classes responsible with computing the axis positions in the visible "frame". Both the horizontal
 * position of the axis and the time representing it needs to be calculated.
 * <p style="color:red">
 * The contract of this methods guarantees that the {@link TimelineRenderingContext#setAxisX(AxisHint[])} will be called during
 * the execution of this function.
 * </p>
 * 
 * @author cristi
 * 
 */
public interface AxisHorizontalLocator {
	public void computeX(TimelineRenderingContext context);

	public static class AxisHint {
		public AxisHint(final float x, final Date date) {
			this.x = x;
			this.date = date;
		}

		public final float x;
		public final Date date;

		@Override
		public String toString() {
			return x + "-" + date;
		}
	}
}
