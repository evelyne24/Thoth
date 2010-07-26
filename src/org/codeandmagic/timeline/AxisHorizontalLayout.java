package org.codeandmagic.timeline;

import java.util.Date;

public interface AxisHorizontalLayout {
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
