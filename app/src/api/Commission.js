import genericPost from "./GenericPost";

const doCreateCommission = (data, access_token, callback) => {
  return genericPost("/api/new_commission", data, access_token, callback);
};

export default doCreateCommission;
