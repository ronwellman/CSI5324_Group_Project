package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "description is required")
    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "rating is required")
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private BigDecimal rating;

    @ToString.Exclude
    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "messages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne
    @NotNull(message = "valid reviewer is required")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User reviewer;

    @ManyToOne
    @NotNull(message = "valid job is required")
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
