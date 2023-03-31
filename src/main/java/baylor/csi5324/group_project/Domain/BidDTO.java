package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public class BidDTO implements Serializable {

    @NotNull(message = "compensation type is required")
    public CompensationType compensationType;
    
    @NotNull(message = "compensation amount is required")
    public BigDecimal compensationAmount;

    @NotNull(message = "userId is required")
    public Long userId;

    @NotNull(message = "commissionId is required")
    public Long commissionId;


}
