package org.codeandmagic.thoth.data;

import org.codeandmagic.timeline.Event;

/**
 * Represents a "post"
 * @author cristi
 *
 */
public abstract class Thoth extends Event{
	
	private static final long serialVersionUID = 6233638514265433655L;
	private String user;
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
