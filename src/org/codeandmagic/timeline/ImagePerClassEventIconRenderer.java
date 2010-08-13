package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Map;

/**
 * {@link DynamicEventIconRenderer} that
 * 
 * @author cristi
 * 
 */
public class ImagePerClassEventIconRenderer extends DynamicEventIconRenderer {
	private Map<Class<?>, Integer> associations;

	public Map<Class<?>, Integer> getAssociations() {
		return associations;
	}

	public void setAssociations(final Map<Class<?>, Integer> associations) {
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
			for (final Map.Entry<Class<?>, Integer> entry : associations.entrySet()) {
				if (entry.getKey().isAssignableFrom(event.getClass()))
					return entry.getValue();
			}
		}
		return 0;
	}
}
