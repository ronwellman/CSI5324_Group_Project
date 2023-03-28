package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.FreelancePostDTO;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Service.FreelancePostService;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class FreelanceServiceRestController {

    private final FreelancePostService freelancePostService;
    private final UserService userService;

    public FreelanceServiceRestController(FreelancePostService freelancePostService, UserService userService) {
        this.freelancePostService = freelancePostService;
        this.userService = userService;
    }

    @PostMapping(value = "/new_post")
    public FreelancePost addFreelancePost(@Valid @RequestBody FreelancePostDTO dto) {
        return freelancePostService.addFreelancePost(dto);
    }

    @PostMapping(value = "/delete_post")
    public void deleteFreelancePost(@RequestParam(value = "id") Long id) {
        freelancePostService.deleteFreelancePost(id);
    }

    @GetMapping(value = "/active_posts")
    public List<FreelancePost> getActivePosts() {
        return freelancePostService.findAllActiveFreelancePosts();
    }

    @GetMapping(value = "/post_by_user")
    public List<FreelancePost> getPostsByUserId(@RequestParam(value = "id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return null;
        }

        return freelancePostService.findAllByFreelancer(user.get());
    }

    @PostMapping(value = "/enable_post")
    public FreelancePost enableFreelancePost(@RequestParam(value = "id") Long id) {
        return freelancePostService.enableFreelancePost(id);
    }

    @PostMapping(value = "/disable_post")
    public FreelancePost disableFreelancePost(@RequestParam(value = "id") Long id) {
        return freelancePostService.disableFreelancePost(id);
    }
}
