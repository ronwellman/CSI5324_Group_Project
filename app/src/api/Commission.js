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
