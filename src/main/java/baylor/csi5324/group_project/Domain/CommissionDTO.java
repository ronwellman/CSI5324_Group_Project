package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CommissionDTO implements Serializable {

    @NotNull(message = "userId is required")
    public Long userId;

    @NotNull(message = "listingTitle is required")
    public String listingTitle;

    @NotNull(message = "description is required")
    public String description;

    @NotNull(message = "deadline is required")
    public LocalDateTime deadline;

    @NotNull(message = "budget is required")
    public BigDecimal budget;
}
