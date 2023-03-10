package baylor.csi5324.group_project.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email"})})
@Data
public class User implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    @Size(max = 2, message = "2 digit state")
    private String state;
    private String zip;

    @Email(message = "valid email required")
    @NotNull(message = "valid email required")
    private String email;
    private String phone;

    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FreelancePost> freelancePosts = new HashSet<>();

    @OneToMany
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "user")
    Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Commission> commissions = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Bid> bids = new HashSet<>();

    @OneToMany(mappedBy = "reviewer")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Issue> issues;

    @OneToMany(mappedBy = "user")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "user")
    private Set<Payment> payments;

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

    public void setFreelancePosts(Set<FreelancePost> freelancePosts) {
        this.freelancePosts = freelancePosts;
    }
}
