import request from '@/utils/request'

export function getArticleList(params) {
  return request({
    url: '/api/article/listByPage',
    method: 'get',
    params
  })
}

export function createArticle(data) {
  return request({
    url: '/api/article/save',
    method: 'put',
    data: data
  })
}


export function getCategoryList(params) {
  return request({
    url: '/morning-web/post/list',
    method: 'get',
    params
  })
}
