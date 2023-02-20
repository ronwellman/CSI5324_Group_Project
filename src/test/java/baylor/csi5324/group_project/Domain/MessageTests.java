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

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageTests {

    @Autowired
    private TestEntityManager em;
    private User user1;
    private User user2;

    private Message message;

    @BeforeEach
    void setup() {
        user1 = new User();
        user1.setFirstName("Albert");
        user1.setLastName("Slater");
        user1.setStreet("123 Bayside Dr");
        user1.setCity("Los Angeles");
        user1.setState("CA");
        user1.setEmail("slater3@bayside.edu");
        user1.setPhone("123-456-7890");
        user1.setZip("90210");

        user2 = new User();
        user2.setFirstName("Zack");
        user2.setLastName("Morris");
        user2.setStreet("123 Bayside Dr");
        user2.setCity("Los Angeles");
        user2.setState("CA");
        user2.setEmail("morris3@bayside.edu");
        user2.setPhone("123-456-7890");
        user2.setZip("90210");

        message = new Message();
        message.setMessage("Meet me at the Pizza Palace at 4pm");
        message.setRead(false);
        message.setSendTime(Timestamp.from(Instant.now()));
    }

    @Test
    @DisplayName("Persist Message: Success")
    void persistMessage() {
        em.persistAndFlush(user1);
        em.persistAndFlush(user2);
        message.setSender(user1);
        message.setReceiver(user2);
        Message savedMessage = em.persistAndFlush(message);

        assertNotNull(savedMessage.getId());
        assertNotNull(message.getId());
        assertEquals(message, savedMessage);
    }

    @Test
    @DisplayName("Persist Message: NULL sender")
    void persistMessageSenderNULL() {
        em.persistAndFlush(user1);
        em.persistAndFlush(user2);
        message.setSender(null);
        message.setReceiver(user2);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(message)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid sender required", violation.getMessage());
                    Assertions.assertEquals("sender", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL receiver")
    void persistMessageReceiverNULL() {
        em.persistAndFlush(user1);
        em.persistAndFlush(user2);
        message.setSender(user1);
        message.setReceiver(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(message)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid receiver required", violation.getMessage());
                    Assertions.assertEquals("receiver", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL sendTime")
    void persistMessageSendTimeNULL() {
        em.persistAndFlush(user1);
        em.persistAndFlush(user2);
        message.setSender(user1);
        message.setReceiver(user2);
        message.setSendTime(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(message)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("send time required", violation.getMessage());
                    Assertions.assertEquals("sendTime", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL read")
    void persistMessageReadNULL() {
        em.persistAndFlush(user1);
        em.persistAndFlush(user2);
        message.setSender(user1);
        message.setReceiver(user2);
        message.setRead(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(message)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid read status required", violation.getMessage());
                    Assertions.assertEquals("read", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Message: NULL message")
    void persistMessageMessageNULL() {
        em.persistAndFlush(user1);
        em.persistAndFlush(user2);
        message.setSender(user1);
        message.setReceiver(user2);
        message.setMessage(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(message)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("a message is required", violation.getMessage());
                    Assertions.assertEquals("message", violation.getPropertyPath().toString());
                });
    }
}
