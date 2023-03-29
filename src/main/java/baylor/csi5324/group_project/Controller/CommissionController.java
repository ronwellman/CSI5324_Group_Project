package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Repository.CommissionRepository;
import baylor.csi5324.group_project.Service.Impl.CommissionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@RequestMapping("/commissions")
@SessionAttributes("commissionRequest")
public class CommissionController {

    private CommissionRepository commissionRepository;
    private CommissionServiceImpl commissionService;

    @Autowired
    public CommissionController(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

    @ModelAttribute(name = "commission")
    public Commission commission() {
        return new Commission();
    }

    @GetMapping("/request-service")
    public String CommissionRequestForm() {
        return "commissionRequestForm";
    }

    @PostMapping
    public String processRequest(
            @Valid Commission commission, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "commissionRequestForm";
        }

        commissionRepository.save(commission);
        sessionStatus.setComplete();

        return "redirect:/commissions/request-service";
    }

    // @GetMapping("/commission")
    // public Optional<Commission> getCommission(Long id){
    //     return commissionRepository.findCommissionById(id);
    // }

}
