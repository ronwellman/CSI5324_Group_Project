package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Service.FreelancePostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
public class FreelanceServiceRestController {

    private final FreelancePostService freelancePostService;

    public FreelanceServiceRestController(FreelancePostService freelancePostService) {
        this.freelancePostService = freelancePostService;
    }


}
