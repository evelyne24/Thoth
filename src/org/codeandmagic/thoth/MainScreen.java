package org.codeandmagic.thoth;

import java.util.Date;

import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.Thoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.thoth.timeline.ThothIconRenderer;
import org.codeandmagic.timeline.LiniarHorizontalLayout;
import org.codeandmagic.timeline.MiddleVerticalLayout;
import org.codeandmagic.timeline.TimelineView;

import android.app.Activity;
import android.os.Bundle;

public class MainScreen extends Activity {
	
	TimelineView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TimelineView(this, new LiniarHorizontalLayout(500000), new MiddleVerticalLayout(0, 300), new ThothIconRenderer());
        setTestData();
        setContentView(tv);
    }
    
	private void setTestData() {
		long now = (new Date()).getTime();
		Thoth t1 = new PictureThoth();
		t1.setDate(new Date(now-TimelineView.DAY*2*1000));
		Thoth t2 = new VideoThoth();
		t2.setDate(new Date(now-TimelineView.DAY*3*1000+TimelineView.HOUR*5*1000));
		Thoth t3 = new TextThoth();
		t3.setDate(new Date(now-TimelineView.DAY*1000-TimelineView.HOUR*16*1000));
		Thoth t4 = new TextThoth();
		t4.setDate(new Date(now-TimelineView.DAY*1000));
		tv.getEvents().add(t1,t2,t3,t4);
	}
}