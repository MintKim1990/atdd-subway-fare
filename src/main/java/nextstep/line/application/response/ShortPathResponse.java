package nextstep.line.application.response;

import nextstep.line.domain.path.ShortPath;
import nextstep.station.application.response.StationResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ShortPathResponse {

    private List<StationResponse> stations;
    private Integer distance;
    private Integer duration;

    public ShortPathResponse(List<StationResponse> stations, Integer distance, Integer duration) {
        this.stations = stations;
        this.distance = distance;
        this.duration = duration;
    }

    public static ShortPathResponse of(ShortPath shortPath) {
        return new ShortPathResponse(
                shortPath.getStations().stream().map(StationResponse::of).collect(Collectors.toList()),
                shortPath.getDistance(),
                shortPath.getDuration()
        );
    }

    public List<StationResponse> getStations() {
        return stations;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getDuration() {
        return duration;
    }
}