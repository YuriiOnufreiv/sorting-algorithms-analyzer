package ua.onufreiv.nc.first.reflection;

import org.reflections.Reflections;
import ua.onufreiv.nc.first.excel.SortersStatistics;
import ua.onufreiv.nc.first.fillers.Filler;
import ua.onufreiv.nc.first.sorters.AbstractSort;
import ua.onufreiv.nc.first.sorters.Sorting;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * This class contains logic for finding all sorting methods that are
 * subclasses of {@link AbstractSort} class and all filler methods
 * of particular array generator class annotated with {@link Filler}
 * annotation,
 *
 * @author  Yurii Onufreiv
 * @version 1.0
 * @since 02/12/2016
 */
public class ReflectionSorter {
    private static final Logger logger = Logger.getLogger(ReflectionSorter.class.toString());

    /** Map that contains sorts */
    private Map<Sorting.Type, Class<? extends AbstractSort>> sortingSubClasses;
    /** Map that contains filler methods */
    private Map<Filler.Type, Method> fillerMethods;

    /**
     * Constructor that receives package name for finding subclasses,
     * class that generates arrays
     * @param packageName package where sort subclasses will be find out
     * @param arrGeneratorClass class that should contains methods for arrays generator
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ReflectionSorter(String packageName, Class arrGeneratorClass)
            throws IOException, ClassNotFoundException {
        sortingSubClasses = new EnumMap<>(Sorting.Type.class);
        fillerMethods = new EnumMap<>(Filler.Type.class);
        fillSortingSubclasses(packageName);
        fillFillerMethods(arrGeneratorClass);
    }

    /**
     * Finds all methods in {@code arrGeneratorClass} that are annotated with
     * {@link Filler} annotation,
     * @param arrGeneratorClass class to found fillers
     */
    private void fillFillerMethods(Class arrGeneratorClass) {
        for (Method method : arrGeneratorClass.getDeclaredMethods()) {
            Filler fillerAnnotation = method.getAnnotation(Filler.class);
            if (fillerAnnotation != null) {
                fillerMethods.put(fillerAnnotation.type(), method);
                logger.info("@" + fillerAnnotation.type() + " filler method in " +
                        arrGeneratorClass.getName() + " found: " + method.getName() + "\n");
            }
        }
    }

    /**
     * Finds all subclasses of {@link AbstractSort} that are annotated with
     * {@link Sorting} annotation,
     * @param packageName packages where subclasses wil be found
     */
    private void fillSortingSubclasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        for (Class subclass : reflections.getSubTypesOf(AbstractSort.class)) {
            Sorting sortingAnnotation = (Sorting) subclass.getAnnotation(Sorting.class);
            if (sortingAnnotation != null) {
                sortingSubClasses.put(sortingAnnotation.type(), subclass);
                logger.info("@" + sortingAnnotation.type() +
                        " subclass of AbstractSorter found: " + subclass.getName() + "\n");
            }

        }
    }

    /**
     * Generates statistics for all found sorting algorithms
     * @param arrSizes set that contains sizes of arrays for statistics generation
     * @return {@link SortersStatistics} object wit generated statistics
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    public SortersStatistics generateStatistics(TreeSet<Integer> arrSizes) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        SortersStatistics statistics = new SortersStatistics();
        for (Filler.Type fillerType : fillerMethods.keySet()) {
            for (Sorting.Type sortingType : sortingSubClasses.keySet()) {
                for (Integer arrSize : arrSizes) {
                    logger.info("START SORT: " + fillerType +
                            ", " + sortingType +
                            ", size=" + arrSize + "\n");
                    int[] arr = (int[]) fillerMethods.get(fillerType).invoke(null, arrSize);
                    long time = sort(sortingType, Arrays.copyOf(arr, arr.length));
                    statistics.addEntry(fillerType, sortingType, arrSize, time);
                    logger.info("END SORT: " + fillerType +
                            ", " + sortingType +
                            ", size=" + arrSize +
                            ", time=" + time + "\n");
                }
            }
        }
        return statistics;
    }

    /**
     * Sorts array with the specified {@code sortingType} using reflection
     * @param sortingType type of sorting to apply
     * @param arr array to sort
     * @return duration of sorting
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    public long sort(Sorting.Type sortingType, int[] arr)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Class sortingClass = sortingSubClasses.get(sortingType);
        long start = System.nanoTime();
        sortingClass.getMethod("sort", arr.getClass()).invoke(sortingClass.newInstance(), arr);
        long end = System.nanoTime();
        return end - start;
    }
}
