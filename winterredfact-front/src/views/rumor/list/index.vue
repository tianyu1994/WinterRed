<template>
  <div style='margin:15px 300px; padding:15px;' class='page'>
    <div style='position:relative; margin-bottom:20px;'>
      <!--首页顶端-->
      <div>
        <img class='background' width='100%' height="100%" src='@/assets/bg.jpg'/>
      </div>
      <div class="absolute-aligned-right">
        <el-row style='margin-right:15px;'>
          <el-col :span="12"><subscribe/></el-col>
          <el-col :span="12">
            <el-button type='primary' @click='checkerRegist' plain>
              专家注册<i class="el-icon-user el-icon--right"></i>
            </el-button>
          </el-col>
        </el-row>
      </div>
      <div class="absolute-aligned">
        <el-col :span="18">
          <el-input placeholder='请输入内容' size="large">
            <el-button slot='append' style="color:#fff; background:#EB6368;">
              搜索<i class="el-icon-search el-icon--right"></i>
            </el-button>
          </el-input>
        </el-col>
          <el-col :span="6">
            <el-button type="danger" size="large" style='margin-left:15px; background:#EB6368;'>
              我要提问<i class="el-icon-mouse el-icon--right"></i>
            </el-button>
          </el-col>
      </div>
    </div>
    <div v-for='(item,index) in tableData' :key='index'>
      <el-row style='line-height:2;'>
        <el-col :span="22">
          <div>
            <span style='font-size:20px; margin-right:15px;'>{{item.info}}</span>
            <el-tag type="success">{{item.result}}</el-tag>
          </div>
          <div style='color:#808080;'>{{item.date}}</div>
        </el-col>
        <el-col :span="2">
          <img :src='img' style='width:80px; height:80px; transform:rotate(15deg); vertical-align: middle;'/>
        </el-col>
      </el-row>
      <el-divider></el-divider>
    </div>
  </div>
</template>

<script>
import subscribe from './component/subscribe'

export default {
  name: 'List',
  components: {
    subscribe
  },
  data() {
    return {
      input: '',
      words: '',
      img: require('@/assets/100false.png'),
      tableData: [
        {
          date: '2016-05-03',
          result: '谣言',
          info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
        },
        {
          date: '2016-05-02',
          result: '确实如此',
          info: '西班牙从去年3月的废水检出新冠病毒相关研究漏洞大'
        },
        {
          date: '2016-05-04',
          result: '谣言',
          info: '因疫情原因，中国驻纳米比亚使馆组织包机回国'
        },
        {
          date: '2016-05-01',
          result: '谣言',
          info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
        },
        {
          date: '2016-05-08',
          result: '谣言',
          info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
        },
        {
          date: '2016-06-01',
          result: '谣言',
          info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
        },
        {
          date: '2016-06-08',
          result: '谣言',
          info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
        }
      ]
    }
  },
  mounted() {},
  methods: {
    checkerRegist() {
      this.$router.push('/rumor/checkerRegist')
    }
  }
}
</script>

<style lang='scss'>
.background {
  background-size: cover;
  background-position: center;
  top: 0;
  z-index: -1;
  opacity:0.95;
}
.page {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 100%;
  margin: 12px 0;
}
.absolute-aligned {
  width: 50%;
  min-width: 250px;
  height: auto;
  overflow: auto;
  margin: auto;
  position: absolute;
  top: 40%; left: 0;
  bottom: 0; right: 0;
  .el-input-group__append {
    background-color: #EB6368;
    border: 1px solid #f56c6c;
  }
}
.absolute-aligned-right {
  padding: 15px;
  height: auto;
  overflow: auto;
  margin: auto;
  position: absolute;
  top: 0; left: 80;
  bottom: 0; right: 0;
}
</style>
