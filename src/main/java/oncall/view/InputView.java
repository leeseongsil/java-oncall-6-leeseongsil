package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Stream;
import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.dto.WorkOrder;
import oncall.dto.WorkSetting;

public class InputView {

    private static final String MONTH_REQUEST = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String WEEKDAY_REQUEST = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String WEEKEND_REQUEST = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String SPLIT_LETTER = ",";

    public WorkSetting inputMonthAndStartDate() {
        print(MONTH_REQUEST);
        return readWorkSetting();
    }

    private WorkSetting readWorkSetting() {
        String[] messages = read().split(SPLIT_LETTER, -1);
        validateMonthMessage(messages);
        return new WorkSetting(Month.find(toInt(messages[0])), DayOfWeek.find(messages[1].trim()));
    }

    private void validateMonthMessage(String[] messages) {
        if (messages.length != 2) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다");
        }
    }

    private int toInt(String inputMessage) {
        try {
            return Integer.parseInt(inputMessage.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다");
        }
    }

    public WorkOrder inputWorkOrder() {
        print(WEEKDAY_REQUEST);
        List<String> weekdayOrder = readStrings();
        print(WEEKEND_REQUEST);
        List<String> weekendOrder = readStrings();

        return new WorkOrder(weekdayOrder, weekendOrder);
    }

    private List<String> readStrings() {
        return Stream.of(read().split(SPLIT_LETTER, -1))
                .map(String::trim)
                .toList();
    }

    private void print(String message) {
        System.out.print(message);
    }

    private String read() {
        return Console.readLine();
    }
}
