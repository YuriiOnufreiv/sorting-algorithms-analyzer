package ua.onufreiv.nc.first.excel;

import ua.onufreiv.nc.first.fillers.Filler;
import ua.onufreiv.nc.first.sorters.Sorting;

import java.util.*;

/**
 * This class contains logic for statistics management
 *
 * @author  Yurii Onufreiv
 * @version 1.0
 * @since 02/12/2016
 */
public class SortersStatistics {
    /** Map that contains statistics entries regarding to particular type of filler */
    private Map<Filler.Type, List<StatisticsEntry>> map;
    /** Set that contains sizes of arrays for generation and further sorting */
    private Set<Integer> arraySizesSet;

    /**
     * Default constructor that prepares all fields for further usage
     */
    public SortersStatistics() {
        map = new EnumMap<>(Filler.Type.class);
        arraySizesSet = new TreeSet<>();
        for (Filler.Type fillerType : Filler.Type.values()) {
            map.put(fillerType, new LinkedList<>());
        }
    }

    /**
     * Adds new statistics observation
     * @param fillerType type of filler used
     * @param sortingType type of sorting used
     * @param arraySize size of generated array
     * @param time duration of sorting
     */
    public void addEntry(Filler.Type fillerType, Sorting.Type sortingType, int arraySize, long time) {
        map.get(fillerType).add(new StatisticsEntry(fillerType, sortingType, arraySize, time));
        arraySizesSet.add(arraySize);
    }

    /**
     * Returns duration of sorting for specified characteristics
     * @param fillerType type of filler
     * @param sortingType type of sorting
     * @param arraySize size of array
     * @return duration of sorting; -1 if there is no entry with the specified characteristics
     */
    public long getTimeFor(Filler.Type fillerType, Sorting.Type sortingType, int arraySize) {
        for (StatisticsEntry entry : map.get(fillerType)) {
            if (entry.getFillerType().equals(fillerType)
                    && entry.getSortingType().equals(sortingType)
                    && entry.getArraySize() == arraySize) {
                return entry.getTime();
            }
        }
        return -1;
    }

    /**
     * Returns map with statistics observations
     * @return entire statistics
     */
    public Map<Filler.Type, List<StatisticsEntry>> getMap() {
        return Collections.unmodifiableMap(map);
    }

    /**
     * Returns set with used array sizes
     * @return set with array sizes
     */
    public Set<Integer> getArraySizesSet() {
        return Collections.unmodifiableSet(arraySizesSet);
    }

    /**
     * Inner class that represents particular statistics observation
     */
    class StatisticsEntry {
        private Filler.Type fillerType;
        private Sorting.Type sortingType;
        private int arraySize;
        private long time;

        private StatisticsEntry(Filler.Type fillerType, Sorting.Type sortingType, int arraySize, long time) {
            this.fillerType = fillerType;
            this.sortingType = sortingType;
            this.arraySize = arraySize;
            this.time = time;
        }

        public Filler.Type getFillerType() {
            return fillerType;
        }

        public Sorting.Type getSortingType() {
            return sortingType;
        }

        public int getArraySize() {
            return arraySize;
        }

        public long getTime() {
            return time;
        }
    }
}
