package baylor.csi5324.group_project.Domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTests {

    @Autowired
    private TestEntityManager em;
    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setPassword("password");
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater1@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");
    }

    @Test
    @DisplayName("Persist User: Success")
    void persistUser() {
        assertNull(user.getId());

        User savedUser = em.persistAndFlush(user);

        assertNotNull(savedUser.getId());
        assertNotNull(user.getId());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    @DisplayName("Persist User: NULL email")
    void nullEmail() {
        assertNull(user.getId());
        user.setEmail(null);

        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(user)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    assertEquals("valid email required", violation.getMessage());
                    assertEquals("email", violation.getPropertyPath().toString());
                });
    }


}
