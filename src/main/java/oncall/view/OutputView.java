package oncall.view;

import java.util.List;
import oncall.domain.Day;
import oncall.dto.EmergencyWorkDto;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printEmergencyWork(List<EmergencyWorkDto> dtos) {
        dtos.forEach(this::printEmergencyWork);
    }

    private void printEmergencyWork(EmergencyWorkDto dto) {
        printDay(dto.day());
        printName(dto.name());
        printLineChanger();
    }

    private void printDay(Day day) {
        if (day.isShowHoliday()) {
            print("%d월 %d일 %s(휴일) ".formatted(day.getMonth(), day.getDate(), day.getDayOfWeek()));
            return;
        }
        print("%d월 %d일 %s ".formatted(day.getMonth(), day.getDate(), day.getDayOfWeek()));
    }

    private void printName(String name) {
        print(name);
    }

    public void printErrorMessage(Exception exception) {
        print(ERROR_PREFIX.concat(exception.getMessage()));
        printLineChanger();
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void printLineChanger() {
        System.out.println();
    }
}
