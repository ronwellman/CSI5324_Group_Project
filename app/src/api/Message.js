import genericPost from "./GenericPost";

const doCreateMessage = (data, access_token, callback) => {
  return genericPost("/api/new_message", data, access_token, callback);
};

export default doCreateMessage;
