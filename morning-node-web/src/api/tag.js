import Axios from 'axios';
// 为了让服务端渲染正确请求数据
if (typeof window === 'undefined') {
  Axios.defaults.baseURL = 'http://127.0.0.1:3333';
}
export default {
  getAllTags() {
    return Axios.get('/api/blog/tag/v1/list');
  }
};
