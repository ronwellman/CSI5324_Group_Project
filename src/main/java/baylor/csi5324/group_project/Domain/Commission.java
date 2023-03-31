package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
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
                    "freelancePosts", "messages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "valid user is required")
    private User user;

    @OneToOne(mappedBy = "commission")
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
    private LocalDate deadline;

    @NotNull(message = "Listing budget cannot be null.")
    private Float budget;

    private Boolean active = true;

    public String getDescription() {
        return description;
    }

    public void setDescription(String listingDescription) {
        this.description = listingDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String title) {
        this.listingTitle = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate listingDeadline) {
        this.deadline = listingDeadline;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float listingBudget) {
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
