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
package org.codeandmagic.thoth.timeline;

import org.codeandmagic.timeline.Event;
import org.codeandmagic.timeline.PopupEventRenderer;
import org.codeandmagic.timeline.TimelineView;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThothEventRenderer extends PopupEventRenderer {

	@Override
	public View getView(final Context context, final TimelineView timelineView, final Event event) {
		final LinearLayout view = new LinearLayout(context);
		final TextView label = new TextView(context);
		label.setText(event.getName());
		view.addView(label);
		return view;
	}

}
