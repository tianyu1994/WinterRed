<template>
  <div>
    <el-button type='text' @click='subscribeDialog = true'>我要订阅</el-button>

    <el-drawer title='订阅我，消息快人一步！' :visible.sync='subscribeDialog' direction='rtl' ref='drawer'>
      <div style='padding:20px;'>
        <el-form :model='subscribeForm' :rules='subscribeRules' ref='subscribeForm'>
          <el-form-item label='邮箱' :label-width='formLabelWidth' prop='email'>
            <el-input v-model='subscribeForm.email' autocomplete='off'></el-input>
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
      subscribeDialog: false,
      subscribeForm: {
        email: '',
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
          alert('submit!')
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
