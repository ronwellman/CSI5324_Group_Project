package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class FreelancePostDTO {

    @NotNull(message = "title required")
    public String listingTitle;

    @NotNull(message = "compensation type required")
    public String description;

    public Boolean active;

    @NotNull(message = "compensation amount required")

    public CompensationType compensationType;
    public BigDecimal compensationAmount;

    @NotNull(message = "user id required")
    public Long userId;
}
