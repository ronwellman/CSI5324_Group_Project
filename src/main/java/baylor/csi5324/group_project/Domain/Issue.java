package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

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

    @ToString.Exclude
    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "sentMessages", "receivedMessages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne
    @NotNull(message = "valid user is required")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @NotNull(message = "valid job is required")
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        return Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
