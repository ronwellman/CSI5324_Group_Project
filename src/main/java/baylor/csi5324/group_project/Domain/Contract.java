package baylor.csi5324.group_project.Domain;

import baylor.csi5324.group_project.Exceptions.ContractException;
import baylor.csi5324.group_project.Exceptions.UserException;
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

    private LocalDateTime freelancerSignature;
    private LocalDateTime consumerSignature;

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

    public LocalDateTime getFreelancerSignature() {
        return freelancerSignature;
    }

    public LocalDateTime getConsumerSignature() {
        return consumerSignature;
    }

    public boolean isSigned() {
        return (freelancerSignature != null && consumerSignature != null);
    }

    public void clearSignatures() {
        this.freelancerSignature = null;
        this.consumerSignature = null;
    }

    public void sign(Long userId) throws ContractException, UserException {
        if (!this.readyToSign()) {
            if (null == this.job) {
                throw new ContractException("job is required before signature");
            }

//            if (null == this.job.getEndDate() || null == this.job.getStartDate()) {
//                throw new ContractException("start and end date for job cannot be empty");
//            }
        }

        if (null == consumer || null == freelancer) {
            throw new UserException("consumer/freelancer not assigned");
        }

        if (consumer.getId() == userId) {
            consumerSignature = LocalDateTime.now();
            return;
        }

        if (freelancer.getId() == userId) {
            freelancerSignature = LocalDateTime.now();
            return;
        }

        throw new UserException("invalid user id");
    }

    private Boolean readyToSign() {
        if (null == this.job) {
            return false;
        }

//        if (null == job.getStartDate() || null == job.getEndDate()) {
//            return false;
//        }

        return true;
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
