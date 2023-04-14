package baylor.csi5324.group_project.Auth;
/**
 * Code adapted from: https://github.com/ali-bouali/spring-boot-3-jwt-security
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("userId")
    private Long userId;
}
