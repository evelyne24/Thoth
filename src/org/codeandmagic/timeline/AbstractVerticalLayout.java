package org.codeandmagic.timeline;


/**
 * Abstract parent for {@link EventVerticalLayout}s which requires the instatiation with
 * a minimum and a maximum allowed value for Y.
 * @author cristi
 *
 */
public abstract class AbstractVerticalLayout implements EventVerticalLayout {
	
	private float minY;
	private float maxY;
	
	public AbstractVerticalLayout(){}

	public AbstractVerticalLayout(float minY, float maxY){
		this.minY = minY;
		this.maxY = maxY;
	}

	public float getMinY() {
		return minY;
	}

	public float getMaxY() {
		return maxY;
	}

	public void setMinY(float minY) {
		this.minY = minY;
	}

	public void setMaxY(float maxY) {
		this.maxY = maxY;
	}
	
}
