package org.codeandmagic.thoth;

import java.util.Date;

import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.Thoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.thoth.timeline.ThothIconRenderer;
import org.codeandmagic.timeline.ColorBackgroundRenderer;
import org.codeandmagic.timeline.LinearAxisHorizontalLayout;
import org.codeandmagic.timeline.LinearEventHorizontalLayout;
import org.codeandmagic.timeline.DefaultAxisRenderer;
import org.codeandmagic.timeline.MiddleVerticalLayout;
import org.codeandmagic.timeline.TimelineView;
import org.codeandmagic.util.TimeUtil;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;

public class MainScreen extends Activity {

	TimelineView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Paint separatorPaint = new Paint();
		separatorPaint.setColor(Color.WHITE);
		separatorPaint.setPathEffect(new DashPathEffect(new float[] { 3, 3 }, 0));
		Paint labelPaint = new Paint();
		labelPaint.setColor(Color.WHITE);
		tv = new TimelineView(this, new LinearEventHorizontalLayout(TimeUtil.DAY * 3 / 600), new MiddleVerticalLayout(), new ThothIconRenderer(),
				new LinearAxisHorizontalLayout(TimeUtil.HOUR * 6), new DefaultAxisRenderer(20, separatorPaint, labelPaint, "H:m"), new ColorBackgroundRenderer(
						Color.LTGRAY));
		setTestData();
		setContentView(tv);
	}

	private void setTestData() {
		long now = (new Date()).getTime();
		Thoth t1 = new PictureThoth();
		t1.setDate(new Date(now - TimeUtil.DAY * 2));
		Thoth t2 = new VideoThoth();
		t2.setDate(new Date(now - TimeUtil.DAY * 3 + TimeUtil.HOUR * 5));
		Thoth t3 = new TextThoth();
		t3.setDate(new Date(now - TimeUtil.DAY - TimeUtil.HOUR * 16));
		Thoth t4 = new TextThoth();
		t4.setDate(new Date(now - TimeUtil.DAY));
		tv.getEvents().add(t1, t2, t3, t4);
	}
}