<template>
  <div class='addRumorDialog'>
    <el-dialog title="曝光谣言" :visible.sync="addRumorDialogVisible" width="50%" :before-close="handleClose">
      <el-form ref='rumorForm' :model="rumorForm" :rules="formRule" label-width="80px">
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
        <el-form-item label='邮箱' prop='email'>
          <el-input v-model='rumorForm.email' autocomplete='off'>
            <el-button slot="append" :disabled="disabled" @click="sendCode">{{btntxt}}</el-button>
          </el-input>
        </el-form-item>
        <el-form-item label='验证码' prop='code'>
          <el-input v-model='rumorForm.code' autocomplete='off'></el-input>
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
      disabled: false,
      time: 0,
      btntxt: '获取验证码',
      rumorForm: {
        title: '',
        abstractInfo: '',
        professionalFieldId: '',
        email: '',
        code: '',
        askUserId: '',
        source: '提问人录入'
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
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
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
    },
    sendCode() {
      if (this.rumorForm.email === '') {
        this.$message.error('请先输入邮箱')
      } else {
        this.time = 60
        this.disabled = true
        this.timer()
      }
    },
    timer() {
      if (this.time > 0) {
        this.time--
        this.btntxt = this.time + 's后重新获取'
        setTimeout(this.timer, 1000)
      } else {
        this.time = 0
        this.disabled = false
        this.btntxt = '获取验证码'
      }
    }
  }
}
</script>
