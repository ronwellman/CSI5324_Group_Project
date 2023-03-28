package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FreelancePostRepository extends CrudRepository<FreelancePost, Long> {
    Optional<FreelancePost> findById(Long id);

    List<FreelancePost> findAllByFreelancer(User user);

    List<FreelancePost> findAllByActive(boolean active);

}
