package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.User;

import java.util.Optional;

public interface UserService {
    public User save(User user);

    public User newUser(User user);

    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String email);

}
