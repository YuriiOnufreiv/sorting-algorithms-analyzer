package ua.onufreiv.nc.first.sorters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The purpose of this annotation is to 'mark' a class, which is
 * annotated &#64;Sorting, as the one that contains realization of particular
 * sorting algorithm, as well as provide info about this algorithm.
 * <p>This annotation could be applied only to classes and it is available at runtime.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Sorting {
    /**
     * Provides info about type of sorting algorithm.
     * @return type of sorting from {@link Sorting.Type Type} enum
     */
    Type type();

    enum Type {
        BUBBLE_FROM_START, BUBBLE_FROM_END, QUICK, MERGE, BUILT_IN
    }
}
