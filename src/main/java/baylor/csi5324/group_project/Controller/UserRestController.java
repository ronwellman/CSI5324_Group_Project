package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000")
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

//    @Autowired
//    private JwtUtil jwtUtil;

//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getUsers() {
//        List<User> users = userService.
//        return ResponseEntity.ok(users);
//    }

}
