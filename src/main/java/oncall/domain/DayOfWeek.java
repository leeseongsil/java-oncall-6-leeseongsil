package oncall.domain;

public enum DayOfWeek {
    MONDAY("월", false, 0),
    TUESDAY("화", false, 1),
    WEDNESDAY("수", false, 2),
    THURSDAY("목", false, 3),
    FRIDAY("금", false, 4),
    SATURDAY("토", true, 5),
    SUNDAY("일", true, 6);

    private final String name;
    private final boolean isHoliday;
    private final int index;

    DayOfWeek(String name, boolean isHoliday, int index) {
        this.name = name;
        this.isHoliday = isHoliday;
        this.index = index;
    }

    public boolean isHoliday() {
        return isHoliday;
    }
}
