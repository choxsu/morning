import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/morning-web/table/list',
    method: 'get',
    params
  })
}
