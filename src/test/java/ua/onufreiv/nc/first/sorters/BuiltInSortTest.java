package ua.onufreiv.nc.first.sorters;

/**
 * Created by yurii on 12/2/16.
 */
public class BuiltInSortTest extends AbstractSortTest {
    @Override
    protected AbstractSort getCurrentSorting() {
        return new BuiltInSort();
    }
}