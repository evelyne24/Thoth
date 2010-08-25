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
 * Interface for classes that are responsible with rendering the background for the current "frame". The background will be the
 * first layer to be drawn on the {@link TimelineView} {@link Canvas}.
 * 
 * @author cristi
 * 
 */
public interface BackgroundRenderer {
	public void renderBackground(Canvas canvas, TimelineRenderingContext context);
}
