package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "compensation amount is required")
    @DecimalMin("0.00")
    private BigDecimal compensationAmount;

    @NotNull(message = "compensation type is required")
    private CompensationType compensationType;

    @CreationTimestamp
    @NotNull(message = "createdAt is required")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @NotNull(message = "updatedAt is required")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "sentMessages", "receivedMessages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull(message = "valid user required")
    private User user;

    @JsonIgnoreProperties(value = "bids")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "commission_id")
    @NotNull(message = "valid commission required")
    private Commission commission;

    public Long getId() {
        return id;
    }

    public BigDecimal getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(BigDecimal compensationAmount) {
        this.compensationAmount = compensationAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public CompensationType getCompensationType() {
        return compensationType;
    }

    public void setCompensationType(CompensationType compensationType) {
        this.compensationType = compensationType;
    }

}
