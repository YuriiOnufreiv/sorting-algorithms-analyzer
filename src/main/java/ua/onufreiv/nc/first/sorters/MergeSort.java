package ua.onufreiv.nc.first.sorters;

/**
 * This class extends the {@link AbstractSort AbstractSort} class
 * and contains the realization of Merge sorting algorithm.
 *
 * <p>Merge Sort - is a recursive algorithm that continually splits
 * a list in half. If the list is empty or has one item, it is sorted by
 * definition (the base case). If the list has more than one item, we split
 * the list and recursively invoke a merge sort on both halves.</p>
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Sorting(type = Sorting.Type.MERGE)
public class MergeSort extends AbstractSort {

    /**
     * Recursive realization of merge sorting algorithm
     * @param arr array to sort
     * @param lowIndex low index of sub array
     * @param highIndex high index of sub array
     */
    private static void mergeSort(int[] arr, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int middle = (lowIndex + highIndex) / 2;
            mergeSort(arr, lowIndex, middle);
            mergeSort(arr, middle + 1, highIndex);
            merge(arr, lowIndex, middle, highIndex);
        }
    }

    /**
     * Merger method for merging two sub arrays into one in an ascending order
     * @param arr array with elements
     * @param lowIndex low index of first sub array
     * @param middle middle element that divides array into two parts
     * @param highIndex high index of second sub array
     */
    private static void merge(int[] arr, int lowIndex, int middle, int highIndex) {
        // define sizes of parts
        int leftSize = middle - lowIndex + 1;
        int rightSize = highIndex - middle;

        // create arrays of appropriate sizes
        int[] leftPart = new int[leftSize];
        int[] rightPart = new int[rightSize];

        // write left part into appropriate array
        for (int i = 0; i < leftSize; i++) {
            leftPart[i] = arr[lowIndex + i];
        }

        // write right part into appropriate array
        for (int i = 0; i < rightSize; i++) {
            rightPart[i] = arr[middle + i + 1];
        }

        int leftCounter = 0;
        int rightCounter = 0;

        for (int i = lowIndex; i <= highIndex; i++) {
            // in case if left part 'is empty' - get element from right part
            if (leftCounter == leftSize) {
                arr[i] = rightPart[rightCounter++];
                continue;
            }

            // in case if right part 'is empty' - get element from left part
            if (rightCounter == rightSize) {
                arr[i] = leftPart[leftCounter++];
                continue;
            }

            // get lower elements from both parts and write into original array
            if (leftPart[leftCounter] <= rightPart[rightCounter]) {
                arr[i] = leftPart[leftCounter++];
            } else {
                arr[i] = rightPart[rightCounter++];
            }
        }
    }

    /**
     * Calls {@link #mergeSort(int[], int, int) mergeSort} recursive method
     * passing the array, {@code 0} as low index, and {@code arr.length - 1} as high index
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
}
