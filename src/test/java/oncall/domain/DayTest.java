package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayTest {

    @Test
    @DisplayName("토요일이나 일요일인 경우, 주말임을 확인할 수 있다")
    void isHolidayTest_whenSaturday() {
        Day day = new Day(Month.JANUARY, 3, DayOfWeek.SATURDAY);

        boolean actual = day.isHoliday();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("법정 공휴일 인 경우, 주말임을 확인할 수 있다")
    void isHolidayTest_whenLegalHoliday() {
        Day day = new Day(Month.JANUARY, 1, DayOfWeek.MONDAY);

        boolean actual = day.isHoliday();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("토/일요일, 법정 공휴일도 아닌 경우, Holiday가 아니다")
    void isHolidayTest_whenWeekday() {
        Day day = new Day(Month.JANUARY, 3, DayOfWeek.MONDAY);

        boolean actual = day.isHoliday();

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("법정 공휴일 이면서 토/일이 아닌 경우에만, 출력 형식에 주말을 포함한다")
    void isShowHoliday_whenLegalHolidayAndWeekday() {
        Day day = new Day(Month.JANUARY, 1, DayOfWeek.MONDAY);

        boolean actual = day.isShowHoliday();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("법정 공휴일 이면서 월~금, 출력 형식에 주말을 포함하지 않는다")
    void isShowHoliday_whenLegalHolidayAndWeekend() {
        Day day = new Day(Month.JANUARY, 1, DayOfWeek.SUNDAY);

        boolean actual = day.isShowHoliday();

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("법정 공휴일이 아닌 경우, 출력 형식에 주말을 포함하지 않는다")
    void isShowHoliday_whenNotLegalHoliday() {
        Day day = new Day(Month.JANUARY, 3, DayOfWeek.SUNDAY);

        boolean actual = day.isShowHoliday();

        assertThat(actual).isFalse();
    }
}