package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UserDTO implements Serializable {
    @NotNull(message = "email is required")
    public String email;
    
    @NotNull(message = "password is required")
    public String password;
}
