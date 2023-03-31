package baylor.csi5324.group_project.Domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommissionTests {

    @Autowired
    TestEntityManager em;

    private Commission commission;
    private User testUser;

    @BeforeEach
    void createNewCommission() {

        testUser = new User();

        testUser.setCity("Test City");
        testUser.setEmail("TestEmail@Email.com");
        testUser.setFirstName("TestName");
        testUser.setLastName("TestLastName");
        testUser.setPhone("555-555-5555");
        testUser.setState("TX");
        testUser.setStreet("123 Main St");
        testUser.setZip("74544");

        commission = new Commission();
        commission.setListingTitle("Commission Title");
        commission.setDescription("Commission description");
        commission.setDeadline(LocalDate.parse("2023-04-27"));
        commission.setBudget(new BigDecimal("46.87"));
        commission.setUser(em.persistAndFlush(testUser));
    }

    @Test
    void testNewCommission() {
        assertNull(commission.getId());
        Commission savedCommission = em.persistAndFlush(commission);

        assertNotNull(commission.getId());
        assertNotNull(savedCommission.getId());
        assertEquals(commission, savedCommission);
    }

    @Test
    void nullListingTitle() {

        assertNull(commission.getId());
        commission.setListingTitle(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(commission)
        );

        String expectedMessageNull = "Listing title cannot be null.";
        String expectedMessageBlank = "Listing title cannot be blank.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessageNull) & actualMessage.contains(expectedMessageBlank));
    }

    @Test
    void nullListingDescription() {

        assertNull(commission.getId());
        commission.setDescription(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(commission)
        );

        String expectedMessageNull = "Listing description cannot be null.";
        String expectedMessageBlank = "Listing description cannot be blank.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessageNull) & actualMessage.contains(expectedMessageBlank));
    }

    @Test
    void nullListingDeadline() {

        assertNull(commission.getId());
        commission.setDeadline(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(commission)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("Listing deadline cannot be null.", violation.getMessage());
                    assertEquals("deadline", violation.getPropertyPath().toString());
                });
    }

    @Test
    void nullListingBudget() {

        assertNull(commission.getId());
        commission.setBudget(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(commission)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("Listing budget cannot be null.", violation.getMessage());
                    assertEquals("budget", violation.getPropertyPath().toString());
                });
    }

}

