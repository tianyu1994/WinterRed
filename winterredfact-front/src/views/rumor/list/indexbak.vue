<template>
  <div>
    <span>辟谣首页</span>
    <button @click="handleClick">跳转</button>
    <add-rumor-dialog
      :addRumorDialogVisible="addRumorDialogVisible"
      :professionalList="professionalList"
      @handle-cancel-dialog="cancelAddRumorDiglog"
      @handle-save-dialog="saveAddRumorDiglog">
    </add-rumor-dialog>
    <el-button type="primary" icon="el-icon-search" @click="showAddRumorDiglog">新增</el-button>
  </div>
</template>

<script>
import addRumorDialog from './addRumorDialog.vue'
import { getPerssionalField } from '@/api/api.js'

export default {
  name: 'List',
  components: {
    addRumorDialog
  },
  data() {
    return {
      addRumorDialogVisible: false,
      professionalList: [
        {
          id: 1,
          fieldName: '计算机科学'
        },
        {
          id: 2,
          fieldName: '医学'
        }
      ]
    }
  },
  methods: {
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
      const lis = getPerssionalField()
      console.log(lis)
    },
    // 获取领域列表
    getprofessionalList() {
      const lis = getPerssionalField()
      console.log(lis)
    }
  }
}
</script>

<style>
</style>