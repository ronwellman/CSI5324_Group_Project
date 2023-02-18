package baylor.csi5324.group_project.Controller;
import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Repository.CommissionRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/commissions")
public class CommissionController {

    private CommissionRepository commissionRepository;

    public CommissionController(CommissionRepository commissionRepository){
        this.commissionRepository = commissionRepository;
    }

    @GetMapping("/commission")
    public Optional<Commission> getCommission(Long id){
        return commissionRepository.findCommissionById(id);
    }

}
