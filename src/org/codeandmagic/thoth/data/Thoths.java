package org.codeandmagic.thoth.data;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Thoths {
	private final SortedSet<Thoth> thoths = new TreeSet<Thoth>();

	public SortedSet<Thoth> getThoths() {
		return thoths;
	}
	
	public void add(Thoth... thoth){
		for(int i=0; i<thoth.length; i++){
			this.thoths.add(thoth[i]);
		}
	}
	
	public void add(Collection<Thoth> thoths){
		this.thoths.addAll(thoths);
	}
	
	public int size(){
		return this.thoths.size();
	}
	
	/**
	 * Returns the first {@link Thoth} that was recoded on the exact {@link Date} or 
	 * after the passed parameter.
	 * @param date
	 * @return
	 */
	public Thoth getFirstAfter(Date date){
		//no point in even trying if we have no thoths
		if(this.thoths.size()==0){
			return null;
		}
		//quick check to see if we have thoths > date
		Thoth last = this.thoths.last();
		if(date.compareTo(last.getDate()) > 0){
			return null;
		}
		//iterate till we find what we need 
		//TODO: we probably need a better algorithm
		Iterator<Thoth> i = this.thoths.iterator();
		while(i.hasNext()){
			Thoth t = i.next();
			if(t.getDate().compareTo(date) >= 0){
				return t;
			}
		}
		//we should never get here because of the quick test
		return null;
	}
	
	public Thoth getLastBefore(Date date){
		//no point in even trying if we have no thoths
		if(this.thoths.size()==0){
			return null;
		}
		//quick check to see if we have thoths < date
		Thoth first = this.thoths.first();
		if(date.compareTo(first.getDate()) < 0){
			return null;
		}
		//iterate till we find what we need 
		//TODO: we probably need a better algorithm
		Iterator<Thoth> i = this.thoths.iterator();
		Thoth candidate = null;
		while(i.hasNext()){
			Thoth t  = i.next();
			if(t.getDate().compareTo(date) > 0){
				return candidate;
			}else{
				candidate = t;
			}
		}
		//we should never get here because of the quick test
		return null;
	}
	
	public SortedSet<Thoth> getInterval(Date from, Date to){
		Thoth fromT = this.getFirstAfter(from);
		Thoth toT = this.getLastBefore(to);
		if(fromT == null){
			if(toT == null){
				return null;
			}else{
				return this.thoths.headSet(toT);
			}
		}else{
			if(toT == null){
				return this.thoths.tailSet(fromT);
			}else{
				return this.thoths.subSet(fromT, toT);
			}
		}
	}
}
