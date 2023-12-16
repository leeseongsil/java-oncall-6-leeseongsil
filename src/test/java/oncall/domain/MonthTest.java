package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MonthTest {

    @ParameterizedTest
    @CsvSource({"1,JANUARY", "12,DECEMBER"})
    @DisplayName("숫자에 따라 정확한 달을 반환할 수 있다")
    void findTest(int month, Month expected) {

        Month actual = Month.find(month);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"0", "13"})
    @DisplayName("잘못된 숫자(달)를 입력하면 예외를 발생시킨다")
    void findTest_whenOutOfRange_ThrowException(int month) {
        assertThatThrownBy(() -> Month.find(month))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 달은 존재하지 않습니다");
    }

    @ParameterizedTest
    @CsvSource({"JANUARY,1,true", "JANUARY,2,false"})
    @DisplayName("법정 공휴일을 알 수 있다")
    void isHolidayTest(Month month, int date, boolean expected) {

        boolean actual = month.isLegalHoliday(date);

        assertThat(actual).isEqualTo(expected);
    }
}