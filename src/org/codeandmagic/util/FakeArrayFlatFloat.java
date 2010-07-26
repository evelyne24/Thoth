package org.codeandmagic.util;

public class FakeArrayFlatFloat implements FakeArrayFloat {

	private final int size;
	private final float value;

	public FakeArrayFlatFloat(final float value, final int size) {
		this.value = value;
		this.size = size;
	}

	public float get(final int i) throws ArrayIndexOutOfBoundsException {
		if (i < 0 || i >= size)
			throw new ArrayIndexOutOfBoundsException();
		return value;
	}

	public int size() {
		return size;
	}

	public int getSize() {
		return size;
	}

	public float getValue() {
		return value;
	}

}
