package org.codeandmagic.util;

public interface FakeArrayFloat {
	public int size();

	public float get(int i) throws ArrayIndexOutOfBoundsException;

}
