import Axios from 'axios';

Axios.defaults.baseURL = 'http://127.0.0.1:3333';

// 为了让服务端渲染正确请求数据
if (typeof window === 'undefined') {
  Axios.defaults.baseURL = 'http://127.0.0.1:3333';
}
export default {
  getAllPublishArticles(pageNumber = 1, pageSize = 10) {
    return Axios.get(`/api/article/listByPage?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  },
  getArticle(id) {
    return Axios.get('/api/article/detail/' + id);
  },
};
