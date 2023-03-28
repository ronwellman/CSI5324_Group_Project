package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.FreelancePostRepository;
import baylor.csi5324.group_project.Service.FreelancePostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreelancePostServiceImpl implements FreelancePostService {

    private final FreelancePostRepository freelanceRepository;

    public FreelancePostServiceImpl(FreelancePostRepository freelanceRepository) {
        this.freelanceRepository = freelanceRepository;
    }

    public FreelancePost addFreelancePost(FreelancePost freelancePost) {
        return freelanceRepository.save(freelancePost);
    }

    @Override
    public void deleteFreelancePost(Long id) {
        freelanceRepository.deleteById(id);
    }

    public List<FreelancePost> findAllByFreelancer(User user) {
        return freelanceRepository.findAllByFreelancer(user);
    }

    @Override
    public List<FreelancePost> findAllActiveFreelancePosts() {
        return freelanceRepository.findAllByActive(true);
    }

    @Override
    public FreelancePost disableFreelancePost(Long id) {
        Optional<FreelancePost> post = freelanceRepository.findById(id);
        if (post.isEmpty()) {
            return null;
        }

        FreelancePost freelancePost = post.get();
        freelancePost.setActive(false);

        return freelanceRepository.save(freelancePost);
    }

    @Override
    public FreelancePost enableFreelancePost(Long id) {
        Optional<FreelancePost> post = freelanceRepository.findById(id);
        if (post.isEmpty()) {
            return null;
        }

        FreelancePost freelancePost = post.get();
        freelancePost.setActive(true);

        return freelanceRepository.save(freelancePost);
    }
}
