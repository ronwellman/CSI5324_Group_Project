package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String email);
}