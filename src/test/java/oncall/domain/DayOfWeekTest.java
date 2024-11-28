package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DayOfWeekTest {

    @ParameterizedTest
    @CsvSource({"월,MONDAY", "일,SUNDAY"})
    @DisplayName("입력한 글자에 따라 적당한 요일을 반환할 수 있다")
    void findTest(String name, DayOfWeek expected) {
        DayOfWeek actual = DayOfWeek.find(name);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"월요일", "' '", "통"})
    @DisplayName("해당 글자를 입력하지 않으면 예외를 던진다")
    void findTest_whenNotExistName_throwException(String name) {
        assertThatThrownBy(() -> DayOfWeek.find(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 요일은 존재하지 않습니다");
    }

    @ParameterizedTest
    @CsvSource({"MONDAY,TUESDAY", "SATURDAY,SUNDAY", "SUNDAY,MONDAY"})
    @DisplayName("해당 날짜의 다음 날을 알 수 있다")
    void findNextDayOfWeekTest(DayOfWeek day, DayOfWeek expected) {

        DayOfWeek actual = day.findNextDayOfWeek();

        assertThat(actual).isEqualTo(expected);
    }
}