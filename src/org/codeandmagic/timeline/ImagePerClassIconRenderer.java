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
package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Map;

/**
 * {@link DynamicEventIconRenderer} that
 * 
 * @author cristi
 * 
 */
public class ImagePerClassIconRenderer extends DynamicEventIconRenderer {
	private Map<Class<? extends Event>, Integer> associations;

	public Map<Class<? extends Event>, Integer> getAssociations() {
		return associations;
	}

	public void setAssociations(final Map<Class<? extends Event>, Integer> associations) {
		this.associations = associations;
	}

	@Override
	public Collection<Integer> getAllDrawables() {
		if (associations == null)
			return null;
		else
			return associations.values();
	}

	@Override
	public int getDrawableForEvent(final Event event) {
		if (associations != null) {
			for (final Map.Entry<Class<? extends Event>, Integer> entry : associations.entrySet()) {
				if (entry.getKey().isAssignableFrom(event.getClass()))
					return entry.getValue();
			}
		}
		return 0;
	}
}
