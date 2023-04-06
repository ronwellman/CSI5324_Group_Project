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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IssueTest {

    @Autowired
    private TestEntityManager em;
    private User user1;
    private Job job;
    private Issue issue;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setPassword("password");
        user1.setFirstName("Albert");
        user1.setLastName("Slater");
        user1.setStreet("123 Bayside Dr");
        user1.setCity("Los Angeles");
        user1.setState("CA");
        user1.setEmail("slater3@bayside.edu");
        user1.setPhone("123-456-7890");
        user1.setZip("90210");

        job = new Job();
        job.setStartDate(LocalDateTime.now());
        job.setEndDate(LocalDateTime.now());

        issue = new Issue();
        issue.setDescription("Missing source code");
        issue.setIssueType(IssueType.MISSING_ARTIFACT);
    }

    @Test
    @DisplayName("Persist Issue: Success")
    void persistIssue() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        issue.setUser(savedUser);
        issue.setJob(savedJob);

        assertNull(issue.getId());
        Issue savedIssue = em.persistAndFlush(issue);
        assertNotNull(savedIssue.getId());
    }

    @Test
    @DisplayName("Persist Message: NULL description")
    void persistIssueDescriptionNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        issue.setUser(savedUser);
        issue.setJob(savedJob);
        issue.setDescription(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(issue)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("description is required", violation.getMessage());
                    Assertions.assertEquals("description", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL issueType")
    void persistIssueIssueTypeNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        issue.setUser(savedUser);
        issue.setJob(savedJob);
        issue.setIssueType(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(issue)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("issueType is required", violation.getMessage());
                    Assertions.assertEquals("issueType", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL user")
    void persistIssueUserNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        issue.setJob(savedJob);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(issue)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid user is required", violation.getMessage());
                    Assertions.assertEquals("user", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL job")
    void persistIssueJobNULL() {
        User savedUser = em.persistAndFlush(user1);
        Job savedJob = em.persistAndFlush(job);

        issue.setUser(savedUser);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(issue)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid job is required", violation.getMessage());
                    Assertions.assertEquals("job", violation.getPropertyPath().toString());
                });
    }
}