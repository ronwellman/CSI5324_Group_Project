import genericPost from "./GenericPost";
import genericGet from "./GenericGet";

const doCreateFreelancePost = (data, access_token, callback) => {
  return genericPost("/api/new_post", data, access_token, callback);
};

const doGetFreelancePosts = (access_token, callback) => {
  return genericGet("/api/active_posts", access_token, callback);
};

const doGetFreelancePostsByUser = (userId, access_token, callback) => {
  return genericGet("/api/posts_by_user?id=" + userId, access_token, callback);
};

const doDisableFreelancePost = (freelancePostId, access_token, callback) => {
  return genericPost(
    "/api/disable_post?id=" + freelancePostId,
    null,
    access_token,
    callback
  );
};

const doEnableFreelancePost = (freelancePostId, access_token, callback) => {
  return genericPost(
    "/api/enable_post?id=" + freelancePostId,
    null,
    access_token,
    callback
  );
};

export default doCreateFreelancePost;

export {
  doGetFreelancePosts,
  doGetFreelancePostsByUser,
  doDisableFreelancePost,
  doEnableFreelancePost,
};
