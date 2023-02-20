package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

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

}
