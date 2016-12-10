package ua.onufreiv.nc.first.sorters;

/**
 * This class extends the {@link AbstractSort AbstractSort} class
 * and contains the realization of Bubble sorting algorithm.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Sorting(type = Sorting.Type.BUBBLE_FROM_END)
public class BubbleFromEndSort extends AbstractSort {

    /**
     * Realization of bubble end-to-start sorting algorithm
     * <p>This algorithm has the following specifications:
     * <p>1. it starts from the end of array and move smaller elements from
     *       the end to the start of array;
     * <p>2. has improvement - it checks whether an array is already sorted
     *       before performing next iteration of the 'external' loop
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {
        boolean isSorted = false;
        for (int i = arr.length - 1; i > 0 && !isSorted; i--) {
            isSorted = true;
            for (int j = arr.length - 1; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    isSorted = false;
                }
            }
        }

    }
}
