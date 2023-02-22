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
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContractTests {

    @Autowired
    TestEntityManager em;

    Contract contract;
    Job job;
    User user;
    Payment payment;

    @BeforeEach
    void createNewContract() {
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

        payment.setPaymentType(PaymentType.CREDIT);
        payment.setAmount(new BigDecimal("53.22"));
        payment.setCreatedAt(LocalDateTime.parse("2023-02-20T00:00:00"));
        payment.setUpdatedAt(LocalDateTime.parse("2023-02-28T00:00:00"));
        payment.setConfirmationCode("ABCDEFG12345");

        em.persistAndFlush(job);
        em.persistAndFlush(user);
        em.persistAndFlush(payment);

        contract.setJob(job);
        contract.setUser(user);
        contract.setPayment(payment);
    }

    @Test
    void testNewContract() {
        assertNull(contract.getId());
        Contract savedContract = em.persistAndFlush(contract);

        assertNotNull(contract.getId());
        assertNotNull(savedContract.getId());
        assertEquals(contract, savedContract);
        assertEquals(contract.getJob(), savedContract.getJob());
        assertEquals(contract.getUser(), savedContract.getUser());
        assertEquals(contract.getPayment(), savedContract.getPayment());
    }

    @Test
    void nullTimestamp() {
        assertNull(contract.getId());
        contract.setTimestamp(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(contract)
        );

        String expectedMessageNull = "valid timestamp required";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessageNull));
    }

}
