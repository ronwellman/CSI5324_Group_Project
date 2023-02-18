package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String listingTitle;
    private String description;
    private LocalDate deadline;
    private float budget;

    private List<Commission> commissions = new ArrayList<>();

    public void updateCommission(Commission commission) {
        this.commissions.add(commission);
    }

}
