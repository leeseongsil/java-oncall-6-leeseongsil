package oncall.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import oncall.dto.EmergencyWorkDto;
import oncall.utils.WorkerQueue;

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

    public static void validateOrder(List<String> order) {
        validateIsNotOverlapped(order);
        validateSize(order);
        order.forEach(EmergencyWork::validateName);
    }

    private void validateOrder(List<String> weekdayOrder, List<String> weekendOrder) {
        validateIsNotOverlapped(weekdayOrder);
        validateSize(weekdayOrder);
        weekdayOrder.forEach(EmergencyWork::validateName);
        validateSameComposition(weekdayOrder, weekendOrder);
    }

    private static void validateIsNotOverlapped(List<String> order) {
        if (!isAppearedOneTime(order)) {
            throw new IllegalArgumentException("비상 근무자는 순번에 1회 편성되어야 합니다");
        }
    }

    private static boolean isAppearedOneTime(List<String> order) {
        return order.size() == new HashSet<>(order).size();
    }

    private static void validateSize(List<String> order) {
        if (order.size() < MIN_WORKER || order.size() > MAX_WORKER) {
            throw new IllegalArgumentException("근무자 최소 5명, 최대 35명이어야 합니다");
        }
    }

    private static void validateName(String name) {
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
        for (String name : weekdayOrder) {
            if (!weekendOrder.contains(name)) {
                return false;
            }
        }
        return true;
    }

    public List<EmergencyWorkDto> generate() {
        WorkerQueue weekdayQueue = new WorkerQueue(weekdayOrder);
        WorkerQueue weekendQueue = new WorkerQueue(weekendOrder);
        List<EmergencyWorkDto> answer = new LinkedList<>();
        String previousWorker = null;
        for (Day day : dates) {
            EmergencyWorkDto result = findWorker(day, weekdayQueue, weekendQueue, previousWorker);
            answer.add(result);
            previousWorker = result.name();
        }
        return answer;
    }

    private EmergencyWorkDto findWorker(Day day, WorkerQueue weekdayQueue, WorkerQueue weekendQueue,
                                        String previousWorker) {
        if (day.isHoliday()) {
            return new EmergencyWorkDto(weekendQueue.take(previousWorker), day);
        }
        return new EmergencyWorkDto(weekdayQueue.take(previousWorker), day);
    }
}
