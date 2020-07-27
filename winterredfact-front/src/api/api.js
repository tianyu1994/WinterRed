import request from '@/utils/http.js' // axios的封装 后续添加axios二次封装

//  /cosmopaas-dev为跨域规则 前面文章有提到
// post方式传参用data
export function getCommonList(query) {
  return request({
    url: '/cosmopaas-dev/algorithm/getAlgorithmModelPage',
    method: 'get',
    data: query
  })
}
export function getPerssionalField() {
  return request({
    url: '/cosmopaas-dev/algorithm/getAlgorithmModelPage',
    method: 'get'
  })
}
