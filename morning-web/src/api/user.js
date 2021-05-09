import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/morning-web/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/morning-web/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/morning-web/user/logout',
    method: 'post'
  })
}
