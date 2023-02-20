package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Message implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull(message = "valid sender required")
    private User sender;

    @ManyToOne
    @NotNull(message = "valid receiver required")
    private User receiver;

    @NotNull(message = "a message is required")
    private String message;


    @NotNull(message = "send time required")
    private Timestamp sendTime;

    @NotNull(message = "valid read status required")
    private Boolean read = false;

    private Timestamp readTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}