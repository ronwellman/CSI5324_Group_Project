package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.FreelancePostDTO;
import baylor.csi5324.group_project.Domain.User;

import java.util.List;

public interface FreelancePostService {
    public FreelancePost addFreelancePost(FreelancePostDTO freelancePost);

    public void deleteFreelancePost(Long id);

    public List<FreelancePost> findAllByFreelancer(User user);

    public List<FreelancePost> findAllActiveFreelancePosts();

    public FreelancePost disableFreelancePost(Long id);

    public FreelancePost enableFreelancePost(Long id);

}
