package ua.onufreiv.nc.first.fillers;

import java.lang.annotation.*;

/**
 * The purpose of this annotation is to 'mark' a method, which is
 * annotated &#64;Filler, as the one that generates the specific type of
 * random array, as well as provide info about this type of array.
 *
 * <p>This annotation could be applied only to methods and it is available at runtime.
 *
 * @author Yurii Onufreiv
 * @version 1.0
 * @since 21/11/2016
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Filler {
    /**
     * Provides info about type of generated array
     * @return type of array from {@link Filler.Type Type} enum
     */
    Type type();

    enum Type {
        SORTED, SORTED_EXCEPT_LAST, REVERSE_SORTED, RANDOM
    }
}
