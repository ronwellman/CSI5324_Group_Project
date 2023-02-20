package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Commission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "commission")
    private Job job;

    @OneToMany(mappedBy = "commission")
    private Set<Bid> bids;


    @NotNull(message="Listing title cannot be null.")
    @NotBlank(message="Listing title cannot be blank.")
    private String listingTitle;

    @NotNull(message="Listing description cannot be null.")
    @NotBlank(message="Listing description cannot be blank.")
    private String description;

    @NotNull(message="Listing deadline cannot be null.")
    private LocalDate deadline;

    @NotNull(message="Listing budget cannot be null.")
    private Float budget;


    // Getters
    public String getListingTitle() {
        return listingTitle;
    }

    public String description(){
        return description;
    }

    public LocalDate getDeadline(){
        return deadline;
    }

    public Float getBudget(){
        return budget;
    }

    public User getUser(){
        return user;
    }

    public Job getJob(){
        return job;
    }

    public Set<Bid> getBids(){
        return bids;
    }

    //Setters
    public void setListingTitle(String title){
        this.listingTitle = title;
    }

    public void setDescription(String listingDescription){
        this.description = listingDescription;
    }

    public void setDeadline(LocalDate listingDeadline){
        this.deadline = listingDeadline;
    }

    public void setBudget(Float listingBudget){
        this.budget = listingBudget;
    }

    public void setUser(User userPostingCommission){
        this.user = userPostingCommission;
    }

    public void setJob(Job associatedJob){
        this.job = associatedJob;
    }

    public void setBids(Set<Bid> bidsOnCommission){
        this.bids = bidsOnCommission;
    }

}
