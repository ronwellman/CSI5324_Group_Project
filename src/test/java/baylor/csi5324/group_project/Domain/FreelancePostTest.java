package baylor.csi5324.group_project.Domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class FreelancePostTest {
    @Autowired
    private TestEntityManager em;
    private User user;
    private FreelancePost post;

    @BeforeEach
    void setup() {
        user = new User();
        user.setPassword("password");
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater1@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");

        post = new FreelancePost();
        post.setListingTitle("Landscaping");
        post.setDescription("I do lawn maintenance as long as there are no plastic pink flamingos in the way.");
        post.setActive(true);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        post.setCompensationType(CompensationType.HOURLY);
        post.setCompensationAmount(new BigDecimal(("30")));
    }

    @Test
    @DisplayName("Persist FreelancePost: Success")
    void persistFreelancePost() {
        User savedUser = em.persistAndFlush(user);
        post.setUser(savedUser);
        FreelancePost savedPost = em.persistAndFlush(post);

        assertNotNull(savedPost.getId());
        assertEquals(savedUser, savedPost.getUser());
    }

    @Test
    @DisplayName("Persist FreelancePost: NULL user")
    void persistFreelancePostFreeUserNull() {
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(post)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid user required", violation.getMessage());
                    Assertions.assertEquals("user", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist FreelancePost: NULL listingTitle")
    void persistFreelancePostTitleNull() {
        User savedUser = em.persistAndFlush(user);
        post.setUser(savedUser);
        post.setListingTitle(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(post)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("title required", violation.getMessage());
                    Assertions.assertEquals("listingTitle", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist FreelancePost: NULL compensationType")
    void persistFreelancePostCompensationTypeNull() {
        User savedUser = em.persistAndFlush(user);
        post.setUser(savedUser);
        post.setCompensationType(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(post)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("compensation type required", violation.getMessage());
                    Assertions.assertEquals("compensationType", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist FreelancePost: NULL compensationAmt")
    void persistFreelancePostCompensationAmtNull() {
        User savedUser = em.persistAndFlush(user);
        post.setUser(savedUser);
        post.setCompensationAmount(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(post)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("compensation amount required", violation.getMessage());
                    Assertions.assertEquals("compensationAmount", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist FreelancePost: NULL createdAt")
    void persistFreelancePostCreatedAtAmtNull() {
        User savedUser = em.persistAndFlush(user);
        post.setUser(savedUser);
        post.setCreatedAt(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(post)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("creation timestamp required", violation.getMessage());
                    Assertions.assertEquals("createdAt", violation.getPropertyPath().toString());
                });
    }
}
