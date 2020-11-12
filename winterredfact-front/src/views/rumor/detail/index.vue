<template>
  <div style="margin:15px 300px; padding:15px;" class="page">
    <div style="position:sticky; top:30px;">
      <el-button @click="handleReturnHome" icon="el-icon-s-home">返回首页</el-button>
    </div>
    <div>
      <el-row>
        <el-col :span="22"><h1>{{rumorInfo.title}}</h1></el-col>
        <el-col :span="2">
          <img :src='rumorInfo.status === "真" ? trueImg : (rumorInfo.status === "存疑" ? doubtImg : falseImg)' class="title_mark">
        </el-col>
      </el-row>
    </div>
    <div>
      <p style="line-height:1.7">
        <span class="subtitle">流传说法：</span>
        <span class="subtitle-rumor">{{rumorInfo.abstractInfo}}</span>
      </p>
    </div>
    <el-divider></el-divider>
    <div style="font-size: 24px; line-height:1.7; vertical-align:middle;">
      <div>
        <span class="common_title">鉴定结果：</span>
        <img :src='rumorInfo.status === "真" ? trueSmallImg : (rumorInfo.status === "存疑" ? doubtSmallImg : falseSmallImg)' style="height:26px; vertical-align: middle;">
      </div><br/>
      <div>
        <span class="common_title">查证要点：</span>
        <div v-for="(point, index) in rumorInfo.checkPoints" :key='index'>
          <span class="common_title">{{(index + 1) + ' - '}}</span>
          {{point}}
          <br/><br/>
        </div>
      </div>
      <div>
        <span class="common_title">查证者：</span>
        <span>{{(rumorInfo.organizationName ? rumorInfo.organizationName : '') + ' • ' + (rumorInfo.checkManName ? rumorInfo.checkManName : '')}}</span>
      </div>
    </div>
    <div>
      <span style="line-height:3.5;">更新时间：{{dateFormat(rumorInfo.updateOn)}}</span>
    </div>
    <el-divider></el-divider>
    <div style="font-size: 24px; line-height:1.5; vertical-align:middle;">
      <span>相关资料：</span>
      <p class="subtitle">{{rumorInfo.quotedContent}}</p>
    </div>
  </div>
</template>

<script>
import { queryRumor } from '@/api/api.js'

export default {
  name: 'Detail',
  data() {
    return {
      falseImg: require('@/assets/100false.png'),
      doubtImg: require('@/assets/doubt.png'),
      trueImg: require('@/assets/true.png'),
      falseSmallImg: require('@/assets/tmp/假谣言.png'),
      doubtSmallImg: require('@/assets/tmp/争议.png'),
      trueSmallImg: require('@/assets/tmp/真的.png'),
      rumorInfo: {}
    }
  },
  methods: {
    dateFormat(time) {
      var date = new Date(time)
      var year = date.getFullYear()
      var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
    },
    queryRumorDetail() {
      const queryParam = {
        id: this.$route.query.rumorId
      }
      queryRumor(queryParam).then((res) => {
        if (res.status !== 'success') {
          this.$message.error(res.msg)
        } else {
          this.rumorInfo = res.results[0]
        }
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
    width: 100px;
    height: 100px;
    z-index: -1;
    opacity: 0.8;
    transform:rotate(15deg);
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
