package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.UserRepository;
import baylor.csi5324.group_project.Service.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Invalid username/password");
    }
}
