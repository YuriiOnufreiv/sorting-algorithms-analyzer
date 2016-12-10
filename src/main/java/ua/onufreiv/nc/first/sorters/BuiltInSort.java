package ua.onufreiv.nc.first.sorters;

import java.util.Arrays;

/**
 * This class extends the {@link AbstractSort AbstractSort} class and it's
 * realization is performed with the help of {@code Arrays.sort(int[])}
 * method from {@code java.util} package.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Sorting(type = Sorting.Type.BUILT_IN)
public class BuiltInSort extends AbstractSort {

    /**
     * Sorts the array with the help of {@code Arrays.sort(int[])}
     * method from {@code java.util} package.
     * @param arr array to sort
     * @see Arrays#sort(int[])
     */
    @Override
    public void sort(int[] arr) {
        Arrays.sort(arr);
    }
}
