package org.codeandmagic.util;

public class ArrayUtils {
	/**
	 * Searched the given array for first element >= start and last element <= end
	 * 
	 * @param base Array to search. Must be already ordered.
	 * @param startValue Start index. F
	 * @param endValue
	 * @return An int array with 2 elements where the 0 element is the index of the head of the subinterval and the 1 index is the
	 *         tail of the subinterval. If no subinterval is found returns null.
	 */
	public static int[] fuzzyIntervalSearch(final float[] base, final float startValue, final float endValue) {

		// no point in searching an empty array
		if (base == null || base.length == 0)
			return null;
		// quick check if the searched interval makes sense or is outside the existing array
		if (startValue > endValue || endValue < base[0] || startValue > base[base.length - 1])
			return null;
		return searchStartAndEnd(base, startValue, endValue, 0, base.length - 1);
	}

	private static int[] searchStartAndEnd(final float[] base, final float startValue, final float endValue, final int leftLimit,
			final int rightLimit) {

		if (leftLimit == rightLimit)
			return new int[] { leftLimit, rightLimit };
		final int middle = (leftLimit + rightLimit) / 2;
		final float middleValue = base[middle];

		// interval is to the left of the middle
		if (endValue < middleValue)
			return searchStartAndEnd(base, startValue, endValue, leftLimit, middle - 1);

		// interval is to the right of the middle
		if (startValue > middleValue)
			return searchStartAndEnd(base, startValue, endValue, middle + 1, rightLimit);

		// start is to the left and end is to the right of the middle
		return new int[] { fuzzySearch(base, startValue, leftLimit, middle, true),
				fuzzySearch(base, endValue, middle, rightLimit, false) };
	}

	/**
	 * Searches the provided array for the same or closest inferior or superior value
	 * 
	 * @param base Array to search. Must be already oredered.
	 * @param value Value to search
	 * @param orGreater If true searches the closest superior value, else searches the closest inferior value
	 * @return The index of the value in the array or the closest element. Note that if an exact match is found and there are
	 *         duplicates of this value the index returned will depend on the value of the orGreater parameter. If this is true,
	 *         the first index is returned, otherwise the last index is returned. If no match is found return -1. In other words
	 *         this function returns the intersection the the base array with the [value,Infinity) or (-Infinity,value] intervals.
	 */
	public static int fuzzySearch(final float[] base, final float value, final boolean orGreater) {

		// no point in searching an empty array
		if (base == null || base.length == 0)
			return -1;

		// quick check if the searched value is inside
		if (value < base[0]) {
			if (orGreater)
				return 0;
			else
				return -1;
		}

		final int last = base.length - 1;
		if (value > base[last]) {
			if (orGreater)
				return -1;
			else
				return last;
		}

		return fuzzySearch(base, value, 0, base.length - 1, orGreater);
	}

	private static int fuzzySearch(final float[] base, final float value, final int leftLimit, final int rightLimit,
			final boolean orGreater) {

		// as we always narrow the interval in which the correct answer lies, if we only have one element left then that's the
		// correct answer
		if (leftLimit >= rightLimit)
			return leftLimit;

		// we need a supplementary check for 2 elements because sometimes we can enter an infinite "loop"
		if (rightLimit - leftLimit == 1) {
			if (orGreater) {
				final float leftValue = base[leftLimit];
				if (leftValue < value)
					return rightLimit;
				else
					return leftLimit;
			}
			else {
				final float rightValue = base[rightLimit];
				if (rightValue > value)
					return leftLimit;
				else
					return rightLimit;
			}
		}

		final int middle = (leftLimit + rightLimit) / 2;
		final float middleValue = base[middle];

		if (value < middleValue) // search in the left half
			return fuzzySearch(base, value, leftLimit, orGreater ? middle : middle - 1, orGreater);
		else if (value > middleValue) // search in the right half
			return fuzzySearch(base, value, orGreater ? middle + 1 : middle, rightLimit, orGreater);
		else {
			if (orGreater) {
				int i = middle - 1;
				while (i > 0 && base[i] == value) {
					--i;
				}
				return i + 1;
			}
			else {
				int i = middle + 1;
				while (i < base.length && base[i] == value) {
					++i;
				}
				return i - 1;
			}
		}
	}
}
