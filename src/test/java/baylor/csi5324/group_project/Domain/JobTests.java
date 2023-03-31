package baylor.csi5324.group_project.Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobTests {

    @Autowired
    private TestEntityManager em;
    private Job job;
    private Commission commission;
    private User user;
    private FreelancePost post;

    @BeforeEach
    void setup() {
        user = new User();
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater1@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");

        commission = new Commission();
        commission.setListingTitle("Commission Title");
        commission.setDescription("Commission description");
        commission.setDeadline(LocalDate.parse("2023-04-27"));
        commission.setBudget(46.87f);

        post = new FreelancePost();
        post.setListingTitle("Landscaping");
        post.setDescription("I do lawn maintenance as long as there are no plastic pink flamingos in the way.");
        post.setActive(true);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        post.setCompensationType(CompensationType.HOURLY);
        post.setCompensationAmount(30F);

        job = new Job();
        job.setStartDate(LocalDateTime.now());
        job.setEndDate(LocalDateTime.now());

    }

    @Test
    @DisplayName("Persist Job: Commission Success")
    void persistJobCommission() {
        assertNull(job.getId());

        em.persistAndFlush(user);
        commission.setUser(user);
        Commission savedCommission = em.persistAndFlush(commission);
        job.setCommission(savedCommission);
        Job savedJob = em.persistAndFlush(job);

        assertNotNull(savedJob.getId());
    }

    @Test
    @DisplayName("Persist Job: FreelancePost Success")
    void persistJob() {
        assertNull(job.getId());

        em.persistAndFlush(user);
        post.setUser(user);
        FreelancePost savedPost = em.persistAndFlush(post);
        job.setFreelancePost(savedPost);
        Job savedJob = em.persistAndFlush(job);

        assertNotNull(savedJob.getId());
    }

//    @Test
//    @DisplayName("Persist Job: : NULL startDate")
//    void persistJobNullStartDate() {
//        em.persistAndFlush(user);
//        commission.setUser(user);
//        Commission savedCommission = em.persistAndFlush(commission);
//        job.setCommission(savedCommission);
//        job.setStartDate(null);
//
//        ConstraintViolationException e = assertThrows(
//                ConstraintViolationException.class,
//                () -> em.persistAndFlush(job)
//        );
//
//        e.getConstraintViolations()
//                .forEach(violation -> {
//                    assertEquals("valid startDate required", violation.getMessage());
//                    assertEquals("startDate", violation.getPropertyPath().toString());
//                });
//    }

//    @Test
//    @DisplayName("Persist Job: : NULL endDate")
//    void persistJobNullEndDate() {
//        em.persistAndFlush(user);
//        commission.setUser(user);
//        Commission savedCommission = em.persistAndFlush(commission);
//        job.setCommission(savedCommission);
//        job.setEndDate(null);
//
//        ConstraintViolationException e = assertThrows(
//                ConstraintViolationException.class,
//                () -> em.persistAndFlush(job)
//        );
//
//        e.getConstraintViolations()
//                .forEach(violation -> {
//                    assertEquals("valid endDate required", violation.getMessage());
//                    assertEquals("endDate", violation.getPropertyPath().toString());
//                });
//    }

}
