<template>
  <div style='margin:15px 300px; padding:15px;' class='page'>
    <div style='position:relative; margin-bottom:20px;font-size:16px;'>
      <!--首页顶端-->
      <div>
        <img class='background' width='100%' height='100%' src='@/assets/bg.jpg'/>
      </div>
      <div class='absolute-aligned-right'>
        <el-row style='margin-right:15px;'>
          <el-col :span='12'><subscribe/></el-col>
          <el-col :span='12'>
            <el-button type='primary' @click='checkerRegist' plain>
              专家注册<i class='el-icon-user el-icon--right'></i>
            </el-button>
          </el-col>
        </el-row>
      </div>
      <div class='absolute-aligned'>
        <el-col :span='18'>
          <el-input placeholder='请输入内� size='large'>
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
        @handle-cancel-dialog="cancelAddRumorDiglog"
        @handle-save-dialog="saveAddRumorDiglog">
      </add-rumor-dialog>
    </div>
    <div v-for='(item, index) in tableData' :key='index'>
      <el-row style='line-height:2;'>
        <el-col :span='22'>
          <div>
            <el-link target="_blank" style='font-size:24px; margin-right:15px;' @click="handleClickRumor(item.id)">
              {{item.title}}
            </el-link>
            <el-tag :type='item.status === "待核� ? "info" : (item.status === "核查通过" ? "success" : "danger")'>
              {{item.status}}
            </el-tag>
          </div>
          <div style='color:#808080;'>{{dateFormat(item.updateOn)}}</div>
        </el-col>
        <el-col :span='2'>
          <img :src='img' style='width:80px; height:80px; transform:rotate(15deg); vertical-align: middle;'/>
        </el-col>
      </el-row>
      <el-divider></el-divider>
    </div>
  </div>
</template>

<script>
import subscribe from './component/subscribe'
import addRumorDialog from './component/addRumorDialog.vue'
import { getPerssionalField, queryRumor } from '@/api/api.js'

export default {
  name: 'List',
  components: {
    subscribe,
    addRumorDialog
  },
  created() {
    this.getprofessionalList()
  },
  data() {
    return {
      input: '',
      words: '',
      img: require('@/assets/100false.png'),
      tableData: []
      ],
      addRumorDialogVisible: false,
      professionalList: [
        {
          id: 1,
          fieldName: '计算机科�
        },
        {
          id: 2,
          fieldName: '医学'
        }
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
      queryRumor().then((res) => {
        this.tableData = res.results.list
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
    // 保存谣言
    saveAddRumorDiglog(rumorFormData) {
      this.addRumorDialogVisible = false
      console.log(rumorFormData)
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
      /* 在日期格式中，月份是�开始的，因此要�
      * 使用三元表达式在小于10的前面加0，以达到格式统一  �09:11:05
      * */
      var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      // 拼接
      return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
    },
    // 保存谣言
    saveAddRumorDiglog(rumorFormData) {
      this.addRumorDialogVisible = false
      console.log(rumorFormData)
    },
    // 获取领域列表
    getprofessionalList() {
      getPerssionalField().then(res => {
        if (res.status === 'success') {
          this.professionalList = res.results
        }
      })
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
</style>
