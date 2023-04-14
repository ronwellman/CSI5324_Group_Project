import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/";
axios.defaults.headers.common["Content-Type"] = "application/json";
axios.defaults.headers.common["Access-Control-Allow-Origin"] =
  "http://localhost:3000";

const api = axios.create();

// const new_user = axios.create({
// 	baseURL: "/api/new_user"
// });

// const new_post = axios.create({
// 	baseURL: "/api/new_post"
// });

// const active_posts = axios.create({
// 	baseURL: "/api/active_posts"
// });

// const posts_by_user = axios.create({
// 	baseURL: "/api/posts_by_user"
// });

// const disable_post = axios.create({
// 	baseURL: "/api/disable_post"
// });

// const enable_post = axios.create({
// 	baseURL: "/api/enable_post"
// });

// const delete_post = axios.create({
// 	baseURL: "/api/delete_post"
// });

// const new_commission = axios.create({
// 	baseURL: "/api/new_commission"
// });

// const commission = axios.create({
// 	baseURL: "/api/commission"
// });

// const open_commissions = axios.create({
// 	baseURL: "/api/open_commissions"
// });

// const delete_commission = axios.create({
// 	baseURL: "/api/delete_commission"
// });

// const commissions_by_user = axios.create({
// 	baseURL: "/api/commissions_by_user"
// });

// const new_bid = axios.create({
// 	baseURL: "/api/new_bid"
// });

// const bid = axios.create({
// 	baseURL: "/api/bid"
// });

// const bids_by_commission = axios.create({
// 	baseURL: "/api/bids_by_commission"
// });

// const bids_by_user = axios.create({
// 	baseURL: "/api/bids_by_user"
// });

// const delete_bid = axios.create({
// 	baseURL: "/api/delete_bid"
// });

// const new_message = axios.create({
// 	baseURL: "/api/new_message"
// });

// const unread_messages = axios.create({
// 	baseURL: "/api/unread_messages"
// });

// const sent_messages = axios.create({
// 	baseURL: "/api/sent_messages"
// });

// const received_messages = axios.create({
// 	baseURL: "/api/received_messages"
// });

// const read_message = axios.create({
// 	baseURL: "/api/read_message"
// });

// const new_user = axios.create({
// 	baseURL: "/api/new_user"
// });

export default api;
