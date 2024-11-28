package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmergencyWorkTest {

    @Test
    void validationTest() {
        // TODO 단위 테스트 구현
        //주말-주중 구성 테스트
    }

    @Test
    @DisplayName("근무자의 이름이 중복되어서는 안된다")
    void validateTest_whenNameIsOverlapped() {
        List<String> names = List.of("111", "222", "333", "444", "555", "111");

        assertThatThrownBy(() -> EmergencyWork.validateOrder(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비상 근무자는 순번에 1회 편성되어야 합니다");
    }

    @ParameterizedTest
    @CsvSource({"4", "36"})
    @DisplayName("근무자 수는 5명 이상 35명 이하이어야 한다")
    void validateTest_whenCountOfWorkerIsOverRange(int size) {
        List<String> names = makeNameList(size);

        assertThatThrownBy(() -> EmergencyWork.validateOrder(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("근무자 최소 5명, 최대 35명이어야 합니다");
    }

    List<String> makeNameList(int size) {
        return IntStream.range(0, size)
                .mapToObj(String::valueOf)
                .toList();
    }

    @ParameterizedTest
    @CsvSource({"''", "' '", "가가가가가가"})
    @DisplayName("각 근무자 이름은 공백이거나 5자를 초과해서는 안된다")
    void validateTest_whenNameIsOutOfRange(String name) {
        List<String> names = List.of("111", "222", "333", "444", "555", name);

        assertThatThrownBy(() -> EmergencyWork.validateOrder(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("닉네임은 1자 이상 5자 이하이어야 합니다");
    }

    @Test
    void generateTest() {
        // TODO 단위 테스트 구현
    }
}