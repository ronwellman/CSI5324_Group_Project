package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email"})})
public class User implements Serializable, UserDetails {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "firstName is required")
    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotBlank(message = "lastName is required")
    private String lastName;
    private String street;
    private String city;
    @Size(max = 2, message = "2 digit state")
    private String state;
    private String zip;

    @Email(message = "valid email required")
    @NotNull(message = "valid email required")
    private String email;

    @NotNull(message = "password is required")
    @JsonIgnore
    private String password;

    private final String role = "User";

    private Boolean active = true;
    private Boolean expired = false;
    private Boolean locked = false;
    private Boolean credentialExpired = false;

    private String phone;

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"user"})
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FreelancePost> freelancePosts = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties({"sender", "receiver"})
    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> sentMessages = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties({"sender", "receiver"})
    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> receivedMessages = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"user"})
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Notification> notifications = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"user"})
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Commission> commissions = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"user"})
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bid> bids = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = "reviewer")
    @JsonIgnore
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = "user")
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Issue> issues = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"freelancer"})
    @JsonIgnore
    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contractsFreelancer = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"consumer"})
    @JsonIgnore
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contractsConsumer = new HashSet<>();

    @ToString.Exclude
    @JsonIgnoreProperties(value = {"user"})
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<FreelancePost> getFreelancePosts() {
        return freelancePosts;
    }

    public boolean addFreelancePosts(FreelancePost freelancePost) {
        return this.freelancePosts.add(freelancePost);
    }

    public boolean addCommission(Commission commission) {
        return this.commissions.add(commission);
    }

    public boolean addBid(Bid bid) {
        return this.bids.add(bid);
    }

    public boolean addFreelanceContract(Contract contract) {
        return this.contractsFreelancer.add(contract);
    }

    public boolean addConsumerContract(Contract contract) {
        return this.contractsConsumer.add(contract);
    }

    public boolean addReview(Review review) {
        return this.reviews.add(review);
    }

    public boolean addSentMessages(Message message) {
        return this.sentMessages.add(message);
    }

    public boolean addReceivedMessages(Message message) {
        return this.receivedMessages.add(message);
    }

    public Set<Message> getSentMessages() {
        return sentMessages;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Set<Commission> getCommissions() {
        return commissions;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public Set<Contract> getContractsFreelancer() {
        return contractsFreelancer;
    }

    public Set<Contract> getContractsConsumer() {
        return contractsConsumer;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public String getRole() {
        return role;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return active;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
