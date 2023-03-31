package baylor.csi5324.group_project.Domain;

import baylor.csi5324.group_project.Exceptions.ContractException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean proofOfSignature = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull(message = "compensation type is required")
    private CompensationType compensationType;

    @NotNull(message = "compensation amount is required")
    @DecimalMin("0.00")
    private BigDecimal compensationAmount;

    @ToString.Exclude
    @JsonIgnoreProperties(value = "contract")
    @OneToOne
    private Job job;

    @ToString.Exclude
    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "messages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne
    @JoinColumn(name = "freelancer_id")
    private User freelancer;

    @ManyToOne
    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "messages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @JoinColumn(name = "consumer_id")
    private User consumer;

    @OneToOne
    private Payment payment;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public CompensationType getCompensationType() {
        return compensationType;
    }

    public void setCompensationType(CompensationType compensationType) {
        if (!this.isSigned()) {
            this.compensationType = compensationType;
        }
    }

    public BigDecimal getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(BigDecimal compensationAmount) {
        if (!this.isSigned()) {
            this.compensationAmount = compensationAmount;
        }
    }

    public Job getJob() {
        return job;
    }

    public Payment getPayment() {
        return payment;
    }

    public boolean isSigned() {
        return this.proofOfSignature;
    }

    public void setProofOfSignature(boolean isSigned) throws ContractException {
        if (!this.proofOfSignature) {
            if (null == this.job) {
                throw new ContractException("job is required before signature");
            }

            if (null == this.job.getEndDate() || null == this.job.getStartDate()) {
                throw new ContractException("start and end date for job cannot be empty");
            }
        }

        this.proofOfSignature = isSigned;
    }

    public void setJob(Job associatedJob) {
        this.job = associatedJob;
    }

    public void setPayment(Payment contractPayment) {
        this.payment = contractPayment;
    }

    public User getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
    }

    public User getConsumer() {
        return consumer;
    }

    public void setConsumer(User consumer) {
        this.consumer = consumer;
    }
}
