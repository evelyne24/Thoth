/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All rights reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify
	it under the terms of the GNU Lesser General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	Thoth is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void testFuzzyIntervalSearch() {
		final float[] base = new float[] { 1, 4, 4, 5, 7.3f, 8, 10, 23.4f, 23.4f, 34 };
		final float[] nullBase = null;
		final float[] emptyBase = new float[0];

		// empty arrays
		assertNull(ArrayUtils.fuzzyIntervalSearch(nullBase, 4, 23));
		assertNull(ArrayUtils.fuzzyIntervalSearch(emptyBase, 4, 23));

		// non-sense
		assertNull(ArrayUtils.fuzzyIntervalSearch(base, 10, 4));
		assertNull(ArrayUtils.fuzzyIntervalSearch(base, -4, 0.5f));
		assertNull(ArrayUtils.fuzzyIntervalSearch(base, 35, 90));

		// 1 element match
		assertArrayEquals(new int[] { 4, 4 }, ArrayUtils.fuzzyIntervalSearch(base, 7, 7.9f));
		assertArrayEquals(new int[] { 1, 2 }, ArrayUtils.fuzzyIntervalSearch(base, 2.3f, 4.5f));

		// normal match
		assertArrayEquals(new int[] { 1, 8 }, ArrayUtils.fuzzyIntervalSearch(base, 4, 23.4f));
		assertArrayEquals(new int[] { 0, 2 }, ArrayUtils.fuzzyIntervalSearch(base, 1, 4));

		// fuzzy match
		assertArrayEquals(new int[] { 1, 8 }, ArrayUtils.fuzzyIntervalSearch(base, 3.2f, 24.5f));
		// TOOD: note that because of float precision using 4.999999999999999f instead of 4.99f will cause 5 to be candidate
		assertArrayEquals(new int[] { 0, 2 }, ArrayUtils.fuzzyIntervalSearch(base, 0.1f, 4.99f));

		// match larger than base
		assertArrayEquals(new int[] { 0, base.length - 1 }, ArrayUtils.fuzzyIntervalSearch(base, -1, 90));

		// match with one end outside the base
		assertArrayEquals(new int[] { 0, 4 }, ArrayUtils.fuzzyIntervalSearch(base, -34.3f, 7.99999f));
		assertArrayEquals(new int[] { 7, base.length - 1 }, ArrayUtils.fuzzyIntervalSearch(base, 23.1f, 99.9999f));
	}

	@Test
	public void testFuzzySearch() {
		final float[] base = new float[] { 1, 4, 4, 5, 7.3f, 8, 10, 23.4f, 23.4f, 34 };
		final float[] nullBase = null;
		final float[] emptyBase = new float[0];

		// empty arrays
		assertEquals(-1, ArrayUtils.fuzzySearch(nullBase, 5, true));
		assertEquals(-1, ArrayUtils.fuzzySearch(emptyBase, 5, false));

		// value outside the array
		assertEquals(0, ArrayUtils.fuzzySearch(base, 0.1f, true));
		assertEquals(-1, ArrayUtils.fuzzySearch(base, 0.1f, false));
		assertEquals(-1, ArrayUtils.fuzzySearch(base, 101, true));
		assertEquals(base.length - 1, ArrayUtils.fuzzySearch(base, 101, false));

		// normal case
		assertEquals(3, ArrayUtils.fuzzySearch(base, 5, true));

		// toTheRight in case of exact match
		assertEquals(1, ArrayUtils.fuzzySearch(base, 4, true));
		assertEquals(2, ArrayUtils.fuzzySearch(base, 4, false));

		// fuzzy match
		assertEquals(4, ArrayUtils.fuzzySearch(base, 7, true));
		assertEquals(3, ArrayUtils.fuzzySearch(base, 7, false));
	}
}
