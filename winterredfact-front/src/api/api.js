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
    method: 'post',
    data: query
  })
}

export function getPerssionalField() {
  return request({
    url: '/professionalField/queryAll',
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

export function saveOrUpdate(params) {
  return request({
    url: '/rumorInfo/saveOrUpdate',
    method: 'post',
    data: params
  })
}

export function saveOrUpdateCheckMan(params) {
  return request({
    url: '/checkMan/saveOrUpdate',
    method: 'post',
    data: params
  })
}

export function queryRumorById(params) {
  return request({
    url: '/rumorInfo/queryById',
    method: 'get',
    params: params
  })
}

export function saveOrUpdateCheck(params) {
  return request({
    url: '/rumorInfo/saveOrUpdateCheck',
    method: 'post',
    data: params
  })
}

export function checkPlatQueryAll(params) {
  return request({
    url: '/checkPlat/queryAll',
    method: 'get',
    params: params
  })
}

export function getAskUserIdByEmail(params) {
  return request({
    url: '/askUser/getAskUserIdByEmail',
    method: 'get',
    params: params
  })
}

export function queryCurrentHotKeywords(params) {
  return request({
    url: '/currentHotKeywords/queryAll',
    method: 'get',
    params: params
  })
}

export function queryRolledRumorInfoById(params) {
  return request({
    url: '/rumorInfo/queryRolledRumorInfoById',
    method: 'get',
    params: params
  })
}
