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
