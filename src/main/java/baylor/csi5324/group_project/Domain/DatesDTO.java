package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DatesDTO implements Serializable {
    @NotNull(message = "job id is required")
    public Long jobId;

    @NotNull(message = "start date is required")
    public LocalDateTime startDate;

    @NotNull(message = "end date is required")
    public LocalDateTime endDate;
}
