package work.lince.predict.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analyze {

    private String id;

    @NotEmpty
    private List<Point> sample;

    @NotEmpty
    private List<Point> predict;

    private String user;

    private OffsetDateTime create;

    private Map<String, Object> details;

}
