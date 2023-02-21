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

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewTest {

    @Autowired
    private TestEntityManager em;
    private User user1;
    private Job job;
    private Review review;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setFirstName("Albert");
        user1.setLastName("Slater");
        user1.setStreet("123 Bayside Dr");
        user1.setCity("Los Angeles");
        user1.setState("CA");
        user1.setEmail("slater3@bayside.edu");
        user1.setPhone("123-456-7890");
        user1.setZip("90210");

        job = new Job();
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        job.setStartDate(LocalDateTime.now());
        job.setEndDate(LocalDateTime.now());

        review = new Review();
        BigDecimal rating = new BigDecimal(1.0);
        review.setRating(rating);
        review.setDescription("Awesome job. Will definitely hire again!");
    }

    @Test
    @DisplayName("Persist Review: Success")
    void persistReview() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        review.setReviewer(savedUser);
        review.setJob(savedJob);

        assertNull(review.getId());
        Review savedReview = em.persistAndFlush(review);
        assertNotNull(savedReview.getId());
    }

    @Test
    @DisplayName("Persist Review: NULL description")
    void persistReviewDescriptionNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        review.setReviewer(savedUser);
        review.setJob(savedJob);
        review.setDescription(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(review)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("description is required", violation.getMessage());
                    Assertions.assertEquals("description", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Review: NULL rating")
    void persistReviewRatingNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        review.setReviewer(savedUser);
        review.setJob(savedJob);
        review.setRating(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(review)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("rating is required", violation.getMessage());
                    Assertions.assertEquals("rating", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Review: NULL reviewer")
    void persistReviewReviewerNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        review.setJob(savedJob);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(review)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid reviewer is required", violation.getMessage());
                    Assertions.assertEquals("reviewer", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Review: NULL job")
    void persistReviewJobNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        review.setReviewer(savedUser);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(review)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid job is required", violation.getMessage());
                    Assertions.assertEquals("job", violation.getPropertyPath().toString());
                });
    }

}