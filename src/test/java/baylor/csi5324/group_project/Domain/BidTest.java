package baylor.csi5324.group_project.Domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        testUser.setEmail("TestEmail@Email.com");
        testUser.setFirstName("TestName");
        testUser.setLastName("TestLastName");
        testUser.setPhone("555-555-5555");
        testUser.setState("TX");
        testUser.setStreet("123 Main St");
        testUser.setZip("74544");

        testCommission.setDeadline(LocalDate.parse("2023-01-01"));
        testCommission.setBudget(50.00f);
        testCommission.setDescription("A test description");
        testCommission.setListingTitle("A test title");

        em.persistAndFlush(testUser);
        em.persistAndFlush(testCommission);


        testBid.setUser(testUser);

        testBid.setAmount(new BigDecimal("50.01"));
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
        testBid.setAmount(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must not be null", violation.getMessage());
                    assertEquals("amount", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Amount Too Low")
    void persistBid_TOOLOWAMOUNT() {
        assertNull(testBid.getId());
        testBid.setAmount(new BigDecimal("-0.01"));

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must be greater than or equal to 0.00", violation.getMessage());
                    assertEquals("amount", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null Created At")
    void persistBid_NULLCREATEDAT(){
        assertNull(testBid.getId());
        testBid.setCreatedAt(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must not be null", violation.getMessage());
                    assertEquals("createdAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null Updated At")
    void persistBid_NULLUPDATEDAT(){
        assertNull(testBid.getId());
        testBid.setUpdatedAt(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must not be null", violation.getMessage());
                    assertEquals("updatedAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null User")
    void persistBid_NULLUSER(){
        assertNull(testBid.getId());
        testBid.setUser(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must not be null", violation.getMessage());
                    assertEquals("user", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Bid: Null Commission")
    void persistBid_NULLCOMMISSION(){
        assertNull(testBid.getId());
        testBid.setCommission(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(testBid)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("must not be null", violation.getMessage());
                    assertEquals("commission", violation.getPropertyPath().toString());
                });
    }

}