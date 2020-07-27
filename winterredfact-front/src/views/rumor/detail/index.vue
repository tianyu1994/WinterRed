<template>
  <div style="margin:15px 350px; padding:15px;" class="page">
    <div style="position:sticky; top:30px;">
      <el-button @click="handleReturnHome" icon="el-icon-s-home">返回首页</el-button>
    </div>
    <div>
      <el-row>
        <el-col :span="22"><h1>vue滚动固定顶部及修改样式</h1></el-col>
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
    <div style="font-size: 24px; line-height:1.7">
      <div>
        <span class="common_title">鉴定结果：</span>
        <img src="@/assets/tmp/假谣言.png" style="width:65px; height:26px; vertical-align: middle;">
      </div>
      <div>
        <span class="common_title">查证要点：</span>
      </div>
      <br/>
      <div>
        <span class="common_title">查证者：</span>
        <span>解放日报•上观新闻运营的辟谣新闻和辟谣服务网络平台</span>
      </div>
    </div>
    <el-divider></el-divider>
    <div>
      <span>更新时间：2020-07-23</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Detail',
  methods: {
    handleReturnHome() {
      this.$router.push({
        path: '/rumor/list'
      })
    }
  }
}
</script>

<style scoped>
  .page {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .title_mark {
    width: 90px;
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
    vertical-align: middle;
  }
</style>
