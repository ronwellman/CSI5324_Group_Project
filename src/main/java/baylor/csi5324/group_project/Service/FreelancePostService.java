package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;

import java.util.List;

public interface FreelancePostService {
    public FreelancePost save(FreelancePost freelancePost);

    public List<FreelancePost> findAllByFreelancer(User user);

}
