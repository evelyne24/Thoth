package org.codeandmagic.thoth.data;

import java.util.Date;

/**
 * Represents a "post"
 * @author cristi
 *
 */
public abstract class Thoth implements Comparable<Thoth>{
	private long id;
	private Date date;
	private String user;
	
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public int compareTo(Thoth another) {
		//Thoths are naturally ordered by date
		return this.date.compareTo(another.getDate());
	}
}
