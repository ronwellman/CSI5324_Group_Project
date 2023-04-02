package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Commission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "sentMessages", "receivedMessages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "valid user is required")
    private User user;

    @JsonIgnoreProperties(value = "commission")
    @OneToOne(mappedBy = "commission", orphanRemoval = true, cascade = CascadeType.ALL)
    private Job job;

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"commission"})
    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bid> bids = new HashSet<>();

    @NotNull(message = "Listing title cannot be null.")
    @NotBlank(message = "Listing title cannot be blank.")
    private String listingTitle;

    @NotNull(message = "Listing description cannot be null.")
    @NotBlank(message = "Listing description cannot be blank.")
    private String description;

    @NotNull(message = "Listing deadline cannot be null.")
    private LocalDateTime deadline;

    @NotNull(message = "Listing budget cannot be null.")
    @DecimalMin("0.0")
    private BigDecimal budget;

    private CommissionStatus status = CommissionStatus.OPEN;

    public String getDescription() {
        return description;
    }

    public void setDescription(String listingDescription) {
        this.description = listingDescription;
    }

    public CommissionStatus getStatus() {
        return status;
    }

    public void setStatus(CommissionStatus status) {
        this.status = status;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String title) {
        this.listingTitle = title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime listingDeadline) {
        this.deadline = listingDeadline;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal listingBudget) {
        this.budget = listingBudget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userPostingCommission) {
        this.user = userPostingCommission;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job associatedJob) {
        this.job = associatedJob;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public boolean addBids(Bid bid) {
        return this.bids.add(bid);
    }

    public Long getId() {
        return id;
    }


}
