package org.codeandmagic.thoth.timeline;

import java.util.SortedSet;

import org.codeandmagic.thoth.data.Thoth;

/**
 * Basic implementation for {@link VerticalLayout} which positions all thoths 
 * on the middle of the timeline (vertically).
 * @author cristi
 *
 */
public class MiddleVerticalLayout extends AbstractVerticalLayout{
	
	public MiddleVerticalLayout(float minY, float maxY) {
		super(minY, maxY);
	}

	public float[] computeY(SortedSet<Thoth> thoths, float[] x) {
		float[] y = new float[x.length];
		float middle = (getMaxY()-getMinY())/2;
		for(int i=0;i<x.length;i++){
			y[i] = middle;
		}
		return y;
	}

}
