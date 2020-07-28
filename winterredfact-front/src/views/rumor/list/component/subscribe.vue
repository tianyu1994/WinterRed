<template>
  <div>
    <el-button type='success' @click='subscribeDialog = true' plain>
      订阅<i class="el-icon-star-off el-icon--right"></i>
    </el-button>

    <el-drawer :with-header="false" :visible.sync='subscribeDialog' direction='rtl' ref='drawer'>
      <div style='padding:20px;'>
        <p>订阅我，消息快人一步！</p><br/>
        <el-form :model='subscribeForm' :rules='subscribeRules' ref='subscribeForm'>
          <el-form-item label='邮箱' :label-width='formLabelWidth' prop='email'>
            <el-input v-model='subscribeForm.email' autocomplete='off'>
              <el-button slot="append" :disabled="disabled" @click="sendCode">{{btntxt}}</el-button>
            </el-input>
          </el-form-item>
          <el-form-item label='验证码' :label-width='formLabelWidth' prop='identifyingCode'>
            <el-input v-model='subscribeForm.identifyingCode' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label='订阅领域' :label-width='formLabelWidth' prop='professionalFieldIdList'>
            <el-checkbox-group v-model="subscribeForm.professionalFieldIdList">
              <el-checkbox v-for="field in fieldList" :label="field.id" :key="field.id">{{field.fieldName}}</el-checkbox>
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
import { getEmailCode, subscribe, queryAllPerssional } from '@/api/api.js'

export default {
  data() {
    return {
      disabled: false,
      time: 0,
      btntxt: '获取验证码',
      subscribeDialog: false,
      subscribeForm: {
        email: '',
        identifyingCode: '',
        professionalFieldIdList: []
      },
      formLabelWidth: '80px',
      fieldList: [],
      subscribeRules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'change' }
        ],
        identifyingCode: [
          { required: true, message: '请输入验证码', trigger: 'change' }
        ],
        professionalFieldIdList: [
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
          subscribe(this.subscribeForm).then(() => {
            this.$message({
              message: '订阅成功',
              type: 'success'
            })
          }).catch((err) => {
            this.$message.error(err)
          })
        }
      })
    },
    sendCode() {
      if (this.subscribeForm.email === '') {
        this.$message.error('请先输入邮箱')
      } else {
        const queryParam = {
          email: this.subscribeForm.email
        }
        getEmailCode(queryParam).then((res) => {
          this.$message({
            message: res.msg,
            type: 'success'
          })
        }).catch((err) => {
          this.$message.error(err)
        })
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
  },
  mounted() {
    queryAllPerssional().then((res) => {
      this.fieldList = res.results
    }).catch((err) => {
      this.$message.error(err)
    })
  }
}
</script>

<style scoped>
</style>
