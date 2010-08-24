package org.codeandmagic.thoth;

import org.codeandmagic.timeline.TimelineActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainScreen extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(MainScreen.this, TimelineActivity.class));
	}

}