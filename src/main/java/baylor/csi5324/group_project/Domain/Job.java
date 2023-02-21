package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    @NotNull(message = "valid createdAT required")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @NotNull(message = "valid updatedAt required")
    private LocalDateTime updatedAt;
    @NotNull(message = "valid startDate required")
    private LocalDateTime startDate;
    @NotNull(message = "valid endDate required")
    private LocalDateTime endDate;

    @OneToOne
    private FreelancePost freelancePost;

    @OneToOne
    private Commission commission;

    @OneToMany(mappedBy = "job")
    private Set<Artifact> artifacts = new HashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<Issue> issues = new HashSet<>();

    @OneToOne
    private Contract contract;
}
