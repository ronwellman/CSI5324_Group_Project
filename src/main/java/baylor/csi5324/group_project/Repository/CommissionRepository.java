package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.CommissionStatus;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    public List<Commission> findCommissionsByStatus(CommissionStatus status);

    public List<Commission> findCommissionByUser(User user);

    public Optional<Commission> findCommissionById(Long id);

    public Optional<Commission> findCommissionByListingTitle(String listingTitle);
}
