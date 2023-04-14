import genericPost from "./GenericPost";
import genericGet from "./GenericGet";

const doCreateCommission = (data, access_token, callback) => {
  return genericPost("/api/new_commission", data, access_token, callback);
};

export default doCreateCommission;

const doGetCommissions = (access_token, callback) => {
  return genericGet("/api/open_commissions", access_token, callback);
};

export { doGetCommissions };

const doGetMyCommissions = (userId, access_token, callback) => {
  const url = "/api/commissions_by_user?id=" + userId;
  return genericGet(url, access_token, callback);
};

export { doGetMyCommissions };
