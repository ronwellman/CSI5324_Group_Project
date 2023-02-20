package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @NotNull(message = "compensation type required")
    private CompensationType compensationType;

    @NotNull(message = "compensation amount required")
    private Float compensationAmt;

    @NotNull(message = "creation timestamp required")
    private Timestamp createdAt;

    private Timestamp lastUpdatedAT;

    @JsonIdentityReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "valid user required")
    private User freelancer;

    @OneToOne
    private Job job;

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
        result = 31 * result + compensationAmt.hashCode();
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

    public Float getCompensationAmt() {
        return compensationAmt;
    }

    public void setCompensationAmt(Float compensationAmt) {
        this.compensationAmt = compensationAmt;
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

    public User getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
    }
}