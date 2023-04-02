package baylor.csi5324.group_project.Domain;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    @NotNull(message = "valid sender id required")
    public Long senderId;

    @NotNull(message = "valid receiver id required")
    public Long receiverId;

    @NotNull(message = "message is required")
    public String message;
    
    public String subject;

}
