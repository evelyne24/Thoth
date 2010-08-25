/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All Rights Reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify it under
	the terms of the GNU Lesser General Public License as published by the
	Free Software Foundation, either version 3 of the License, or (at your
	option) any later version.

	Thoth is distributed in the hope that it will be useful, but WITHOUT 
	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public 
	License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.timeline;

import android.graphics.Canvas;

/**
 * Interface for class responsible with rendering the {@link Event} icons onto the timeline. Because the {@link EventIconRenderer}
 * is the only one that actually knows the shape and size of the event icons, implementing classes will also be responsible with
 * detecting collisions between mouse and an event icon.
 * 
 * @author cristi
 * 
 */
public interface EventIconRenderer {
	/**
	 * Renders the events visible in the current frame onto the {@link Canvas} of the {@link TimelineView}.<br/>
	 * The {@link TimelineRenderingContext} provides the index of the first and last visible {@link Event}s through the
	 * {@link TimelineRenderingContext#getFirtEventIndex()} and {@link TimelineRenderingContext#getTimeline()} methods.Then they
	 * can be extracted from {@link TimelineRenderingContext#getTimeline()} -> {@link TimelineView#getEvents()} ->
	 * {@link Events#getEvents()} <br />
	 * The the horizontal positioning of the events can be found in {@link TimelineRenderingContext#getEventsX()}. The vertical
	 * position can be found in {@link TimelineRenderingContext#getEventsY()}
	 * 
	 * @param canvas
	 * @param renderingContext
	 * 
	 * @see TimelineRenderingContext#getFirtEventIndex()
	 * @see TimelineRenderingContext#getLastEventIndex()
	 * @see TimelineView#getEvents()
	 */
	public void renderIcons(Canvas canvas, TimelineRenderingContext renderingContext);

	/**
	 * Determines if an event icon that is positioned at the given coordinates intersects the other point given by the other
	 * coordinates.
	 * 
	 * @param event
	 * @param eventX
	 * @param eventY
	 * @param clickX
	 * @param clickY
	 * @return
	 */
	public boolean isHit(Event event, float eventX, float eventY, float clickX, float clickY);
}
