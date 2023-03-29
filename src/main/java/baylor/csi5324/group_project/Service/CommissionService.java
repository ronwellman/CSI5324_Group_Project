package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.CommissionDTO;

import java.util.List;
import java.util.Optional;

public interface CommissionService {
    public Commission addCommission(CommissionDTO dto);

    public Optional<Commission> getCommissionById(Long id);

    public List<Commission> getActiveCommissions();

    public List<Commission> getCommissionsByUser(Long id);

    public void deleteCommission(Long id);
}
