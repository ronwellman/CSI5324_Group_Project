package baylor.csi5324.group_project.Repository;

import org.springframework.data.repository.CrudRepository;

import baylor.csi5324.group_project.Domain.Commission;

import java.util.Optional;

public interface CommissionRepository extends CrudRepository<Commission, Long>{

    public Optional<Commission> findCommissionById(Long id);
}
