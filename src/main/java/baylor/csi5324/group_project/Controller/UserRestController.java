package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new_user")
    public User addUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

}
