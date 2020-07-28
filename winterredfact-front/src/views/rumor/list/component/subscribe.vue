<template>
  <div>
    <el-button type='success' @click='subscribeDialog = true' plain>
      订阅<i class="el-icon-star-off el-icon--right"></i>
    </el-button>

    <el-drawer title='订阅我，消息快人一步！' :visible.sync='subscribeDialog' direction='rtl' ref='drawer'>
      <div style='padding:20px;'>
        <el-form :model='subscribeForm' :rules='subscribeRules' ref='subscribeForm'>
          <el-form-item label='邮箱' :label-width='formLabelWidth' prop='email'>
            <el-input v-model='subscribeForm.email' autocomplete='off'>
              <el-button slot="append" :disabled="disabled" @click="sendCode">{{btntxt}}</el-button>
            </el-input>
          </el-form-item>
          <el-form-item label='验证码' :label-width='formLabelWidth' prop='code'>
            <el-input v-model='subscribeForm.code' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label='订阅领域' :label-width='formLabelWidth' prop='field'>
            <el-checkbox-group v-model="subscribeForm.field">
              <el-checkbox v-for="field in fieldList" :label="field.id" :key="field.id">{{field.name}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
        <div>
          <el-button @click='cancelForm'>取 消</el-button>
          <el-button @click='handleSubscribe' type='primary'>订 阅</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      disabled: false,
      time: 0,
      btntxt: '获取验证码',
      subscribeDialog: false,
      subscribeForm: {
        email: '',
        code: '',
        field: []
      },
      formLabelWidth: '80px',
      fieldList: [
        {
          id: 1,
          name: '医学前沿'
        },
        {
          id: 2,
          name: '防疫'
        },
        {
          id: 3,
          name: '5G技术'
        }
      ],
      subscribeRules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'change' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'change' }
        ],
        field: [
          { type: 'array', required: true, message: '请至少选择一个领域', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    cancelForm() {
      this.subscribeDialog = false
    },
    handleSubscribe() {
      this.$refs.subscribeForm.validate((valid) => {
        if (valid) {
          console.log(this.subscribeForm)
        }
      })
    },
    sendCode() {
      var regEmail = /^[A-Za-z1-9]+([-_.][A-Za-z1-9]+)*@([A-Za-z1-9]+[-.])+[A-Za-z]{2,5}$/
      if (this.subscribeForm.email === '') {
        this.$message.error('请先输入邮箱')
      } else if (!regEmail.test(this.subscribeForm.email)) {
        this.$message.error('请输入正确的邮箱')
      } else {
        console.log('发送验证吗')

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

<style scoped>
</style>
