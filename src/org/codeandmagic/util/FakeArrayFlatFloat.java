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
