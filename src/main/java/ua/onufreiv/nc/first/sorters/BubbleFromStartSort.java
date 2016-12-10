package ua.onufreiv.nc.first.sorters;

/**
 * This class extends the {@link AbstractSort AbstractSort} class
 * and contains the realization of Bubble sorting algorithm.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Sorting(type = Sorting.Type.BUBBLE_FROM_START)
public class BubbleFromStartSort extends AbstractSort {

    /**
     * Realization of bubble start-to-end sorting algorithm
     * <p>This algorithm has the following specifications:
     * <p>1. it starts from the first element of array and move larger elements from
     *       the start to the end of array;
     * <p>2. has improvement - it remembers the position of the last swap,
     *       so it won't compare the elements that are already 'on their places'.
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {
        int currentSwap = 0;
        int lastSwap = arr.length - 1;
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < lastSwap; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    currentSwap = j;
                }
            }
            lastSwap = currentSwap;
        }
    }
}
