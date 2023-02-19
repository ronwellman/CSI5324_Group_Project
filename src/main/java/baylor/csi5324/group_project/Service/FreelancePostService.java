package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.FreelancePost;

public interface FreelancePostService {
    public FreelancePost newPost(FreelancePost freelancePost);

    public FreelancePost updatePost(FreelancePost freelancePost);
}
