package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal amount;

    @CreationTimestamp
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @NotNull
    private LocalDateTime updatedAt = LocalDateTime.now();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "commission_id", referencedColumnName = "id")
    private Commission commission;
}
