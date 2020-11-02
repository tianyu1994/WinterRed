<template>
  <div>
    <div class="msgBox">
      <el-card class="box-card" shadow="always">
        <div slot="header" style="font-size:16px;">
          <span>实时同步数据</span>
        </div>
        <div v-for="msg in newMsg" :key="msg.id">
          <el-row style='line-height:2;'>
            <el-col :span='18' align="left">
              <span style='font-size:14px; margin-right:15px;' >
                {{msg.title.substr(0, 15) + '..'}}
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
            <el-col :span='9'>
              <el-button type="danger" @click="showAddRumorDiglog">
                我要提问<i class='el-icon-mouse el-icon--right'></i>
              </el-button>
            </el-col>
            <el-col :span='9'><checkerRegist/></el-col>
            <el-col :span='6'><subscribe/></el-col>
          </el-row>
        </div>
        <div class='absolute-aligned'>
          <el-row>
            <el-col :span='24' align="center">
              <el-autocomplete  :fetch-suggestions="querySuggestSearch" placeholder='请输入内容' size='large' v-model="keyWord" style="width: 450px;">
                <el-button slot='append' style='color:#fff; background:#EB6368;' @click='queryRumor'>
                  搜索<i class='el-icon-search el-icon--right'></i>
                </el-button>
              </el-autocomplete>
            </el-col>
          </el-row>
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
import { getPerssionalField, queryRumor, queryCurrentHotKeywords } from '@/api/api.js'

export default {
  name: 'List',
  components: {
    subscribe,
    checkerRegist,
    addRumorDialog
  },
  created() {
    this.getprofessionalList()
    this.displayNewMsg()
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
      fackNewMsg: [
        {
          id: 'a',
          title: '李兰娟院士提醒北京疫情可能将全面大爆发',
          source: '区块链'
        },
        {
          id: 'b',
          title: '大学生青海失联十余天未立案',
          source: '区块链'
        },
        {
          id: 'c',
          title: '坠湖公交司机女儿投湖自杀',
          source: '区块链'
        },
        {
          id: 'd',
          title: '猪流感真的要来了',
          source: '区块链'
        },
        {
          id: 'e',
          title: '上海北外滩拆迁最高一户赔偿6.8亿',
          source: '区块链'
        },
        {
          id: 'f',
          title: '网传银川百辆消防车支援凉山',
          source: '区块链'
        },
        {
          id: 'g',
          title: '天津取消住房限购',
          source: '区块链'
        },
        {
          id: 'h',
          title: '部分四川户口在重庆购房可减免房产税',
          source: '区块链'
        },
        {
          id: 'i',
          title: '鄄城出现新冠肺炎患者',
          source: '区块链'
        },
        {
          id: 'j',
          title: '济南地铁8号线未批先建',
          source: '区块链'
        },
        {
          id: 'k',
          title: '成都地铁庆祝18号线开通免费送地铁卡',
          source: '区块链'
        },
        {
          id: 'l',
          title: '北京新增四例确诊新冠肺炎病例',
          source: '区块链'
        },
        {
          id: 'm',
          title: '哈同高速公路桥梁坍塌',
          source: '区块链'
        },
        {
          id: 'n',
          title: '湖南平江近两千亩稻田绝收',
          source: '区块链'
        },
        {
          id: 'o',
          title: '心脏病发作或猝死前用力咳嗽就能救命',
          source: '区块链'
        },
        {
          id: 'p',
          title: '四川甘孜道孚猪肉出现问题不能食用',
          source: '区块链'
        },
        {
          id: 'q',
          title: '益阳市赫山区出现新冠肺炎患者',
          source: '区块链'
        },
        {
          id: 'r',
          title: '黑龙江省2020年度公务员省考笔试泄题',
          source: '区块链'
        },
        {
          id: 's',
          title: '江西省普通高校专升本“第二次调剂”',
          source: '区块链'
        },
        {
          id: 't',
          title: '西安市医保卡将终止使用',
          source: '区块链'
        }
      ],
      newMsg: [],
      suggestSearch: []
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
    displayNewMsg() {
      for (let i = 1; i < this.fackNewMsg.length; i++) {
        const random = Math.floor(Math.random() * (i + 1))
        const t = this.fackNewMsg[i]
        this.fackNewMsg[i] = this.fackNewMsg[random]
        this.fackNewMsg[random] = t
      }
      this.newMsg = this.fackNewMsg.slice(0, 16)
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
    },
    querySuggestSearch(queryString, cb) {
      var suggestSearch = this.suggestSearch
      var results = queryString ? suggestSearch.filter(this.createFilter(queryString)) : suggestSearch
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter(queryString) {
      return (suggestSearchTmp) => {
        return (suggestSearchTmp.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    }
  },
  mounted() {
    this.queryRumor()
    queryCurrentHotKeywords().then(res => {
      if (res.results !== null) {
        this.suggestSearch = res.results
      } else {
        this.suggestSearch = []
      }
    })
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
