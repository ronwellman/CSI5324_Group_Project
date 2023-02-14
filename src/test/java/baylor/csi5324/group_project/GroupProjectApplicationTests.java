package baylor.csi5324.group_project;

import baylor.csi5324.group_project.Model.User;
import baylor.csi5324.group_project.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.desktop.OpenFilesEvent;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupProjectApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

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

        assert(userById.isPresent());
        assert(saved.equals(userById.get()));
        assert(userByEmail.isPresent());
        assert(saved.equals((userByEmail.get())));

    }

}
