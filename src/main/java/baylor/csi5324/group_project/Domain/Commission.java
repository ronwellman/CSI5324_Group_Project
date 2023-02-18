package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;

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



}
