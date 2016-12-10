package ua.onufreiv.nc.first.sorters;

/**
 * This class extends the {@link AbstractSort AbstractSort} class
 * and contains the realization of Quick sorting algorithm.
 * <p>
 * <p>Quick Sort - first divides a large array into two smaller sub-arrays:
 * the low elements and the high elements. It can then recursively sort sub-arrays.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Sorting(type = Sorting.Type.QUICK)
public class QuickSort extends AbstractSort {

    /**
     * Recursive realization of quick sorting algorithm
     *
     * @param arr  array to sort
     * @param low  low index of sub array
     * @param high high index of sub array
     */
    private static void quickSort(int[] arr, int low, int high) {
        int index = partition(arr, low, high);
        if (low < index - 1)
            quickSort(arr, low, index - 1);
        if (index < high)
            quickSort(arr, index, high);
    }

    /**
     * Performs division of array into sub arrays on the basis of pivot element,
     * that is taken from the {@code arr[(low + high)/2]} position;
     *
     * @param arr  array to sort
     * @param low  low index of sub array
     * @param high high index of sub array
     * @return value of pivot
     */
    private static int partition(int[] arr, int low, int high) {
        int i = low, j = high;
        int tmp;
        int pivot = arr[(low + high) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };

        return i;
    }

    /**
     * Calls {@link #quickSort(int[], int, int) mergeSort} recursive method
     * passing the array, {@code 0} as low index, and {@code arr.length - 1} as high index
     *
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
}
