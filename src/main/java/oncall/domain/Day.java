package oncall.domain;

import java.util.List;
import java.util.stream.Stream;

public class Day {

    private final Month month;
    private final int date;
    private final DayOfWeek dayOfWeek;

    private Day(Month month, int date, DayOfWeek dayOfWeek) {
        this.month = month;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public static List<Day> createList(Month month, DayOfWeek startDayOfWeek) {
        return Stream.iterate(new Day(month, 1, startDayOfWeek),
                        day -> new Day(month, day.date + 1, day.dayOfWeek.findNextDayOfWeek()))
                .limit(month.getMaxDate())
                .toList();
    }

    public boolean isHoliday() {
        return dayOfWeek.isHoliday() || month.isLegalHoliday(date);
    }

    public boolean isShowHoliday() {
        return month.isLegalHoliday(date) && !dayOfWeek.isHoliday();
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
