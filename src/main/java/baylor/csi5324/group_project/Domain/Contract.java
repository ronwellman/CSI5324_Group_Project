package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean proofOfSignature;
    private LocalDate timestamp;

    private List<Contract> contracts = new ArrayList<>();

    public void uploadContract(Contract contract) {
        this.contracts.add(contract);
    }

}
