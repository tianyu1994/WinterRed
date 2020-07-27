<template>
  <div class='addRumorDialog'>
    <el-dialog title="曝光谣言" :visible.sync="addRumorDialogVisible" width="50%" :before-close="handleClose">
      <el-form ref='rumorForm' :model="rumorForm" :rules="formRule">
        <el-form-item label="标题" prop="title">
          <el-input v-model="rumorForm.title" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="abstractInfo">
          <el-input type="textarea" v-model="rumorForm.abstractInfo" placeholder="请输入内容" rows=5></el-input>
        </el-form-item>
        <el-form-item label="所属领域" prop="professionalFieldId">
          <el-select v-model="rumorForm.professionalFieldId">
            <el-option
            v-for="item in professionalList"
            :key="item.id"
            :label="item.fieldName"
            :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="handleClose">取 消</el-button>
          <el-button type="primary" @click="handleSave">发 布</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'addRumorDialog',
  props: {
    addRumorDialogVisible: {
      type: Boolean,
      default: false
    },
    professionalList: {
      type: Array,
      default: function() {
        return []
      }
    }
  },
  data() {
    return {
      rumorForm: {
        title: '',
        abstractInfo: '',
        professionalFieldId: ''
      },
      formRule: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' },
          { max: 300, message: '不能超过300 个字符', trigger: 'blur' }
        ],
        abstractInfo: [
          { required: true, message: '请输入内容', trigger: 'change' },
          { max: 10000, message: '不能超过10000 个字符', trigger: 'blur' }
        ],
        professionalFieldId: [
          { required: true, message: '请选择领域', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    handleClose() {
      this.$refs.rumorForm.resetFields()
      this.$emit('handle-cancel-dialog')
    },
    // 新增谣言
    handleSave() {
      this.$refs.rumorForm.validate(valid => {
        if (valid) {
          this.$emit('handle-save-dialog', this.rumorForm)
          this.$refs.rumorForm.resetFields()
        }
      })
    }
  }
}
</script>
