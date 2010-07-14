package org.codeandmagic.thoth.timeline;

import java.util.SortedSet;

import org.codeandmagic.thoth.data.Thoth;

/**
 * {@link HorizontalLayout} which positions {@link Thoth}s linearly based on a provided scaling factor.
 * @author cristi
 *
 */
public class LiniarHorizontalLayout implements HorizontalLayout {
	
	/**
	 * The scale factor represents how many time units (milliseconds) are compacted into one pixel  
	 */
	private float scale;

	public LiniarHorizontalLayout(){
	}
	
	public LiniarHorizontalLayout(float scale){
		this.scale = scale;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float[] computeX(SortedSet<Thoth> thoths) {
		float[] x = new float[thoths.size()];
		
		Thoth first = thoths.first();
		long t0 = first.getDate().getTime();
		
		int i = 0;
		for(Thoth t : thoths){
			x[i++] = (t.getDate().getTime() - t0) / scale;
		}
		
		return x;
	}
}
