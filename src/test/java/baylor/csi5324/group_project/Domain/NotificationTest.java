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
class NotificationTest {

    @Autowired
    private TestEntityManager em;
    private User user1;
    private Notification notification;

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

        notification = new Notification();
        notification.setDescription("A message has arrive");
        notification.setCreatedAt(Timestamp.from(Instant.now()));
        notification.setRead(false);
        notification.setNotificationType(NotificationType.MESSAGE);
    }

    @Test
    @DisplayName("Persist Notification: Success")
    void notificationTest() {
        User savedUser = em.persistAndFlush(user1);

        assertNull(notification.getId());
        notification.setUser(savedUser);

        Notification savedNotification = em.persistAndFlush(notification);
        assertNotNull(savedNotification.getId());
    }

    @Test
    @DisplayName("Persist Notification: Null Description")
    void notificationDescriptionNull() {
        User savedUser = em.persistAndFlush(user1);

        assertNull(notification.getId());
        notification.setUser(savedUser);
        notification.setDescription(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(notification)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("description is required", violation.getMessage());
                    Assertions.assertEquals("description", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Notification: Null CreatedAt")
    void notificationCreatedAtNull() {
        User savedUser = em.persistAndFlush(user1);

        assertNull(notification.getId());
        notification.setUser(savedUser);
        notification.setCreatedAt(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(notification)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("createdAt timestamp required", violation.getMessage());
                    Assertions.assertEquals("createdAt", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Notification: Null Read")
    void notificationReadNull() {
        User savedUser = em.persistAndFlush(user1);

        assertNull(notification.getId());
        notification.setUser(savedUser);
        notification.setRead(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(notification)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("read status is required", violation.getMessage());
                    Assertions.assertEquals("read", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Notification: Null NotificationType")
    void notificationNotificationTypeNull() {
        User savedUser = em.persistAndFlush(user1);

        assertNull(notification.getId());
        notification.setUser(savedUser);
        notification.setNotificationType(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(notification)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("notification type is required", violation.getMessage());
                    Assertions.assertEquals("notificationType", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Notification: Null User")
    void notificationUserNull() {
        User savedUser = em.persistAndFlush(user1);

        assertNull(notification.getId());
        notification.setUser(savedUser);
        notification.setUser(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(notification)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("a valid user is required", violation.getMessage());
                    Assertions.assertEquals("user", violation.getPropertyPath().toString());
                });
    }
}