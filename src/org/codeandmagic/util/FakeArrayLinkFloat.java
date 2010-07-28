package org.codeandmagic.util;

public class FakeArrayLinkFloat implements FakeArrayFloat {

	private final float[] innerArray;
	private final int leftLimit;
	private final int rightLimit;

	public FakeArrayLinkFloat(final float[] array, final int left, final int right) {
		innerArray = array;
		leftLimit = left;
		rightLimit = right;
	}

	public float get(final int i) throws ArrayIndexOutOfBoundsException {
		final int indx = i + leftLimit;
		if (indx < leftLimit || indx > rightLimit)
			throw new ArrayIndexOutOfBoundsException();
		return innerArray[indx];
	}

	public int size() {
		return rightLimit - leftLimit + 1;
	}

	public float[] getInnerArray() {
		return innerArray;
	}

	public int getLeftLimit() {
		return leftLimit;
	}

	public int getRightLimit() {
		return rightLimit;
	}

}
