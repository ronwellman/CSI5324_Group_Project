package baylor.csi5324.group_project.Domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BidTest {
    @Autowired
    TestEntityManager em;
    Bid testBid;
    User testUser;
    Commission testCommission;

    @BeforeEach
    void setup() {
        testUser = new User();
        testBid = new Bid();
        testCommission = new Commission();

        testUser.setCity("Test City");
        testUser.setPassword("password");
        testUser.setEmail("TestEmail@Email.com");
        testUser.setFirstName("TestName");
        testUser.setLastName("TestLastName");
        testUser.setPhone("555-555-5555");
        testUser.setState("TX");
        testUser.setStreet("123 Main St");
        testUser.setZip("74544");

        testCommission.setDeadline(LocalDateTime.parse("2023-01-01T00:00:00"));
        testCommission.setBudget(new BigDecimal("50.00"));
        testCommission.setDescription("A test description");
        testCommission.setListingTitle("A test title");

        testCommission.setUser(em.persistAndFlush(testUser));
        em.persistAndFlush(testCommission);


        testBid.setUser(testUser);

        testBid.setCompensationAmount(new BigDecimal("50.01"));
        testBid.setCompensationType(CompensationType.ONE_TIME);
        testBid.setCommission(testCommission);
    }

    @Test
    @DisplayName("Persist Bid: Success Path")
    void persistBid_SUCCESS() {
        assertNull(testBid.getId());

        Bid savedBid = em.persistAndFlush(testBid);

        assertNotNull(testBid.getId());
        assertNotNull(savedBid.getId());
        assertEquals(testBid, savedBid);
        assertEquals(testBid.getUser(), savedBid.getUser());
        assertEquals(testBid.getCommission(), savedBid.getCommission());
    }

    @Test
    @DisplayName("Persist Bid: Null Amount")
    void persistBid_NULLAMOUNT() {
        assertNull(testBid.getId());
        testBid.setCompensationAmount(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("compensation amount is required", violation.getMessage());
                    assertEquals("compensationAmount", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Amount Too Low")
    void persistBid_TOOLOWAMOUNT() {
        assertNull(testBid.getId());
        testBid.setCompensationAmount(new BigDecimal("-0.01"));

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must be greater than or equal to 0.00", violation.getMessage());
                    assertEquals("compensationAmount", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null Created At")
    void persistBid_NULLCREATEDAT() {
        assertNull(testBid.getId());
        testBid.setCreatedAt(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("createdAt is required", violation.getMessage());
                    assertEquals("createdAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null Updated At")
    void persistBid_NULLUPDATEDAT() {
        assertNull(testBid.getId());
        testBid.setUpdatedAt(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("updatedAt is required", violation.getMessage());
                    assertEquals("updatedAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null User")
    void persistBid_NULLUSER() {
        assertNull(testBid.getId());
        testBid.setUser(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("valid user required", violation.getMessage());
                    assertEquals("user", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null Commission")
    void persistBid_NULLCOMMISSION() {
        assertNull(testBid.getId());
        testBid.setCommission(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("valid commission required", violation.getMessage());
                    assertEquals("commission", violation.getPropertyPath().toString());
                });
    }

}