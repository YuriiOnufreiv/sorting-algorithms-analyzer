package ua.onufreiv.nc.first.fillers;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by yurii on 12/2/16.
 */
public class ArrayFillersTest {

    private static int arrLength;

    @BeforeClass
    public static void setUp() throws Exception {
        arrLength = 100;
    }

    @Test
    public void testGetSortedNotNull() {
        int[] arr = ArrayFillers.getSorted(arrLength);
        assertNotNull(arr);
    }

    @Test
    public void testGetSortedWithRandomAtEndNotNull() {
        int[] arr = ArrayFillers.getSortedWithRandomAtEnd(arrLength);
        assertNotNull(arr);
    }

    @Test
    public void testGetReverseSortedNotNull() {
        int[] arr = ArrayFillers.getReverseSorted(arrLength);
        assertNotNull(arr);
    }

    @Test
    public void testGetRandomNotNull() {
        int[] arr = ArrayFillers.getTotallyRandom(arrLength);
        assertNotNull(arr);
    }

    @Test
    public void testGetSortedSameLength() {
        int[] arr = ArrayFillers.getSorted(arrLength);
        assertTrue(arr.length == arrLength);
    }

    @Test
    public void testGetSortedWithRandomAtEndSameLength() {
        int[] arr = ArrayFillers.getSortedWithRandomAtEnd(arrLength);
        assertTrue(arr.length == arrLength);
    }

    @Test
    public void testGetReverseSortedSameLength() {
        int[] arr = ArrayFillers.getReverseSorted(arrLength);
        assertTrue(arr.length == arrLength);
    }

    @Test
    public void testGetRandomSameLength() {
        int[] arr = ArrayFillers.getTotallyRandom(arrLength);
        assertTrue(arr.length == arrLength);
    }

    @Test
    public void getSorted() {
        int[] arr = ArrayFillers.getSorted(arrLength);
        for(int i = 0; i < arrLength - 1; i++) {
            assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    @Test
    public void getSortedWithRandomAtEnd() {
        int[] arr = ArrayFillers.getSortedWithRandomAtEnd(arrLength);
        for(int i = 0; i < arrLength - 2; i++) {
            assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    @Test
    public void getReverseSorted() {
        int[] arr = ArrayFillers.getReverseSorted(arrLength);
        for(int i = 0; i < arrLength - 1; i++) {
            assertTrue(arr[i] >= arr[i + 1]);
        }
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testSortedException() {
        ArrayFillers.getSorted(-1);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testSortedWithRandomAtEndException() {
        ArrayFillers.getSortedWithRandomAtEnd(-1);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testReverseSortedException() {
        ArrayFillers.getReverseSorted(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomSortedException() {
        ArrayFillers.getTotallyRandom(-1);
    }
}