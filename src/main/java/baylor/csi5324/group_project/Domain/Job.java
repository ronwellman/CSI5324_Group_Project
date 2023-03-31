package baylor.csi5324.group_project.Domain;

import baylor.csi5324.group_project.Exceptions.JobException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private JobStatus status = JobStatus.PENDING;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private FreelancePost freelancePost;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commission_id")
    private Commission commission;

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"job"})
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Artifact> artifacts = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"job"})
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"job"})
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Issue> issues = new HashSet<>();

    @OneToOne
    private Contract contract;

    public Long getId() {
        return id;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) throws JobException {
        if (this.status == JobStatus.CANCELLED) {
            throw new JobException("job is already cancelled");
        }
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public FreelancePost getFreelancePost() {
        return freelancePost;
    }

    public void setFreelancePost(FreelancePost freelancePost) {
        this.freelancePost = freelancePost;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Set<Artifact> getArtifacts() {
        return artifacts;
    }

    public void addArtifact(Artifact artifact) {
        this.artifacts.add(artifact);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
