package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.UserRepository;
import baylor.csi5324.group_project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User newUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
