package org.codeandmagic.timeline;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Events {
	private final SortedSet<Event> events = new TreeSet<Event>();

	public SortedSet<Event> getEvents() {
		return events;
	}
	
	public void add(Event... event){
		for(int i=0; i<event.length; i++){
			this.events.add(event[i]);
		}
	}
	
	public void add(Collection<Event> events){
		this.events.addAll(events);
	}
	
	public int size(){
		return this.events.size();
	}
	
	/**
	 * Returns the first {@link Event} that was recoded on the exact {@link Date} or 
	 * after the passed parameter.
	 * @param date
	 * @return
	 */
	public Event getFirstAfter(Date date){
		//no point in even trying if we have no events
		if(this.events.size()==0){
			return null;
		}
		//quick check to see if we have events > date
		Event last = this.events.last();
		if(date.compareTo(last.getDate()) > 0){
			return null;
		}
		//iterate till we find what we need 
		//TODO: we probably need a better algorithm
		Iterator<Event> i = this.events.iterator();
		while(i.hasNext()){
			Event e = i.next();
			if(e.getDate().compareTo(date) >= 0){
				return e;
			}
		}
		//we should never get here because of the quick test
		return null;
	}
	
	public Event getLastBefore(Date date){
		//no point in even trying if we have no events
		if(this.events.size()==0){
			return null;
		}
		//quick check to see if we have events < date
		Event first = this.events.first();
		if(date.compareTo(first.getDate()) < 0){
			return null;
		}
		//iterate till we find what we need 
		//TODO: we probably need a better algorithm
		Iterator<Event> i = this.events.iterator();
		Event candidate = null;
		while(i.hasNext()){
			Event t  = i.next();
			if(t.getDate().compareTo(date) > 0){
				return candidate;
			}else{
				candidate = t;
			}
		}
		//we should never get here because of the quick test
		return null;
	}
	
	public SortedSet<Event> getInterval(Date from, Date to){
		Event fromT = this.getFirstAfter(from);
		Event toT = this.getLastBefore(to);
		if(fromT == null){
			if(toT == null){
				return null;
			}else{
				return this.events.headSet(toT);
			}
		}else{
			if(toT == null){
				return this.events.tailSet(fromT);
			}else{
				return this.events.subSet(fromT, toT);
			}
		}
	}
}
