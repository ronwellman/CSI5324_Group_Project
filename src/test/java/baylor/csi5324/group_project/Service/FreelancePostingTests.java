package baylor.csi5324.group_project.Service;


import baylor.csi5324.group_project.Domain.CompensationType;
import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.FreelancePostRepository;
import baylor.csi5324.group_project.Repository.UserRepository;
import baylor.csi5324.group_project.Service.Impl.FreelancePostServiceImpl;
import baylor.csi5324.group_project.Service.Impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FreelancePostingTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private FreelancePostRepository freelancePostRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private FreelancePostServiceImpl freelancePostService;

    private User user;
    private FreelancePost post;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setFirstName("Albert");
        user.setLastName("Slater");
        user.setStreet("123 Bayside Dr");
        user.setCity("Los Angeles");
        user.setState("CA");
        user.setEmail("slater2@bayside.edu");
        user.setPhone("123-456-7890");
        user.setZip("90210");
        user.setId(1L);

//        user = userService.save(tmpUser);

        post = new FreelancePost();
        post.setListingTitle("Landscaping");
        post.setDescription("I do lawn maintenance as long as there are no plastic pink flamingos in the way.");
        post.setActive(true);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        post.setCompensationType(CompensationType.HOURLY);
        post.setCompensationAmt(30F);
        post.setFreelancer(user);
        post.setId(1L);
//        post = freelancePostService.addFreelancePost(tmpPost);

//        user.getFreelancePosts().add(post);
//        userService.save(user);
    }

    @Test
    @Order(1)
    @DisplayName("Create freelancePost: Success")
    void createNewFreelancePost() {
        given(userRepository.save(user)).willReturn(user);
        given(freelancePostRepository.save(post)).willReturn(post);

        user = userRepository.save(user);
        post.setFreelancer(user);

        FreelancePost savedPost = freelancePostService.addFreelancePost(post);
        assertNotNull(savedPost);
    }

    @Test
    @Order(2)
    @DisplayName("Search for posts by user: Success")
    void getPostsByUser() {
        given(freelancePostRepository.findAllByFreelancer(user)).willReturn(List.of(post));
        List<FreelancePost> freelancerPosts = freelancePostService.findAllByFreelancer(user);

        assertTrue(!freelancerPosts.isEmpty());
        assertEquals(post, freelancerPosts.get(0));
    }

    @Test
    @Order(3)
    @DisplayName("Delete Post: Success")
    void deletePostByUserId() {
        freelancePostService.deleteFreelancePost(post.getId());

        verify(freelancePostRepository, times(1)).deleteById(post.getId());
    }

    @Test
    @Order(4)
    @DisplayName("Find Active Posts")
    void findActivePosts() {
        given(freelancePostRepository.findAllByActive(true)).willReturn(List.of(post));

        List<FreelancePost> posts = freelancePostService.findAllActiveFreelancePosts();
        assertTrue(!posts.isEmpty());
        assertTrue(posts.get(0).equals(post));

        verify(freelancePostRepository, times(1)).findAllByActive(true);
    }

}
