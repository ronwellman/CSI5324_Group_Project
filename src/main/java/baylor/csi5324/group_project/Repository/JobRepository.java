package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    public Job findByFreelancePost(FreelancePost post);

    public Job findByCommission(Commission commission);
}
