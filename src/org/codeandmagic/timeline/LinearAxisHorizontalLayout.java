package org.codeandmagic.timeline;

import java.util.Date;
import java.util.SortedSet;

public class LinearAxisHorizontalLayout implements AxisHorizontalLayout {

	private long timeStep;
	
	public LinearAxisHorizontalLayout(long timeStep){
		setTimeStep(timeStep);
	}
	
	public AxisHint[] computeX(TimelineRenderingContext context) {
		if(!(context.getTimeline().getHorizontalLayout() instanceof LinearEventHorizontalLayout)){
			throw new IllegalArgumentException("LinearAxisHorizontalLayout can only work with a LinearEventHorizontalLayout but "
					+context.getTimeline().getHorizontalLayout().getClass().getName()+" was found instead!");
		}
		SortedSet<Event> events = context.getTimeline().getEvents().getEvents();
		
		//int timeScale = detectTimeScale();
		
		Date firstEvent = events.first().getDate();
		Date lastEvent = events.last().getDate();
		
		//Date first = TimeUtil.floor( firstEvent, timeScale );
		//Date last = TimeUtil.ceiling( lastEvent, timeScale );
		
		//long firstDif = firstEvent.getTime() - first.getTime();
		//long lastDif = last.getTime() - lastEvent.getTime();*/
		
		float scaleFactor = ((LinearEventHorizontalLayout)context.getTimeline().getHorizontalLayout()).getScale();
		float xStep = timeStep/scaleFactor;
		
		float x = 0;
		long t0 = firstEvent.getTime();
		long tn = lastEvent.getTime();
		long t = t0;
		int i = 0;
		
		int nr = (int) ((tn-t0)/timeStep) + 1;
		AxisHint[] hints = new AxisHint[nr];
		
		while (t <= tn){
			hints[i++] = new AxisHint(x, new Date(t));
			x += xStep;
			t += timeStep;
		}
		
		return hints;
	}

	public AxisHint getLeftXFactor() {
		// TODO Auto-generated method stub
		return null;
	}

	public AxisHint getRightXFactor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTimeStep(long timeStep) {
		this.timeStep = timeStep;
	}

	public long getTimeStep() {
		return timeStep;
	}

}
