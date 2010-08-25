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
