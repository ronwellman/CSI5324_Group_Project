package baylor.csi5324.group_project.Domain;

import baylor.csi5324.group_project.Exceptions.ContractException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContractTests {

    @Autowired
    TestEntityManager em;

    Contract contract;
    Job job;
    User consumer;
    User freelancer;

    @BeforeEach
    void createNewContract() throws ContractException {
        contract = new Contract();
        job = new Job();
        consumer = new User();
        freelancer = new User();

        job.setStartDate(LocalDateTime.parse("2023-02-20T00:00:00"));
        job.setEndDate(LocalDateTime.parse("2023-02-28T00:00:00"));

        contract.setJob(job);
        contract.setProofOfSignature(false);
        contract.setCompensationAmount(new BigDecimal("100.00"));
        contract.setCompensationType(CompensationType.ONE_TIME);

        consumer.setFirstName("John");
        consumer.setLastName("Doe");
        consumer.setStreet("Main Street");
        consumer.setCity("Chicago");
        consumer.setState("IL");
        consumer.setZip("12345");
        consumer.setEmail("john.doe@gmail.com");
        consumer.setPhone("123-456-7899");

        freelancer.setFirstName("John");
        freelancer.setLastName("Doe2");
        freelancer.setStreet("Main Street");
        freelancer.setCity("Chicago");
        freelancer.setState("IL");
        freelancer.setZip("12345");
        freelancer.setEmail("john.doe2@gmail.com");
        freelancer.setPhone("123-456-7899");

        em.persistAndFlush(job);
        em.persistAndFlush(consumer);
        em.persistAndFlush(freelancer);

        contract.setConsumer(consumer);
        contract.setFreelancer(freelancer);
    }

    @Test
    void testNewContract() {
        assertNull(contract.getId());
        Contract savedContract = em.persistAndFlush(contract);

        assertNotNull(contract.getId());
        assertNotNull(savedContract.getId());
        assertEquals(contract, savedContract);
        assertEquals(contract.getJob(), savedContract.getJob());
        assertEquals(contract.getConsumer(), savedContract.getConsumer());
        assertEquals(contract.getFreelancer(), savedContract.getFreelancer());
    }

    @Test
    void nullCompensationType() {
        assertNull(contract.getId());
        contract.setCompensationType(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(contract)
        );

        String expectedMessageNull = "compensation type is required";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessageNull));
    }

    @Test
    void nullCompensationAmount() {
        assertNull(contract.getId());
        contract.setCompensationAmount(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(contract)
        );

        String expectedMessageNull = "compensation amount is required";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessageNull));
    }

}
