package oncall.domain;

public class Day {

    private final Month month;
    private final DayOfWeek dayOfWeek;
    private final int date;

    public Day(Month month, DayOfWeek dayOfWeek, int date) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
    }

    public boolean isHoliday() {
        return dayOfWeek.isHoliday() || month.isLegalHoliday(date);
    }

    public boolean isLegalHoliday() {
        return month.isLegalHoliday(date);
    }
}
