<template>
  <div style="margin:15px 300px; padding:15px;" class="page">
    <div style="position:sticky; top:30px;">
      <el-button @click="handleReturnHome" icon="el-icon-s-home">返回首页</el-button>
    </div>
    <div>
      <el-row>
        <el-col :span="22"><h1>“钟南山来新疆了”？网友调侃：已通过朋友圈抵达乌鲁木齐</h1></el-col>
        <el-col :span="2"><img src="@/assets/tmp/false.png" class="title_mark"></el-col>
      </el-row>
    </div>
    <div>
      <p style="line-height:1.7">
        <span class="subtitle">流传说法：</span>
        <span class="subtitle-rumor">近日，一段“钟南山来新疆了”的短视频在社交媒体广泛传播，#钟南山乌鲁木齐##钟南山新疆#的话题阅读量飙升。</span>
      </p>
    </div>
    <el-divider></el-divider>
    <div style="font-size: 24px; line-height:1.7; vertical-align:middle;">
      <div>
        <span class="common_title">鉴定结果：</span>
        <img src="@/assets/tmp/假谣言.png" style="width:65px; height:26px; vertical-align: middle;">
      </div><br/>
      <div>
        <span class="common_title">查证要点：</span>
        <div v-for="(point, index) in pointList" :key='index'>
          <span class="common_title">{{(index + 1) + ' - '}}</span>
          {{point}}
          <br/><br/>
        </div>
      </div>
      <div>
        <span class="common_title">查证者：</span>
        <span>解放日报•上观新闻运营的辟谣新闻和辟谣服务网络平台</span>
      </div>
    </div>
    <div>
      <span style="line-height:3.5;">更新时间：2020-07-23</span>
    </div>
    <el-divider></el-divider>
    <div style="font-size: 24px; line-height:1.5; vertical-align:middle;">
      <span>相关资料：</span>
      <p class="subtitle">
        据四川媒体报道，一家以“吸氢气”为卖点的生活体验馆涉嫌虚假宣传。相关视频显示，该店店员宣称，通过他们的仪器制备氢气并吸入包治百病，甚至对治新冠肺炎都有效，该店还用钟南山头像做宣传。目前市场监管部门正对该店进行核查取证，店铺暂停营业。
      </p>
    </div>
  </div>
</template>

<script>
import { queryRumor } from '@/api/api.js'

export default {
  name: 'Detail',
  data() {
    return {
      pointList: [
        '喀什网警巡查执法官方账号辟谣称：钟南山并没有来新疆，请大家不要放大疫情。请大家不要转发非官方信息。阿勒泰新闻网也在网络上辟谣：7月20日钟南山飞抵乌鲁木齐抗击疫情是谣言。',
        '网传视频中有一个明显的麦当劳标志，但乌鲁木齐市并没有麦当劳门店，由此可以判断网传视频并非拍自新疆。'
      ]
    }
  },
  methods: {
    queryRumorDetail() {
      const queryParam = {
        id: this.$route.query.rumorId
      }
      queryRumor(queryParam).then((res) => {
        console.log(res.results.list[0].title)
      }).catch((err) => {
        this.$message.error(err)
      })
    },
    handleReturnHome() {
      this.$router.push({
        path: '/rumor/list'
      })
    }
  },
  mounted() {
    this.queryRumorDetail()
  }
}
</script>

<style scoped>
  .page {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .title_mark {
    width: 85px;
    height: 100px;
    z-index: -1;
    opacity: 0.8;
  }
  .subtitle {
    font-size: 24px;
    color: #808080;
  }
  .subtitle-rumor {
    font-size: 24px;
  }
  .common_title {
    font-weight: bold;
  }
</style>
