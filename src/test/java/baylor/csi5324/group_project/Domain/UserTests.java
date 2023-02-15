package baylor.csi5324.group_project.Domain;

import baylor.csi5324.group_project.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createNewUser() {
        User user = new User();
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");

        User saved = userRepository.save(user);

        Optional<User> userById = userRepository.findById(user.getId());
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        assertTrue(userById.isPresent());
        assertEquals(saved, userById.get());
        assertTrue(userByEmail.isPresent());
        assertEquals(saved, userByEmail.get());
    }
}
