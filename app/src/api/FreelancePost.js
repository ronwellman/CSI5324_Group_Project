import genericPost from "./GenericPost";
import genericGet from "./GenericGet";

const doCreateFreelancePost = (data, access_token, callback) => {
  return genericPost("/api/new_post", data, access_token, callback);
};

export default doCreateFreelancePost;

const doGetFreelancePosts = (access_token, callback) => {
  return genericGet("/api/active_posts", access_token, callback);
};

export { doGetFreelancePosts };
