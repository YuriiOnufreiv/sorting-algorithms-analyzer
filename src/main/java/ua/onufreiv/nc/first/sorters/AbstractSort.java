package ua.onufreiv.nc.first.sorters;

/**
 * This abstract class provides 'skeleton' for any sorting realization.
 * The developer need only subclass this class and
 * define the {@code sort(int[])} method.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
public abstract class AbstractSort {
    /**
     * Abstract method for further realization of particular
     * sorting algorithm
     * @param arr array to sort
     */
    public abstract void sort(int[] arr);

    /**
     * Swaps to element in the array
     * @param arr array with elements to swap
     * @param from index of first element
     * @param to index of second element
     */
    protected static void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
