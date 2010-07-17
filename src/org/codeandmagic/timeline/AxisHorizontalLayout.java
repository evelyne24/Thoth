package org.codeandmagic.timeline;

import java.util.Date;

public interface AxisHorizontalLayout {
	public AxisHint[] computeX(TimelineRenderingContext context);
	public AxisHint getLeftXFactor();
	public AxisHint getRightXFactor();
	
	public static class AxisHint{
		public AxisHint(float x, Date date){
			this.x = x;
			this.date = date;
		}
		public final float x;
		public final Date date;
		public String toString(){
			return x+"-"+date; 
		}
	}
}
