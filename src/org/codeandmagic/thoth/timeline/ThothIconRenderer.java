package org.codeandmagic.thoth.timeline;

import java.util.HashMap;
import java.util.Map;

import org.codeandmagic.thoth.R;
import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.TextThoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.timeline.ImagePerClassEventIconRenderer;

public class ThothIconRenderer extends ImagePerClassEventIconRenderer {

	private final static int THOTH_ICON_WIDTH = 33;
	private final static int THOTH_ICON_HEIGHT = 31;

	public ThothIconRenderer() {
		super();

		setIconWidth(THOTH_ICON_WIDTH);
		setIconHeight(THOTH_ICON_HEIGHT);

		final Map<Class<?>, Integer> assoc = new HashMap<Class<?>, Integer>();
		assoc.put(PictureThoth.class, R.drawable.photo);
		assoc.put(VideoThoth.class, R.drawable.video);
		assoc.put(TextThoth.class, R.drawable.text);
		setAssociations(assoc);

		setLabelLeft(THOTH_ICON_WIDTH + 5);
		setLabelTop(THOTH_ICON_HEIGHT / 2 + 5);
	}
}
