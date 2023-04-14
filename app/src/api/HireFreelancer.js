import genericPost from "./GenericPost";

const doHireFreelancer = (data, access_token, callback) => {
  const url =
    "/api/hire_freelancer?freelancePostId=" +
    data.freelancePostId +
    "&userId=" +
    data.userId;
  return genericPost(url, {}, access_token, callback);
};

export default doHireFreelancer;
