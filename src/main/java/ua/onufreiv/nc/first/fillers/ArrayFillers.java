package ua.onufreiv.nc.first.fillers;

import java.util.Random;

/**
 * This class contains {@code public static} methods that are responsible
 * for generating arrays with random numbers according to predefined criteria
 *
 * @author  Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
public class ArrayFillers {

    static {
        random = new Random();
    }

    /** Range step used for generating random numbers */
    private final static int RANGE_STEP = 3;

    /** Instance of {@code java.util.Random} class used for generating random numbers */
    private static Random random;

    /**
     * Returns an already increasingly sorted array filled with a random {@code int} numbers
     * in range from {@code 0} (inclusive) to {@code length * RANGE_STEP} (inclusive).
     *
     * <p> Uses {@link #getRandomIntInRange(int, int) getRandomIntInRange} method for
     * random numbers generation.
     * @param length amount of numbers in an array
     * @return array of {@code int} numbers sorted by increasing
     */
    @Filler(type = Filler.Type.SORTED)
    public static int[] getSorted(int length) {
        int[] array = new int[length];
        for(int i = 0; i < length; i++) {
            int min = i * RANGE_STEP;
            int max = min + RANGE_STEP;
            array[i] = getRandomIntInRange(min, max);
        }
        return array;
    }

    /**
     * Returns an already increasingly sorted array, except of the last element, filled with a random
     * {@code int} numbers in range {@code 0} (inclusive) to {@code length * RANGE_STEP} (inclusive).
     * The last element is a random generated {@code int} number, from the same range, inserted
     * ignoring the order of numbers in array.
     *
     * <p> Uses {@link #getRandomIntInRange(int, int) getRandomIntInRange} method for
     * random numbers generation.
     * @param length amount of numbers in an array
     * @return array of {@code int} numbers, sorted by increasing, except of the last element
     */
    @Filler(type = Filler.Type.SORTED_EXCEPT_LAST)
    public static int[] getSortedWithRandomAtEnd(int length) {
        int[] array = new int[length];
        int sortedArrayLength = length - 1;
        // write already sorted array
        System.arraycopy(getSorted(sortedArrayLength), 0, array, 0, sortedArrayLength);
        // insert last random number
        array[sortedArrayLength] = getRandomIntInRange(0, length * RANGE_STEP);
        return array;
    }

    /**
     * Returns an already decreasingly sorted array filled with a random {@code int} numbers
     * in range from {@code 0} (inclusive) to {@code length * RANGE_STEP} (inclusive).
     *
     * <p> Uses {@link #getRandomDoubleInRange(int, int) getRandomDoubleInRange} method for
     * random numbers generation.
     * @param length amount of numbers in an array
     * @return array of {@code int} numbers sorted by decreasing
     */
    @Filler(type = Filler.Type.REVERSE_SORTED)
    public static int[] getReverseSorted(int length) {
        int[] array = new int[length];
        int max = length * RANGE_STEP;
        int min;
        for(int i = 0; i < length; i++) {
            min = max - RANGE_STEP;
            array[i] = (int) getRandomDoubleInRange(min, max);
            max = min;
        }
        return array;
    }

    /**
     * Returns an already increasingly sorted array filled with a random {@code int} numbers
     * in range from {@code 0} (inclusive) to {@code length * RANGE_STEP} (inclusive).
     *
     * <p> Uses {@code ints(long, int, int)} method of {@code java.util.Random} class
     * for random numbers generation.
     * @param length amount of numbers in an array
     * @return array of {@code int} numbers sorted by decreasing
     * @see Random#ints(long, int, int)
     */
    @Filler(type = Filler.Type.RANDOM)
    public static int[] getTotallyRandom(int length) {
        return random.ints(length, 0, length * RANGE_STEP + 1).toArray();
    }

    /**
     * Returns a pseudorandom {@code int} number between {@code min} (inclusive) and {@code max}
     * (inclusive) using the {@code java.util.Random.nextInt(int bound)} method
     * @param min lower bound (inclusive)
     * @param max upper bound (inclusive)
     * @return pseudorandom {@code int} number
     * @see Random#nextInt
     */
    private static int getRandomIntInRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns a pseudorandom {@code double} value between {@code min} (inclusive)
     * and {@code max} (inclusive) using the {@code Math.random()} method
     * @param min lower bound (inclusive)
     * @param max upper bound (inclusive)
     * @return pseudorandom {@code double} number
     * @see Math#random
     */
    private static double getRandomDoubleInRange(int min, int max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }
}
