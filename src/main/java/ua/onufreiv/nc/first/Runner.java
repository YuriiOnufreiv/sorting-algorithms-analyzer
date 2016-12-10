package ua.onufreiv.nc.first;

import ua.onufreiv.nc.first.excel.SortersStatistics;
import ua.onufreiv.nc.first.excel.StatisticsExcelWriter;
import ua.onufreiv.nc.first.fillers.ArrayFillers;
import ua.onufreiv.nc.first.reflection.ReflectionSorter;
import ua.onufreiv.nc.first.sorters.AbstractSort;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

/**
 * Created by yurii on 11/1/16.
 */
public class Runner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ReflectionSorter reflectionSorter
                = new ReflectionSorter(AbstractSort.class.getPackage().getName(), ArrayFillers.class);

        TreeSet<Integer> sizes = new TreeSet<>();
        sizes.add(25_000);
        sizes.add(50_000);
        sizes.add(100_000);

        SortersStatistics statistics = reflectionSorter.generateStatistics(sizes);
        new StatisticsExcelWriter(statistics).write(new File("statistics.xlsx"));

    }
}
