import request from '@/utils/http.js' // axios的封�后续添加axios二次封装

export function getEmailCode(params) {
  return request({
    url: '/subscribeUser/sendEmail',
    method: 'get',
    params: params
  })
}

// post方式传参用data
export function subscribe(data) {
  return request({
    url: '/subscribeUser/checkIdentifyingCode',
    method: 'post',
    data: data
  })
}

export function queryAllPerssional(params) {
  return request({
    url: '/professionalField/queryAll',
    method: 'get',
    params: params
  })
}

// post方式传参用data
export function getCommonList(query) {
  return request({
    url: '/algorithm/getAlgorithmModelPage',
    method: 'get',
    data: query
  })
}

export function getPerssionalField() {
  return request({
    url: '/professionalField/getAllFieldName',
    method: 'get'
  })
}

export function queryRumor(params) {
  return request({
    url: '/rumorInfo/queryAll',
    method: 'get',
    params: params
  })
}
