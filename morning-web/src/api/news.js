import request from '@/utils/request'

export function getPostList(params) {
  return request({
    url: '/api/article/listByPage',
    method: 'get',
    params
  })
}


export function getCategoryList(params) {
  return request({
    url: '/morning-web/post/list',
    method: 'get',
    params
  })
}
