package oncall.dto;

import oncall.domain.DayOfWeek;
import oncall.domain.Month;

public record WorkSetting(Month month, DayOfWeek startDayOfWeek) {
}
