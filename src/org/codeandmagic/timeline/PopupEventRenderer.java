package org.codeandmagic.timeline;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class PopupEventRenderer implements EventDetailsRenderer {

	public class PopupView extends LinearLayout {

		public PopupView(final Context context) {
			super(context);
			setBackgroundColor(Color.WHITE);

			final TextView label = new TextView(context);
			label.setText("POP-UUUUPPP!");
			addView(label);
		}

	}

	public void eventSelected(final TimelineView timelineView, final Event event, final float eventX, final float eventY) {
		timelineView.addView(new PopupView(timelineView.getContext()));
	}

}
