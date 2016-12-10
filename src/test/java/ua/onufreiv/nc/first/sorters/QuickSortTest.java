package ua.onufreiv.nc.first.sorters;

/**
 * Created by yurii on 12/2/16.
 */
public class QuickSortTest extends AbstractSortTest {
    @Override
    protected AbstractSort getCurrentSorting() {
        return new QuickSort();
    }
}