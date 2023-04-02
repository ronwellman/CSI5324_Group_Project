package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Message implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "sentMessages", "receivedMessages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    @NotNull(message = "valid sender required")
    private User sender;

    @JsonIgnoreProperties(value =
            {
                    "freelancePosts", "sentMessages", "receivedMessages", "notifications",
                    "bids", "reviews", "issues", "payments", "contracts",
                    "commissions", "contractsConsumer", "contractsFreelancer"
            })
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    @NotNull(message = "valid receiver required")
    private User receiver;

    @NotNull(message = "a message is required")
    private String message;

    private String subject;

    private Timestamp sendTime = Timestamp.from(Instant.now());

    private Boolean read = false;

    private Timestamp readTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}