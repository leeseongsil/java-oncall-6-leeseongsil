package oncall.controller;

import java.util.List;
import java.util.function.Supplier;
import oncall.domain.Day;
import oncall.domain.EmergencyWork;
import oncall.dto.WorkSetting;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<Day> days = readRepeatedlyUntilNoException(this::readDays);
        EmergencyWork emergencyWork = readRepeatedlyUntilNoException(() -> readEmergencyWork(days));

        outputView.printEmergencyWork(emergencyWork.generate());
    }

    private List<Day> readDays() {
        WorkSetting setting = inputView.inputMonthAndStartDate();
        return Day.createList(setting.month(), setting.startDayOfWeek());
    }

    private EmergencyWork readEmergencyWork(List<Day> days) {
        List<String> weekdayOrder = inputView.inputWeekdayWorkOrder();
        EmergencyWork.validateOrder(weekdayOrder);
        List<String> weekendOrder = inputView.inputWeekendWorkOrder();
        return new EmergencyWork(weekdayOrder, weekendOrder, days);
    }

    private <T> T readRepeatedlyUntilNoException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return readRepeatedlyUntilNoException(supplier);
        }
    }
}
