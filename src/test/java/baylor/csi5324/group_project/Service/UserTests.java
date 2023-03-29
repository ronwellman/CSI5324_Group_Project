package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void createNewUser() {
        User user = new User();
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater1@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");

        User saved = userService.save(user);

        Optional<User> userById = userService.getUserById(user.getId());
        Optional<User> userByEmail = userService.findByEmail(user.getEmail());

        assertTrue(userById.isPresent());
        assertEquals(saved, userById.get());
        assertTrue(userByEmail.isPresent());
        assertEquals(saved, userByEmail.get());
    }
}
