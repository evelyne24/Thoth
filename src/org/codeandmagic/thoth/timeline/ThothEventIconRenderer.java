package org.codeandmagic.thoth.timeline;

import java.util.HashMap;
import java.util.Map;

import org.codeandmagic.thoth.R;
import org.codeandmagic.thoth.data.PictureThoth;
import org.codeandmagic.thoth.data.VideoThoth;
import org.codeandmagic.timeline.AbstractEventIconRenderer;
import org.codeandmagic.timeline.Event;

public class ThothEventIconRenderer extends AbstractEventIconRenderer{
	
	private final static int THOTH_ICON_WIDTH = 33;
	private final static int THOTH_ICON_HEIGHT = 31;

	public final static int BMP_TEXT_THOTH = 0;
	public final static int BMP_PHOTO_THOTH = 1;
	public final static int BMP_VIDEO_THOTH = 2;
	
	public ThothEventIconRenderer() {
		super(THOTH_ICON_WIDTH, THOTH_ICON_HEIGHT);
	}
	
	Map<Integer, Integer> drawables;
	@Override
	public Map<Integer, Integer> getAllDrawables() {
		if(drawables == null){
			drawables = new HashMap<Integer, Integer>();
			drawables.put(BMP_TEXT_THOTH, R.drawable.text);
			drawables.put(BMP_PHOTO_THOTH, R.drawable.photo);
			drawables.put(BMP_VIDEO_THOTH, R.drawable.video);
		}
		return drawables;
	}

	@Override
	public int getKeyForEvent(Event event) {
		if(event instanceof PictureThoth){
			return BMP_PHOTO_THOTH;
		}else if(event instanceof VideoThoth){
			return BMP_VIDEO_THOTH;
		}else{
			return BMP_TEXT_THOTH;
		}
	}
	
}
