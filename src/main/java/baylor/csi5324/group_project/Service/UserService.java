package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.User;

import java.util.Optional;

public interface UserService {
    public User save(User user);

    public Optional<User> getUserById(Long id);

    public Optional<User> findByEmail(String email);
}
