package baylor.csi5324.group_project.Domain;

import baylor.csi5324.group_project.Exceptions.ContractException;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PaymentTest {
    @Autowired
    TestEntityManager em;

    Contract contract;
    Job job;
    User freelancer;
    User consumer;
    Payment payment;

    @BeforeEach
    void setup() throws ContractException {
        contract = new Contract();
        job = new Job();
        freelancer = new User();
        payment = new Payment();

        job.setStartDate(LocalDateTime.parse("2023-02-20T00:00:00"));
        job.setEndDate(LocalDateTime.parse("2023-02-28T00:00:00"));

        contract.setJob(job);
        contract.setCompensationAmount(new BigDecimal("100.00"));
        contract.setCompensationType(CompensationType.ONE_TIME);

        job.setStartDate(LocalDateTime.parse("2023-02-20T00:00:00"));
        job.setEndDate(LocalDateTime.parse("2023-02-28T00:00:00"));

        freelancer.setPassword("password");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setStreet("Main Street");
        freelancer.setCity("Chicago");
        freelancer.setState("IL");
        freelancer.setZip("12345");
        freelancer.setEmail("john.doe@gmail.com");
        freelancer.setPhone("123-456-7899");

        consumer = new User();
        consumer.setPassword("password");
        consumer.setFirstName("Zack");
        consumer.setLastName("Morris");
        consumer.setStreet("123 Bayside Dr");
        consumer.setCity("Los Angeles");
        consumer.setState("CA");
        consumer.setEmail("morris3@bayside.edu");
        consumer.setPhone("123-456-7890");
        consumer.setZip("90210");

        payment.setPaymentType(PaymentType.CREDIT);
        payment.setAmount(new BigDecimal("53.22"));
        payment.setCreatedAt(LocalDateTime.parse("2023-02-20T00:00:00"));
        payment.setUpdatedAt(LocalDateTime.parse("2023-02-28T00:00:00"));
        payment.setConfirmationCode("ABCDEFG12345");

        em.persistAndFlush(job);
        em.persistAndFlush(freelancer);
        em.persistAndFlush(consumer);

        contract.setFreelancer(freelancer);
        em.persistAndFlush(contract);

        payment.setContract(contract);
        payment.setUser(consumer);
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