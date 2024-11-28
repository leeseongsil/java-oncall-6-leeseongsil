package oncall.domain;

import java.util.List;
import java.util.stream.Stream;

public enum Month {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));

    private final int order;
    private final int maxDate;
    private final List<Integer> holidays;

    Month(int order, int maxDate, List<Integer> holidays) {
        this.order = order;
        this.maxDate = maxDate;
        this.holidays = holidays;
    }

    public static Month find(int order) {
        return Stream.of(Month.values())
                .filter(month -> month.isMatchOrder(order))
                .findAny().orElseThrow();
    }

    private boolean isMatchOrder(int order) {
        return order == this.order;
    }

    public int getMaxDate() {
        return maxDate;
    }
}
