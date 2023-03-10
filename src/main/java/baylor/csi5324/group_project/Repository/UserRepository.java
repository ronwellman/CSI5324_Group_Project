package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String email);
}