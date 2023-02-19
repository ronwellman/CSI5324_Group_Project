package baylor.csi5324.group_project.Domain;


import baylor.csi5324.group_project.Repository.UserRepository;
import baylor.csi5324.group_project.Service.FreelancePostService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreelancePostingTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FreelancePostService freelancePostService;

    @Test
    @Order(2)
    void createNewFreelancePost() {
        User user = new User();
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");

        User savedUser = userRepository.save(user);

        FreelancePost post = new FreelancePost();
        post.setListingTitle("Landscaping");
        post.setDescription("I do lawn maintenance as long as there are no plastic pink flamingos in the way.");
        post.setActive(true);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        post.setCompensationType(CompensationType.HOURLY);
        post.setCompensationAmt(30F);
        post.setFreelancer(savedUser);
        FreelancePost savedPost = freelancePostService.save(post);

        savedUser.getFreelancePosts().add(savedPost);
        userRepository.save(savedUser);

        List<FreelancePost> freelancerPosts = freelancePostService.findAllByFreelancer(savedUser);

        assertTrue(!freelancerPosts.isEmpty());
        assertEquals(savedPost, freelancerPosts.get(0));
    }
}
