package nextstep.subway.maps.map.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubwayPaths {

    private final List<SubwayPath> paths;

    private SubwayPaths(List<SubwayPath> paths) {
        this.paths = Collections.unmodifiableList(new ArrayList<>(paths));
    }

    public static SubwayPaths of(List<SubwayPath> paths) {
        return new SubwayPaths(paths);
    }

    public SubwayPath findFastestArrivalPath(LocalDateTime departTime) {
        return this.paths.stream()
                .min(Comparator.comparing(timePath -> timePath.getArrivalTime(departTime)))
                .orElseThrow(() -> new RuntimeException("경로가 존재하지 않는 요청입니다."));
    }
}