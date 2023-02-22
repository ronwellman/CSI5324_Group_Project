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
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaymentTest {
    @Autowired
    TestEntityManager em;

    Contract contract;
    Job job;
    User user;
    User user2;
    Payment payment;

    @BeforeEach
    void setup() {
        contract = new Contract();
        job = new Job();
        user = new User();
        payment = new Payment();

        contract.setProofOfSignature(false);
        contract.setTimestamp(LocalDate.now());

        job.setCreatedAt(LocalDateTime.parse("2023-02-18T12:34:05"));
        job.setUpdatedAt(LocalDateTime.parse("2023-02-19T11:55:07"));
        job.setStartDate(LocalDateTime.parse("2023-02-20T00:00:00"));
        job.setEndDate(LocalDateTime.parse("2023-02-28T00:00:00"));

        user.setFirstName("John");
        user.setLastName("Doe");
        user.setStreet("Main Street");
        user.setCity("Chicago");
        user.setState("IL");
        user.setZip("12345");
        user.setEmail("john.doe@gmail.com");
        user.setPhone("123-456-7899");

        user2 = new User();
        user2.setFirstName("Zack");
        user2.setLastName("Morris");
        user2.setStreet("123 Bayside Dr");
        user2.setCity("Los Angeles");
        user2.setState("CA");
        user2.setEmail("morris3@bayside.edu");
        user2.setPhone("123-456-7890");
        user2.setZip("90210");

        payment.setPaymentType(PaymentType.CREDIT);
        payment.setAmount(new BigDecimal("53.22"));
        payment.setCreatedAt(LocalDateTime.parse("2023-02-20T00:00:00"));
        payment.setUpdatedAt(LocalDateTime.parse("2023-02-28T00:00:00"));
        payment.setConfirmationCode("ABCDEFG12345");

        em.persistAndFlush(job);
        em.persistAndFlush(user);
        em.persistAndFlush(user2);

        contract.setJob(job);
        contract.setUser(user);
        em.persistAndFlush(contract);

        payment.setContract(contract);
        payment.setUser(user2);
    }

    @Test
    @DisplayName("Persist Payment: Success")
    void persistPayment() {
        assertNull(payment.getId());
        Payment savedPayment = em.persistAndFlush(payment);
        assertNotNull(savedPayment.getId());
    }

    @Test
    @DisplayName("Persist Payment: NULL paymentType")
    void persistPaymentPaymentTypeNULL() {

        payment.setPaymentType(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("paymentType is required", violation.getMessage());
                    Assertions.assertEquals("paymentType", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Payment: NULL amount")
    void persistPaymentAmountNULL() {

        payment.setAmount(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("amount is required", violation.getMessage());
                    Assertions.assertEquals("amount", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Payment: NULL createdAt")
    void persistPaymentCreatedAtNULL() {

        payment.setCreatedAt(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("createdAt is required", violation.getMessage());
                    Assertions.assertEquals("createdAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Payment: NULL updatedAt")
    void persistPaymentUpdatedAtNULL() {

        payment.setUpdatedAt(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("updatedAt is required", violation.getMessage());
                    Assertions.assertEquals("updatedAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Payment: NULL confirmationCode")
    void persistPaymentConfirmationCodeNULL() {

        payment.setConfirmationCode(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("confirmationCode is required", violation.getMessage());
                    Assertions.assertEquals("confirmationCode", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Payment: NULL user")
    void persistPaymentUserNULL() {

        payment.setUser(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid user is required", violation.getMessage());
                    Assertions.assertEquals("user", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Payment: NULL contract")
    void persistPaymentContractNULL() {

        payment.setContract(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(payment)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid contract is required", violation.getMessage());
                    Assertions.assertEquals("contract", violation.getPropertyPath().toString());
                });
    }

}