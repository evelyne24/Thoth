package org.codeandmagic.timeline;

import java.io.Serializable;
import java.util.Date;

import org.codeandmagic.thoth.data.Thoth;

public class Event implements Comparable<Thoth>, Serializable{
	private static final long serialVersionUID = 6214470588903393335L;
	
	private long id;
	private Date date;
	private String name;
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	public int compareTo(Thoth another) {
		//Events are naturally ordered by date
		return this.date.compareTo(another.getDate());
	}
}
