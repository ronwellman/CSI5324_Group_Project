package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Review;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByReviewer(User reviewer);
}
