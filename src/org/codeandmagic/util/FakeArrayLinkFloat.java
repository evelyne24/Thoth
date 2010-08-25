/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All Rights Reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify it under
	the terms of the GNU Lesser General Public License as published by the
	Free Software Foundation, either version 3 of the License, or (at your
	option) any later version.

	Thoth is distributed in the hope that it will be useful, but WITHOUT 
	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public 
	License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
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
