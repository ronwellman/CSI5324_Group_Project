package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.FreelancePostDTO;
import baylor.csi5324.group_project.Domain.User;

import java.util.List;
import java.util.Optional;

public interface FreelancePostService {
    public FreelancePost save(FreelancePost freelancePost);

    public FreelancePost addFreelancePost(FreelancePostDTO freelancePost);

    public Optional<FreelancePost> findById(Long id);

    public void deleteFreelancePost(Long id);

    public List<FreelancePost> findAllByUser(User user);

    public List<FreelancePost> findAllActiveFreelancePosts();

    public FreelancePost disableFreelancePost(Long id);

    public FreelancePost enableFreelancePost(Long id);

}
