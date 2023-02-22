package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "paymentType is required")
    private PaymentType paymentType;

    @NotNull(message = "amount is required")
    @DecimalMin("0.00")
    private BigDecimal amount;

    @NotNull(message = "createdAt is required")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull(message = "updatedAt is required")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull(message = "confirmationCode is required")
    @NotBlank(message = "confirmationCode is required")
    private String confirmationCode;

    @ManyToOne
    @NotNull(message = "valid user is required")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @NotNull(message = "valid contract is required")
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
