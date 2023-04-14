package baylor.csi5324.group_project.Auth;
/**
 * Code adapted from: https://github.com/ali-bouali/spring-boot-3-jwt-security
 */

import baylor.csi5324.group_project.Domain.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST}, allowCredentials = "true")
public class AuthenticationController {

    private final AuthenticationService authService;

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ) {
//        return ResponseEntity.ok(service.register(request));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody UserDTO dto
    ) {
        return ResponseEntity.ok(authService.authenticate(dto));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }

//    @GetMapping("/logout")
//    public ResponseEntity<AuthenticationResponse> authenticate(@AuthenticationPrincipal User user) {
//        System.out.println(user);
//        authService.revokeAllUserTokens(user);
//        return new ResponseEntity(null, HttpStatus.OK);
//    }

}
