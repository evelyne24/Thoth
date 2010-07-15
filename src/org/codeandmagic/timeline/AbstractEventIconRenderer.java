package org.codeandmagic.timeline;

import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public abstract class AbstractEventIconRenderer implements EventIconRenderer {
	/**
	 * Bitmaps necessary for drawing icons
	 */
	private Bitmap[] icons;
	private final int iconWidth;
	private final int iconHeight;
	
	public AbstractEventIconRenderer(int iconWidth, int iconHeight){
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	private void loadIcon(int key, Drawable drawable) {
		icons[key] = loadBitmap(drawable, iconWidth, iconHeight);
	}

	private Bitmap loadBitmap(Drawable drawable, int width, int height) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
	}

	public Bitmap getIconForEvent(Event event) {
		return icons[getKeyForEvent(event)];
	}

	public void preloadIcons(Context context) {
		Resources res = context.getResources();
		Map<Integer,Integer> keys = getAllDrawables();
		icons = new Bitmap[keys.size()];
		for(Map.Entry<Integer,Integer> entry : keys.entrySet()){
			loadIcon(entry.getKey(),res.getDrawable(entry.getValue()));
		}
	}

	public abstract int getKeyForEvent(Event event);
	public abstract Map<Integer,Integer> getAllDrawables();

}