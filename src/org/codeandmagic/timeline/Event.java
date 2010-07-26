package org.codeandmagic.timeline;

import java.io.Serializable;
import java.util.Date;

public class Event implements Comparable<Event>, Serializable {
	private static final long serialVersionUID = 6214470588903393335L;

	private long id;
	private Date date;
	private String name;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int compareTo(final Event another) {
		// Events are naturally ordered by date
		return date.compareTo(another.getDate());
	}
}
