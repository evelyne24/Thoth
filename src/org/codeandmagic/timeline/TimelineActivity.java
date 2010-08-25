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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.codeandmagic.thoth.R;
import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.Thoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.thoth.timeline.ThothEventRenderer;
import org.codeandmagic.timeline.StaticEventIconRenderer.TimeAndNameEventLabelFormat;
import org.codeandmagic.util.TimeUtils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;

public class TimelineActivity extends Activity {

	private TimelineView tv;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final LinearHorizontalLocator ehl = new LinearHorizontalLocator();
		final DodgeVerticalLocator evl = new DodgeVerticalLocator(ehl);
		evl.setVerticalStep(50);
		final LinearAxisLocator ahl = new LinearAxisLocator(ehl);
		final DefaultAxisRenderer ar = new DefaultAxisRenderer();
		final ColorBackgroundRenderer br = new ColorBackgroundRenderer();
		final ThothEventRenderer er = new ThothEventRenderer();

		final ImagePerClassIconRenderer eir = new ImagePerClassIconRenderer();
		eir.setIconHeight(25);
		eir.setIconWidth(25);
		final Map<Class<? extends Event>, Integer> assoc = new HashMap<Class<? extends Event>, Integer>();
		assoc.put(PictureThoth.class, R.drawable.photo);
		assoc.put(VideoThoth.class, R.drawable.video);
		assoc.put(TextThoth.class, R.drawable.text);
		eir.setAssociations(assoc);
		final TimeAndNameEventLabelFormat labelFormat = new TimeAndNameEventLabelFormat();
		labelFormat.setMaxChars(-1);
		labelFormat.setDateFormat("dd/MM/yyy hh:mm a - ");
		labelFormat.setDateInFront(true);
		eir.setLabelFormat(labelFormat);
		eir.setLabelTop(17);
		eir.setLabelLeft(31);
		final Paint p = new Paint();
		p.setColor(Color.WHITE);
		p.setTextSize(12);
		p.setAntiAlias(true);
		p.setTypeface(Typeface.DEFAULT_BOLD);
		eir.setLabelPaint(p);

		tv = new TimelineView(this, ehl, evl, eir, ahl, ar, br, er);
		setTestData();

		setContentView(tv);
	}

	private void setTestData() {
		// setLotsOfData();
		setStaticData();
	}

	void setStaticData() {
		final long now = (new Date()).getTime();

		final Thoth t1 = new PictureThoth();
		t1.setName("T1 Picture Thoth");
		t1.setDate(new Date(now - TimeUtils.DAY * 2));

		final Thoth t15 = new VideoThoth();
		t15.setName("T1.5 Video Thoth");
		t15.setDate(new Date(now - TimeUtils.DAY * 3 - TimeUtils.HOUR * 5));

		final Thoth t2 = new VideoThoth();
		t2.setName("T2 Video Thoth");
		t2.setDate(new Date(now - TimeUtils.DAY * 3 + TimeUtils.HOUR * 8));

		final Thoth t3 = new TextThoth();
		t3.setName("T3 Text Thoth");
		t3.setDate(new Date(now - TimeUtils.DAY - TimeUtils.HOUR * 10));

		final Thoth t4 = new TextThoth();
		t4.setName("T4 Text Thoth");
		t4.setDate(new Date(now - TimeUtils.DAY - TimeUtils.HOUR * 2));

		final Thoth t5 = new TextThoth();
		t5.setName("T5 Text Thoth");
		t5.setDate(new Date(now + TimeUtils.DAY));

		tv.getEvents().add(t1, t15, t2, t3, t4, t5);
	}

	void setLotsOfData() {
		final int nrEvents = 100;
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
