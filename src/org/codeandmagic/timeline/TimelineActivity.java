package org.codeandmagic.timeline;

import java.util.Date;

import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.Thoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.thoth.timeline.ThothEventRenderer;
import org.codeandmagic.thoth.timeline.ThothIconRenderer;
import org.codeandmagic.util.TimeUtils;

import android.app.Activity;
import android.os.Bundle;

public class TimelineActivity extends Activity {

	private TimelineView tv;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final LinearEventHorizontalLayout ehl = new LinearEventHorizontalLayout();
		final MiddleVerticalLayout evl = new MiddleVerticalLayout();
		final ThothIconRenderer eir = new ThothIconRenderer();
		final LinearAxisHorizontalLayout ahl = new LinearAxisHorizontalLayout(ehl);
		final DefaultAxisRenderer ar = new DefaultAxisRenderer();
		final ColorBackgroundRenderer br = new ColorBackgroundRenderer();
		final ThothEventRenderer er = new ThothEventRenderer();

		tv = new TimelineView(this, ehl, evl, eir, ahl, ar, br, er);
		setTestData();

		setContentView(tv);
	}

	private void setTestData() {
		final long now = (new Date()).getTime();
		final Thoth t1 = new PictureThoth();
		t1.setDate(new Date(now - TimeUtils.DAY * 2));
		final Thoth t2 = new VideoThoth();
		t2.setDate(new Date(now - TimeUtils.DAY * 3 + TimeUtils.HOUR * 5));
		final Thoth t3 = new TextThoth();
		t3.setDate(new Date(now - TimeUtils.DAY - TimeUtils.HOUR * 16));
		final Thoth t4 = new TextThoth();
		t4.setDate(new Date(now - TimeUtils.DAY));
		tv.getEvents().add(t1, t2, t3, t4);
	}

}
