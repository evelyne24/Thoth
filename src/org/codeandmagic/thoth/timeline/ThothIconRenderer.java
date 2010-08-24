/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All rights reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify
	it under the terms of the GNU Lesser General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	Thoth is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.thoth.timeline;

import java.util.HashMap;
import java.util.Map;

import org.codeandmagic.thoth.R;
import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.timeline.Event;
import org.codeandmagic.timeline.ImagePerClassIconRenderer;

public class ThothIconRenderer extends ImagePerClassIconRenderer {

	private final static int THOTH_ICON_WIDTH = 33;
	private final static int THOTH_ICON_HEIGHT = 31;

	public ThothIconRenderer() {
		super();

		setIconWidth(THOTH_ICON_WIDTH);
		setIconHeight(THOTH_ICON_HEIGHT);

		final Map<Class<? extends Event>, Integer> assoc = new HashMap<Class<? extends Event>, Integer>();
		assoc.put(PictureThoth.class, R.drawable.photo);
		assoc.put(VideoThoth.class, R.drawable.video);
		assoc.put(TextThoth.class, R.drawable.text);
		setAssociations(assoc);

		setLabelLeft(THOTH_ICON_WIDTH + 5);
		setLabelTop(THOTH_ICON_HEIGHT / 2 + 5);
	}
}
