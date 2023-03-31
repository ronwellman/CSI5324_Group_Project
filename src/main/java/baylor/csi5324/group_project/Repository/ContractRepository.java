package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Contract;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    public List<Contract> findAllByFreelancer(User freelancer);

    public List<Contract> findAllByConsumer(User consumer);
}
