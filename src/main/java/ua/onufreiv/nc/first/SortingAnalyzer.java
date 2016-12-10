package ua.onufreiv.nc.first;

import ua.onufreiv.nc.first.sorters.AbstractSort;

/**
 * Created by yurii on 11/17/16.
 */
public class SortingAnalyzer {

    public static long analyzeSort(AbstractSort sorter, int[] arr) {
        long startTime = System.nanoTime();
        sorter.sort(arr);
        return System.nanoTime() - startTime;
    }

    /*private int[] initialArray;

    public SortingAnalyzer(int[] initialArray) {
        this.initialArray = initialArray;
    }

    public void startAnalysis(boolean printArrays) {
        int[] arrayCopy;
        long startTime;
        long endTime;

        if(printArrays) {
            System.out.println(Arrays.toString(initialArray));
        }

        for (ua.onufreiv.nc.lab.zero.ArraySorter.Type type : ArraySorter.Type.values()) {
            arrayCopy = getArrayCopy();
            startTime = System.nanoTime();
            switch (type) {
                case BUBBLE_FROM_START:
                    ArraySorter.bubbleSortFromStart(arrayCopy);
                    break;
                case BUBBLE_FROM_END:
                    ArraySorter.bubbleSortFromEnd(arrayCopy);
                    break;
                case MERGE:
                    ArraySorter.mergeSort(arrayCopy);
                    break;
                case QUICK:
                    ArraySorter.quickSort(arrayCopy);
                    break;
                case BUILT_IN:
                    ArraySorter.builtInSort(arrayCopy);
                    break;

            }
            endTime = System.nanoTime();
            System.out.printf("--> %-17s %15s millis %n", type,
                    new DecimalFormat("###,###,###.###")
                            .format(getTimeDifInMillis(startTime, endTime)));
            if (printArrays) {
                System.out.println(Arrays.toString(arrayCopy));
            }
        }
    }

    private int[] getArrayCopy() {
        return Arrays.copyOf(initialArray, initialArray.length);
    }

    private long getTimeDifInMillis(long startTimeMillis, long endTimeMillis) {
        return (endTimeMillis - startTimeMillis);
    }*/
}
