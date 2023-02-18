package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;

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

    private String listingTitle;
    private String description;
    private LocalDate deadline;
    private float budget;

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

    public float getBudget(){
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

    public void setBudget(float listingBudget){
        this.budget = listingBudget;
    }

}
