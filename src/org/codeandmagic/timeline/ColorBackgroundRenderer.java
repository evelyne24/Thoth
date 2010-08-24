/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All rights reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify
	it under the terms of the GNU Lesser General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	Thoth is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.timeline;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * {@link BackgroundRenderer} that draws a solid color onto the canvas.
 * 
 * @author cristi
 * 
 */
public class ColorBackgroundRenderer implements BackgroundRenderer {

	private int color;

	public ColorBackgroundRenderer() {
		// set defaults
		setColor(Color.LTGRAY);
	}

	public int getColor() {
		return color;
	}

	public void setColor(final int color) {
		this.color = color;
	}

	public void renderBackground(final Canvas canvas, final TimelineRenderingContext context) {
		canvas.drawColor(Color.DKGRAY);
	}

}
