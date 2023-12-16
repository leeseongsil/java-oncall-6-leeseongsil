package oncall.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class EmergencyWork {

    private static final int MIN_WORKER = 5;
    private static final int MAX_WORKER = 35;
    private static final int MAX_NAME = 5;

    private final List<String> weekdayOrder;
    private final List<String> weekendOrder;
    private final List<Day> dates;

    public EmergencyWork(List<String> weekdayOrder, List<String> weekendOrder, List<Day> dates) {
        validateOrder(weekdayOrder, weekendOrder);

        this.weekdayOrder = weekdayOrder;
        this.weekendOrder = weekendOrder;
        this.dates = dates;
    }

    private void validateOrder(List<String> weekdayOrder, List<String> weekendOrder) {
        validateOneTimes(weekdayOrder);
        validateSize(weekdayOrder);
        weekdayOrder.forEach(this::validateName);
        validateSameComposition(weekdayOrder, weekendOrder);
    }

    private void validateOneTimes(List<String> order) {
        if (!isAppearedOneTime(order)) {
            throw new IllegalArgumentException("비상 근무자는 순번에 1회 편성되어야 합니다");
        }
    }

    private boolean isAppearedOneTime(List<String> order) {
        return order.size() == new HashSet<>(order).size();
    }

    private void validateSize(List<String> order) {
        if (order.size() < MIN_WORKER || order.size() > MAX_WORKER) {
            throw new IllegalArgumentException("근무자 최소 5명, 최대 35명이어야 합니다");
        }
    }

    private void validateName(String name) {
        if (name.isBlank() || name.length() > MAX_NAME) {
            throw new IllegalArgumentException("닉네임은 1자 이상 5자 이하이어야 합니다");
        }
    }

    private void validateSameComposition(List<String> weekdayOrder, List<String> weekendOrder) {
        if (!isSameComposition(weekdayOrder, weekendOrder)) {
            throw new IllegalArgumentException("비상 근무자는 평일 순번, 휴일 순번에 각각 1회 편성되어야 합니다");
        }
    }

    private boolean isSameComposition(List<String> weekdayOrder, List<String> weekendOrder) {
        return Stream.of(weekendOrder)
                .allMatch(weekendOrder::contains);
    }
}
