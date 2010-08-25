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

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public abstract class PopupEventRenderer implements EventDetailsRenderer {

	private int width;
	private int height;

	public PopupEventRenderer() {
		setWidth(400);
		setHeight(200);
	}

	public class PopupView extends RelativeLayout {

		public PopupView(final TimelineView timelineView) {
			super(timelineView.getContext());
			setBackgroundColor(Color.WHITE);
		}
	}

	public abstract View getView(final Context context, final TimelineView timelineView, final Event event);

	public void eventSelected(final TimelineView timelineView, final Event event, final float eventX, final float eventY) {
		final PopupView popup = new PopupView(timelineView);
		popup.addView(getView(timelineView.getContext(), timelineView, event), width, height);
		timelineView.addView(popup, width, height);
		final RelativeLayout.LayoutParams params = (LayoutParams) popup.getLayoutParams();
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}
}
