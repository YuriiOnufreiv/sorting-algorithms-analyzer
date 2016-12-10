package ua.onufreiv.nc.first.sorters;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.onufreiv.nc.first.fillers.ArrayFillers;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by yurii on 12/5/16.
 */
@Ignore
public abstract class AbstractSortTest {
    private static int[] arr;
    private static int arrLength;
    protected AbstractSort sorting;

    @BeforeClass
    public static void setUpBeforeClass() {
        arrLength = 100;
    }

    @Before
    public void setUp() throws Exception {
        arr = ArrayFillers.getTotallyRandom(arrLength);
        sorting = getCurrentSorting();
    }

    @Test(timeout = 10)
    public void testSort() throws Exception {
        sorting.sort(arr);
        for (int i = 0; i < arrLength - 1; i++) {
            assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    @Test
    public void testSameLengthAfterSort() {
        sorting.sort(arr);
        assertTrue(arr.length == arrLength);
    }

    @Test
    public void testSameElementsAfterSort() {
        int[] arrBeforeSort = arr.clone();
        sorting.sort(arr);
        Arrays.sort(arrBeforeSort);
        assertTrue(Arrays.equals(arrBeforeSort, arr));
    }

    protected abstract AbstractSort getCurrentSorting();
}
