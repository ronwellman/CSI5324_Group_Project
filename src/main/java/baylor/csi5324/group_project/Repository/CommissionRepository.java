package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

    public List<Commission> findCommissionByActive(Boolean active);

    public List<Commission> findCommissionByUser(User user);

    public Optional<Commission> findCommissionById(Long id);

    public Optional<Commission> findCommissionByListingTitle(String listingTitle);
}
