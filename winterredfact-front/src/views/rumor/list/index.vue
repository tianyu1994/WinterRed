<template>
  <div>
    <div class="msgBox">
      <el-card class="box-card" shadow="always">
        <div slot="header" style="font-size:16px;">
          <span>实时数据</span>
        </div>
        <div v-for="msg in newMsg" :key="msg.id">
          <el-row style='line-height:2;'>
            <el-col :span='18' align="left">
              <span style='font-size:14px; margin-right:15px;' >
                {{msg.title.substr(0, 20) + '..'}}
              </span>
            </el-col>
            <el-col :span='6' align="right">
              <el-tag type='primary'>
                {{msg.source}}
              </el-tag>
            </el-col>
          </el-row>
          <el-divider></el-divider>
        </div>
      </el-card>
    </div>
    <div style='margin:15px 15px 45px 450px; padding:15px;' class='page'>
      <div style='position:relative; margin-bottom:20px;font-size:16px;'>
        <!--首页顶端-->
        <div>
          <img class='background' width='100%' height='100%' src='@/assets/bg.jpg'/>
        </div>
        <div class='absolute-aligned-right'>
          <el-row style='margin-right:15px;'>
            <el-col :span='12'><subscribe/></el-col>
            <el-col :span='12'><checkerRegist/></el-col>
          </el-row>
        </div>
        <div class='absolute-aligned'>
          <el-col :span='18'>
            <el-input placeholder='请输入内容' size='large' v-model="keyWord">
              <el-button slot='append' style='color:#fff; background:#EB6368;' @click='queryRumor'>
                搜索<i class='el-icon-search el-icon--right'></i>
              </el-button>
            </el-input>
          </el-col>
            <el-col :span='6'>
              <el-button type="danger" @click="showAddRumorDiglog" size="large" style='margin-left:15px; background:#EB6368;'>
                我要提问<i class='el-icon-mouse el-icon--right'></i>
              </el-button>
            </el-col>
        </div>
      </div>
      <div>
        <add-rumor-dialog
          :addRumorDialogVisible="addRumorDialogVisible"
          :professionalList="professionalList"
          @handle-cancel-dialog="cancelAddRumorDiglog">
        </add-rumor-dialog>
      </div>
      <div v-for='(item, index) in tableData' :key='index'>
        <el-row style='line-height:2;'>
          <el-col :span='22'>
            <div>
              <el-link target="_blank" style='font-size:24px; margin-right:15px;' @click="handleClickRumor(item.id)">
                {{item.title}}
              </el-link>
              <el-tag :type='item.status === "真" ? "success" : (item.status === "存疑" ? "warning" : "danger")'>
                {{item.status}}
              </el-tag>
            </div>
            <div style='color:#808080;'>{{dateFormat(item.updateOn)}}</div>
          </el-col>
          <el-col :span='2'>
            <img :src='item.status === "真" ? trueImg : (item.status === "存疑" ? doubtImg : falseImg)'
              style='width:80px; height:80px; transform:rotate(15deg); vertical-align:middle; opacity:0.9;'/>
          </el-col>
        </el-row>
        <el-divider></el-divider>
      </div>
    </div>
  </div>
</template>

<script>
import subscribe from './component/subscribe'
import checkerRegist from './component/checkerRegist'
import addRumorDialog from './component/addRumorDialog.vue'
import { getPerssionalField, queryRumor } from '@/api/api.js'

export default {
  name: 'List',
  components: {
    subscribe,
    checkerRegist,
    addRumorDialog
  },
  created() {
    this.getprofessionalList()
    this.timer = setInterval(this.getNewMsg, 6000)
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  data() {
    return {
      input: '',
      words: '',
      keyWord: '',
      falseImg: require('@/assets/100false.png'),
      doubtImg: require('@/assets/doubt.png'),
      trueImg: require('@/assets/true.png'),
      tableData: [],
      addRumorDialogVisible: false,
      professionalList: [],
      newMsg: [
        {
          id: 1,
          title: '2020年10月31日新疆新增新三例冠病毒案例',
          source: '用户提问'
        },
        {
          id: 2,
          title: '火星人玩转地球预演',
          source: '火星研究所'
        },
        {
          id: 3,
          title: '超人大战哥斯拉',
          source: '地球防卫部'
        }
      ]
    }
  },
  methods: {
    handleClickRumor(rumorId) {
      this.$router.push({
        path: '/rumor/detail',
        query: {
          rumorId: rumorId
        }
      })
    },
    queryRumor() {
      const queryParam = {}
      if (this.keyWord !== null && this.keyWord !== '') {
        queryParam.keyWord = this.keyWord
      }
      queryRumor(queryParam).then((res) => {
        if (res.status !== 'success') {
          this.$message.error(res.msg)
        } else {
          this.tableData = res.results
        }
      }).catch((err) => {
        this.$message.error(err)
      })
    },
    checkerRegist() {
      this.$router.push('/rumor/checkerRegist')
    },
    handleClick() {
      this.$router.push({
        path: '/rumor/detail'
      })
    },
    // 打开新增谣言页面
    showAddRumorDiglog() {
      this.addRumorDialogVisible = true
    },
    // 取消
    cancelAddRumorDiglog() {
      this.addRumorDialogVisible = false
    },
    // 获取领域列表
    getprofessionalList() {
      getPerssionalField().then(res => {
        if (res.status === 'success') {
          this.professionalList = res.results
        }
      })
    },
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
    getNewMsg() {
      if (this.newMsg.length > 15) {
        this.newMsg.pop()
      }
      this.newMsg.unshift(
        {
          id: 5,
          title: '2020年10月31日新疆新增新三例冠病毒案例',
          source: '用户提问'
        }
      )
    }
  },
  mounted() {
    this.queryRumor()
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
.msgBox {
  overflow: hidden;
  z-index: 9999;
  position: fixed;
  margin: 15px;
  text-align:center;
  width: 400px;
  font-size: 14px;
  height: '800px';
}
.el-card {
    border: 4px solid #673ab72e;
    border-radius: 10px
}
</style>
