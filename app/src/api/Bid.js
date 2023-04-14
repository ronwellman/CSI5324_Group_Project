import genericPost from "./GenericPost";

const doCreateBid = (data, access_token, callback) => {
  return genericPost("/api/new_bid", data, access_token, callback);
};

export default doCreateBid;
