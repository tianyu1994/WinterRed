<template>
  <div>
    <el-button type='primary' @click='registDialog = true' plain>
      专家注册<i class='el-icon-user el-icon--right'></i>
    </el-button>
    <el-drawer :with-header="false" :visible.sync='registDialog' direction='rtl' ref='drawer' style="font-size: 22px">
      <div style='padding:20px;'>
        <p>专家注册页面</p><br/>
        <el-form :model='form' :rules='rules' ref='form'>
          <el-form-item label="用户名" :label-width='formLabelWidth' prop='user'>
            <el-input v-model="form.user"></el-input>
          </el-form-item>
          <el-form-item label="真实姓名" :label-width='formLabelWidth' prop='name'>
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop='sex' :label-width='formLabelWidth'>
            <el-select v-model="form.sex" >
              <el-option label="男" value="male"></el-option>
              <el-option label="女" value="female"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label='邮箱地址' :label-width='formLabelWidth' prop='email'>
            <el-input v-model='form.email' autocomplete='off'>
            <el-button slot="append" :disabled="disabled" @click="sendCode">{{btntxt}}</el-button>
          </el-input>
          </el-form-item>
          <el-form-item label='验证码' :label-width='formLabelWidth' prop='code'>
            <el-input v-model='form.code' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label="机构组织" prop="orgOptions" :label-width='formLabelWidth'>
            <el-select v-model="form.orgn" placeholder="请选择所属机构组织">
              <el-option v-for="item in orgOptions" :key="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属地区" prop="areaOptions" :label-width='formLabelWidth'>
            <el-select v-model="form.area" placeholder="请选择所属地区">
              <el-option v-for="item in areaOptions" :key="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <div style='padding:20px 50px;'>
        <el-button @click="cancelForm">取消</el-button>
        <el-button type="primary" @click="onSubmit">立即注册</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formLabelWidth: '100px',
      note: '',
      areaOptions: [
        {
          id: 1,
          name: '中国'
        },
        {
          id: 2,
          name: '美国'
        },
        {
          id: 3,
          name: '英国'
        }
      ],
      orgOptions: [
        {
          id: 1,
          name: '较真'
        },
        {
          id: 2,
          name: '是真的吗'
        }
      ],
      disabled: false,
      time: 0,
      btntxt: '获取验证码',
      registDialog: false,
      form: {
        name: '',
        user: '',
        code: '',
        sex: '',
        email: '',
        orgn: '',
        area: ''
      },
      rules: {
        name: [
          { required: true, message: '真实姓名不能为空', trigger: 'change' }
        ],
        user: [
          { required: true, message: '用户名不能为空', trigger: 'change' }
        ],
        area: [
          { required: true, message: '所属地区不能为空', trigger: 'change' }
        ],
        email: [
          { required: true, message: '邮箱地址不能为空', trigger: 'change' }
        ],
        pass: [
          { required: true, message: '密码不能为空', trigger: 'change' }
        ],
        orgn: [
          { required: true, message: '所属机构不能为空', trigger: 'change' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    cancelForm() {
      this.registDialog = false
    },
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          console.log(this.form)
        }
      })
    },
    sendCode() {
      if (this.form.email === '') {
        this.$message.error('请先输入邮箱')
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
