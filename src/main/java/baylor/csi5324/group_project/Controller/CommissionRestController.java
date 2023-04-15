package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.CommissionDTO;
import baylor.csi5324.group_project.Service.Impl.CommissionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
public class CommissionRestController {

    private final CommissionServiceImpl commissionService;

    public CommissionRestController(CommissionServiceImpl commissionService) {
        this.commissionService = commissionService;
    }

    @PostMapping(value = "new_commission")
    public Commission addCommission(@Valid @RequestBody CommissionDTO dto) {
        return commissionService.addCommission(dto);
    }

    @GetMapping(value = "commission")
    public Optional<Commission> getCommissionById(@RequestParam(value = "id") Long id) {
        return commissionService.findById(id);
    }

    @PostMapping(value = "/delete_commission")
    public void deleteCommission(@RequestParam(value = "id") Long id) {
        commissionService.deleteCommission(id);
    }

    @GetMapping(value = "/open_commissions")
    public List<Commission> getOpenCommissions() {
        return commissionService.findOpenCommissions();
    }

    @GetMapping(value = "commissions_by_user")
    public List<Commission> getCommissionsByUser(@RequestParam(value = "id") Long id) {
        return commissionService.findCommissionsByUser(id);
    }
}
