package org.codeandmagic.thoth;

import org.codeandmagic.thoth.miscwidgets.Panel;
import org.codeandmagic.thoth.miscwidgets.Panel.OnPanelListener;
import org.codeandmagic.thoth.miscwidgets.interpolator.ExpoInterpolator;
import org.codeandmagic.thoth.miscwidgets.interpolator.EasingType.Type;

import android.app.Activity;
import android.os.Bundle;

public class ThothScreen extends Activity implements OnPanelListener {
	private Panel panel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thoth_view);

		panel = (Panel) findViewById(R.id.leftPanel);
		panel.setOnPanelListener(this);
		panel.setInterpolator(new ExpoInterpolator(Type.OUT));
	}

	public void onPanelClosed(Panel panel) {
		// TODO Auto-generated method stub

	}

	public void onPanelOpened(Panel panel) {
		// TODO Auto-generated method stub

	}

}
