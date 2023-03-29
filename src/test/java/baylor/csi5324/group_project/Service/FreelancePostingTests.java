package baylor.csi5324.group_project.Service;


import baylor.csi5324.group_project.Domain.CompensationType;
import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.FreelancePostRepository;
import baylor.csi5324.group_project.Repository.UserRepository;
import baylor.csi5324.group_project.Service.Impl.FreelancePostServiceImpl;
import baylor.csi5324.group_project.Service.Impl.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FreelancePostingTests {

    private UserRepository userRepository;
    private FreelancePostRepository freelancePostRepository;
    private UserServiceImpl userService;
    private FreelancePostServiceImpl freelancePostService;

    private User user;
    private FreelancePost post;

    @Before
    public void setupMock() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        freelancePostRepository = mock(FreelancePostRepository.class);
        freelancePostService = new FreelancePostServiceImpl(freelancePostRepository, userService);
    }

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        freelancePostRepository = mock(FreelancePostRepository.class);
        freelancePostService = new FreelancePostServiceImpl(freelancePostRepository, userService);

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
        post.setCompensationAmount(30F);
        post.setUser(user);
        post.setId(1L);
//        post = freelancePostService.addFreelancePost(tmpPost);

//        user.getFreelancePosts().add(post);
//        userService.save(user);
    }

//    @Test
//    @Order(1)
//    @DisplayName("Create freelancePost: Success")
//    void createNewFreelancePost() {
//        FreelancePostDTO dto = new FreelancePostDTO();
////        user = userRepository.save(user);
////        post.setFreelancer(user);
////
////        dto.userId = user.getId();
////        dto.active = post.getActive();
////        dto.compensationAmount = post.getCompensationAmount();
////        dto.description = post.getDescription();
////        dto.listingTitle = post.getListingTitle();
////
//        Optional<User> optionalUser = Optional.of(user);
//        given(userRepository.save(user)).willReturn(user);
//        given(userRepository.findById(1L)).willReturn(optionalUser);
//        given(userService.findById(1L)).willReturn(optionalUser);
//        given(freelancePostRepository.save(post)).willReturn(post);
//        given(freelancePostService.addFreelancePost(dto)).willReturn(post);
//
//        FreelancePost savedPost = freelancePostService.addFreelancePost(dto);
//        assertNotNull(savedPost);
//    }

    @Test
    @Order(2)
    @DisplayName("Search for posts by user: Success")
    void getPostsByUser() {
        given(freelancePostRepository.findAllByUser(user)).willReturn(List.of(post));
        List<FreelancePost> freelancerPosts = freelancePostService.findAllByUser(user);

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
