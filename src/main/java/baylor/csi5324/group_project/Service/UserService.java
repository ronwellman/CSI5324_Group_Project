package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.User;

public interface UserService {
    public User newUser(User user);

    public User updateUser(User user);
}
