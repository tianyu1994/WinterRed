import request from '@/utils/http.js' // axios的封�后续添加axios二次封装

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
