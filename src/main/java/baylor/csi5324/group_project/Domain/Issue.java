package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "issues")
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "description is required")
    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "issueType is required")
    private IssueType issueType;

    @ManyToOne
    @NotNull(message = "valid user is required")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @NotNull(message = "valid job is required")
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;
}
