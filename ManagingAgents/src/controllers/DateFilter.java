package controllers;

import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.filter.base.AbstractFilter;
import io.github.palexdev.materialfx.i18n.I18N;
import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Extension of {@link AbstractFilter} for date fields.
 * <p></p>
 * Offers the following default {@link BiPredicateBean}s:
 * <p> - "is": checks for date equality
 * <p> - "is not": checks for date inequality
 * <p> - "after": checks if a date is after another date
 * <p> - "before": checks if a date is before another date
 * <p> - "between": checks if a date is between two other dates
 * <p></p>
 * Example:
 * <pre>
 * {@code
 *     MFXTableView table = ...;
 *     table.getFilters.addAll(
 *         ...
 *         new DateFilter<>("A date filter", ...),
 *         ...
 *     );
 * }
 * </pre>
 */
public class DateFilter<T> extends AbstractFilter<T, Date> {

    //================================================================================
    // Constructors
    //================================================================================
    public DateFilter(String name, Function<T, Date> extractor) {
        super(name, extractor, new StringConverter<Date>() {
            @Override
            public String toString(Date date) {
                return date.toString();
            }

            @Override
            public Date fromString(String s) {
                return Date.valueOf(LocalDate.parse(s));
            }
        });
    }

    //================================================================================
    // Overridden Methods
    //================================================================================
    @Override
    protected ObservableList<BiPredicateBean<Date, Date>> defaultPredicates() {
        return Stream.<BiPredicateBean<Date, Date>>of(
                new BiPredicateBean<>(I18N.getOrDefault("filter.is"), Date::equals),
                new BiPredicateBean<>(I18N.getOrDefault("filter.isNot"), (date1, date2) -> !date1.equals(date2)),
                new BiPredicateBean<>(I18N.getOrDefault("filter.greater"), (date1, date2) -> date1.compareTo(date2) > 0),
                new BiPredicateBean<>(I18N.getOrDefault("filter.lesser"), (date1, date2) -> date1.compareTo(date2) < 0)
                
        ).collect(FXCollectors.toList());
    }

    @SafeVarargs
    @Override
    protected final DateFilter<T> extend(BiPredicateBean<Date, Date>... predicateBeans) {
        Collections.addAll(super.predicates, predicateBeans);
        return this;
    }
}
