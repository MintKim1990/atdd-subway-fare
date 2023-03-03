package nextstep.subway.domain;

public class OverTenFare implements Fare {

    private static final int OVER_TEN_MAXIMUM_DISTANCE = 50;
    private static final int DEFAULT_DISTANCE = 10;

    @Override
    public int calculateOverFare(int distance) {
        if (distance <= DEFAULT_DISTANCE) {
            return 0;
        }
        if (distance > OVER_TEN_MAXIMUM_DISTANCE) {
            return (int) ((Math.ceil((OVER_TEN_MAXIMUM_DISTANCE - DEFAULT_DISTANCE - 1) / 5) + 1) * 100);
        }
        return (int) ((Math.ceil((distance - DEFAULT_DISTANCE - 1) / 5) + 1) * 100);
    }
}