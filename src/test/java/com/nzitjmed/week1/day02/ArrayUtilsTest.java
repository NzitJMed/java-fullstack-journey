package com.nzitjmed.week1.day02;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayUtilsTest {

    @Test
    @DisplayName("Reverse returns new array in reverse order without modifying original")
    void testReverse() {
        int[] original = {1, 2, 3, 4, 5};
        int[] reversed = ArrayUtils.reverse(original);

        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reversed);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, original); // original unchanged
    }

    @Test
    @DisplayName("Find max using recursion with positive numbers")
    void testFindMaxPositive() {
        assertEquals(42, ArrayUtils.findMax(new int[]{3, 42, 7, 1}));
    }

    @Test
    @DisplayName("Find max with negative numbers")
    void testFindMaxNegative() {
        assertEquals(-1, ArrayUtils.findMax(new int[]{-5, -3, -1, -10}));
    }

    @Test
    @DisplayName("isSorted detects ascending order")
    void testIsSortedAscending() {
        assertTrue(ArrayUtils.isSorted(new int[]{1, 2, 3, 4}));
    }

    @Test
    @DisplayName("isSorted returns true for single element")
    void testIsSortedSingleElement() {
        assertTrue(ArrayUtils.isSorted(new int[]{5}));
    }

    @Test
    @DisplayName("isSorted returns true for empty array")
    void testIsSortedEmpty() {
        assertTrue(ArrayUtils.isSorted(new int[]{}));
    }
    @Test
    @DisplayName("isSorted returns false for unsorted array")
    void testIsSortedUnsorted() {
        assertFalse(ArrayUtils.isSorted(new int[]{3, 1, 2}));
    }

    @Test
    @DisplayName("mergeSorted combines two sorted arrays")
    void testMergeSorted() {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, ArrayUtils.mergeSorted(a, b));
    }

    @Test
    @DisplayName("mergeSorted handles arrays with duplicates")
    void testMergeSortedWithDuplicates() {
        int[] a = {1, 2, 2};
        int[] b = {2, 3, 4};
        assertArrayEquals(new int[]{1, 2, 2, 2, 3, 4}, ArrayUtils.mergeSorted(a, b));
    }

    @Test
    @DisplayName("Null array should throw IllegalArgumentException for reverse")
    void testReverseNullThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.reverse(null));
        assertTrue(ex.getMessage().toLowerCase().contains("null"));
    }

    @Test
    @DisplayName("Null array should throw IllegalArgumentException for findMax")
    void testFindMaxNullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.findMax(null));
    }

    @Test
    @DisplayName("Empty array should throw IllegalArgumentException for findMax")
    void testFindMaxEmptyThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.findMax(new int[]{}));
    }
}
