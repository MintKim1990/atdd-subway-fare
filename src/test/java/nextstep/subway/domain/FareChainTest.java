package nextstep.subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FareChainTest {

    private static final int DEFAULT_FARE = 1_250;

    private final FareChain fareChain = new FareChain(
        List.of(
            new DefaultFare(),
            new OverTenFare(),
            new OverFiftyFare()
        )
    );

    @DisplayName("10이내는 기본 요금이다")
    @Test
    void 기본요금_구간은_10이내() {
        assertThat(fareChain.calculateFare(10)).isEqualTo(DEFAULT_FARE);
    }

    @DisplayName("거리가 10 초과 50이하이면 5마다 100원씩 추가된다 100원이 추가 되는 경우")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void 거리가_10_초과_50이하이면_5마다_100원씩_추가된다_100원이_추가_되는_경우(int additionalDistance) {
        assertThat(fareChain.calculateFare(10 + additionalDistance)).isEqualTo(DEFAULT_FARE + 100);
    }

    @DisplayName("거리가 10 초과 50이하이면 5마다 100원씩 추가된다 800원이 추가 되는 경우")
    @ParameterizedTest
    @ValueSource(ints = {36, 37, 38, 39, 40})
    void 거리가_10_초과_50이하이면_5마다_100원씩_추가된다_800원이_추가_되는_경우(int additionalDistance) {
        assertThat(fareChain.calculateFare(10 + additionalDistance)).isEqualTo(DEFAULT_FARE + 800);
    }

    @DisplayName("거리가 10 초과 50이하이면 5마다 100원씩 추가된다 800원이 추가 되는 경우")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void 거리가_50_초과_이면_8마다_100원씩_추가된다_100원이_추가_되는_경우(int additionalDistance) {
        assertThat(fareChain.calculateFare(50 + additionalDistance)).isEqualTo(DEFAULT_FARE + 800 + 100);
    }
}