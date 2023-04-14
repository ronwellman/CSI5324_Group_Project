import genericPost from "./GenericPost";

const doCreateBid = (data, access_token, callback) => {
  return genericPost("/api/new_bid", data, access_token, callback);
};

export default doCreateBid;

const doAcceptBid = (bidId, access_token, callback) => {
  const url = "/api/accept_bid?id=" + bidId;
  return genericPost(url, {}, access_token, callback);
};

export { doAcceptBid };
