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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Abstract subclass of {@link StaticEventIconRenderer} which provides a few simplifications for subclasses that have different
 * images for different types of events.
 * 
 * @author cristi
 * 
 */
public abstract class DynamicEventIconRenderer extends StaticEventIconRenderer {
	/**
	 * Bitmaps necessary for drawing icons
	 */
	private Map<Integer, Bitmap> icons;

	@Override
	public void preloadIcons(final Context context) {
		// loads the default icon
		super.preloadIcons(context);
		// loads all other icons
		final Resources res = context.getResources();
		final Collection<Integer> drawables = getAllDrawables();
		icons = new HashMap<Integer, Bitmap>();
		if (drawables != null) {
			for (final Integer drawable : drawables) {
				icons.put(drawable, loadBitmap(res.getDrawable(drawable), getIconWidth(), getIconHeight()));
			}
		}
	}

	@Override
	public Bitmap getIconForEvent(final Event event) {
		final Integer drawable = getDrawableForEvent(event);
		if (drawable == 0)
			return super.getIconForEvent(event);

		final Bitmap b = icons.get(drawable);
		if (b == null)
			return super.getIconForEvent(event);

		return b;
	}

	/**
	 * Gets an array containing all the ids of the {@link Drawable}s used by this {@link EventIconRenderer}. This does not have to
	 * contain the default icon.
	 * 
	 * @return
	 */
	public abstract Collection<Integer> getAllDrawables();

	/**
	 * Gets the {@link Drawable} id to use for the specified {@link Event}. A value of 0 means the default drawable (
	 * {@link #getDefaultIconDrawable()}) will be used.
	 * 
	 * @param e
	 * @return
	 */
	public abstract int getDrawableForEvent(Event e);

}
