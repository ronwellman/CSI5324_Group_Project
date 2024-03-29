package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freelance_posting")
public class FreelancePost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "title required")
    private String listingTitle;

    private String description;

    private Boolean active = false;

    @NotNull(message = "compensation type required")
    private CompensationType compensationType;

    @NotNull(message = "compensation amount required")
    @DecimalMin("0.00")
    private BigDecimal compensationAmount;

    @NotNull(message = "creation timestamp required")
    private Timestamp createdAt;

    private Timestamp lastUpdatedAT;


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

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"freelancePost, commission"})
    @OneToMany(mappedBy = "freelancePost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Job> jobs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreelancePost that = (FreelancePost) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + listingTitle.hashCode();
        result = 31 * result + compensationType.hashCode();
        result = 31 * result + compensationAmount.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompensationType getCompensationType() {
        return compensationType;
    }

    public void setCompensationType(CompensationType compensationType) {
        this.compensationType = compensationType;
    }

    public BigDecimal getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(BigDecimal compensationAmount) {
        this.compensationAmount = compensationAmount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastUpdatedAT() {
        return lastUpdatedAT;
    }

    public void setLastUpdatedAT(Timestamp lastUpdatedAT) {
        this.lastUpdatedAT = lastUpdatedAT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean addJob(Job job) {
        return this.jobs.add(job);
    }
}