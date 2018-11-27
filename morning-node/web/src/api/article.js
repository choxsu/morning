import Axios from 'axios';

Axios.defaults.baseURL = 'http://127.0.0.1:3333';

// 为了让服务端渲染正确请求数据
if (typeof window === 'undefined') {
  Axios.defaults.baseURL = 'http://127.0.0.1:3333';
}
export default {
  getAllPublishArticles(tagId = '', category = '', pageNumber = 1, pageSize = 10) {
    return Axios.get(`/api/index/v1/list?tagId=${tagId}&category=${category}&pageNumber=${pageNumber}&pageSize=${pageSize}`);
  },
  getArticle(id) {
    return Axios.get('/api/blog/v1/detail/' + id);
  },
};
