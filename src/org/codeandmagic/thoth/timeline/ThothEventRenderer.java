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
