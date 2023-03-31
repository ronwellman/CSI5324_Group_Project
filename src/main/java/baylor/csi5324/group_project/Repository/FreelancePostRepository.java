package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreelancePostRepository extends JpaRepository<FreelancePost, Long> {
    Optional<FreelancePost> findById(Long id);

    List<FreelancePost> findAllByUser(User user);

    List<FreelancePost> findAllByActive(boolean active);

}
