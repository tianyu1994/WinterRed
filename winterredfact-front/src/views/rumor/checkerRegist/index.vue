<template>
  <div style="margin:15px 300px; padding:15px; align-content: center">
    <el-tag type="info" style="font-size: 25px">注册信息</el-tag>
    <div style="height: 40px;"></div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.name"
                  :rules="[
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                   ]">
        </el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.sex" >
          <el-option label="男" value="male"></el-option>
          <el-option label="女" value="female"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱地址">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="form.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="form.pass" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="职业领域" prop="areaOptions">
        <el-select v-model="form.area" placeholder="请选择职业领域">
          <el-option v-for="item in areaOptions" :key="item.value" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="机构组织" prop="areaOptions">
        <el-select v-model="form.orgn" placeholder="请选择所属机构组织">
          <el-option v-for="item in orgOptions" :key="item.value" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="float: right; margin-right: 50px">
        <el-button type="primary" @click="onSubmit">立即注册</el-button>
        <el-button @click="quit">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'CheckerRegist',
  data() {
    return {
      note: '',
      areaOptions: [],
      orgOptions: [],
      form: {
        name: '',
        area: '',
        sex: '',
        email: '',
        pass: '',
        orgn: ''
      },
      rules: {
        name: [
          { required: true, message: '用户名不能为空' }
        ],
        area: [
          { required: true, message: '职业领域不能为空' }
        ],
        email: [
          { required: true, message: '邮箱地址不能为空' }
        ],
        pass: [
          { required: true, message: '密码不能为空' }
        ],
        orgn: [
          { required: true, message: '所属机构不能为空' }
        ]
      }
    }
  },

  mounted() {
    this.getOptions()
  },
  methods: {
    onSubmit() {
      console.log('submit!')
    },
    quit() {
      this.$router.push('/rumor/list')
    }
  }
}
</script>
