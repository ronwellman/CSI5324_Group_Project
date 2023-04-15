package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/new_user", consumes = {"application/json"})
    @CrossOrigin(origins = "http://localhost:3000")
    public User addUser(@Valid @RequestBody User user) {
        System.out.println(user);
        return userService.newUser(user);
    }

}
