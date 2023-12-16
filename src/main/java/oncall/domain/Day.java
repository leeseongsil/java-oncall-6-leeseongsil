package oncall.domain;

public class Day {

    private final Month month;
    private final int date;
    private final DayOfWeek dayOfWeek;

    public Day(Month month, int date, DayOfWeek dayOfWeek) {
        this.month = month;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isHoliday() {
        return dayOfWeek.isHoliday() || month.isLegalHoliday(date);
    }

    public boolean isLegalHoliday() {
        return month.isLegalHoliday(date);
    }

    public int getMonth() {
        return month.getOrder();
    }

    public int getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek.getName();
    }
}
