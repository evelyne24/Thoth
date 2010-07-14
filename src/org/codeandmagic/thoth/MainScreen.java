package org.codeandmagic.thoth;

import org.codeandmagic.thoth.timeline.TimelineView;

import android.app.Activity;
import android.os.Bundle;

public class MainScreen extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TimelineView tv = new TimelineView(this);
        setContentView(tv);
    }
}