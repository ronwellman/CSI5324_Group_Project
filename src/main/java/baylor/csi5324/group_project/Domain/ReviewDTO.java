package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReviewDTO implements Serializable {

    @NotNull(message = "user id is required")
    public Long userId;

    @NotNull(message = "job id is required")
    public Long jobId;

    @NotNull(message = "description is required")
    public String description;

    @NotNull(message = "rating is required")
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    public BigDecimal rating;


}
