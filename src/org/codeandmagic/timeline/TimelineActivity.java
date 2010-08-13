package org.codeandmagic.timeline;

import java.util.Date;
import java.util.Random;

import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.Thoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.thoth.timeline.ThothEventRenderer;
import org.codeandmagic.util.TimeUtils;

import android.app.Activity;
import android.os.Bundle;

public class TimelineActivity extends Activity {

	private TimelineView tv;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final LinearEventHorizontalLocator ehl = new LinearEventHorizontalLocator();
		final MiddleVerticalLocator evl = new MiddleVerticalLocator();
		final StaticEventIconRenderer eir = new StaticEventIconRenderer();
		final LinearAxisHorizontalLocator ahl = new LinearAxisHorizontalLocator(ehl);
		final DefaultAxisRenderer ar = new DefaultAxisRenderer();
		final ColorBackgroundRenderer br = new ColorBackgroundRenderer();
		final ThothEventRenderer er = new ThothEventRenderer();

		tv = new TimelineView(this, ehl, evl, eir, ahl, ar, br, er);
		setTestData();

		setContentView(tv);
	}

	private void setTestData() {
		setStaticData();
	}

	private void setStaticData() {
		final long now = (new Date()).getTime();

		final Thoth t1 = new PictureThoth();
		t1.setName("T1 Picture Thoth");
		t1.setDate(new Date(now - TimeUtils.DAY * 2));

		final Thoth t2 = new VideoThoth();
		t2.setName("T2 Video Thoth");
		t2.setDate(new Date(now - TimeUtils.DAY * 3 + TimeUtils.HOUR * 5));

		final Thoth t3 = new TextThoth();
		t3.setName("T3 Text Thoth");
		t3.setDate(new Date(now - TimeUtils.DAY - TimeUtils.HOUR * 16));

		final Thoth t4 = new TextThoth();
		t4.setName("T4 Text Thoth");
		t4.setDate(new Date(now - TimeUtils.DAY));

		final Thoth t5 = new TextThoth();
		t5.setName("T5 Text Thoth");
		t5.setDate(new Date(now + TimeUtils.DAY));

		tv.getEvents().add(t1, t2, t3, t4, t5);
	}

	private void setLotsOfDate() {
		final int nrEvents = 200;
		final long now = (new Date()).getTime();
		final long min = now - TimeUtils.DAY * 10;
		final long max = now + TimeUtils.DAY * 10;

		final Thoth[] thoths = new Thoth[nrEvents];
		final long dif = max - min + 1;
		final Random r = new Random(new Date().getTime());
		for (int i = 0; i < nrEvents; ++i) {
			final long t = r.nextInt((int) dif) + min;
			final Thoth tt = randomThothType();
			tt.setName("Thoth " + tt.hashCode());
			tt.setDate(new Date(t));
			thoths[i] = tt;
		}

		tv.getEvents().add(thoths);
	}

	final Random rt = new Random(new Date().getTime());

	private Thoth randomThothType() {
		final int n = rt.nextInt() % 3;
		switch (n) {
		case 0:
			return new VideoThoth();
		case 1:
			return new PictureThoth();
		default:
			return new TextThoth();
		}
	}
}
