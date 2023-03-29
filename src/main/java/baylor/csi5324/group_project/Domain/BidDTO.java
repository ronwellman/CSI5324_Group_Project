package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public class BidDTO implements Serializable {

    @NotNull(message = "amount is required")
    public BigDecimal amount;

    @NotNull(message = "userId is required")
    public Long userId;

    @NotNull(message = "commissionId is required")
    public Long commissionId;
}
