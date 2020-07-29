<template>
  <div class='checkRumor'>
    <div class='title' v-html="rumor.title"></div>
    <div class='content' v-html="rumor.abstractInfo"></div>
    <div class='content' v-html="rumor.professionalFieldName"></div>
    <el-divider></el-divider>
    <el-form ref='checkRumorForm' :model="checkRumorForm" :rules="formRule">
      <el-form-item label="鉴定" prop="status">
        <el-radio-group v-model="rumor.status">
          <el-radio label="假">假</el-radio>
          <el-radio label="真">真</el-radio>
          <el-radio label="存疑">存疑</el-radio>
        </el-radio-group>
      </el-form-item>
      <font style="margin-right:15px;">查证要点</font>
      <el-button @click="addCheckPoint" type="primary" icon="el-icon-circle-plus-outline" circle></el-button>
      <el-form-item
        v-for="(checkPoint, index) in checkRumorForm.checkPoints"
        :label="'要点' + (index + 1)"
        :key="checkPoint.key"
        :prop="'checkPoints.' + index + '.value'"
        :rules="{
          required: true, message: '要点不能为空', trigger: 'blur'
        }"
      >
        <el-input type="textarea" v-model="checkPoint.value" placeholder="请输入内容" rows=2>
        </el-input>
        <el-button type="danger" icon="el-icon-delete" v-if="index>0" style="margin-top:15px;"
          @click.prevent="removeCheckPoint(checkPoint)" circle></el-button>
      </el-form-item>
      <el-form-item label="引用内容" prop="quotedContent">
        <el-input v-model="checkRumorForm.quotedContent" placeholder="请输入内容"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSave">提 交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { queryRumorById, saveOrUpdateCheck } from '@/api/api.js'

export default {
  name: 'checkRumor',
  created() {
    const { id, checkManId } = this.$route.query
    this.queryRumorInfo(id)
    this.checkRumorForm.checkManId = checkManId
  },
  data() {
    return {
      rumor: {
        id: '',
        title: '',
        abstractInfo: '',
        professionalFieldId: '',
        professionalFieldName: '',
        status: '待核查',
        source: '',
        askUserId: ''
      },
      checkRumorForm: {
        checkManId: '',
        checkPoints: [
          {
            value: ''
          }
        ],
        quotedContent: ''
      },
      formRule: {
        quotedContent: [
          { required: true, message: '请输入引用内容', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    queryRumorInfo(id) {
      var params = {
        id: id
      }
      queryRumorById(params).then(rep => {
        this.rumor = rep.results
      }).catch(err => {
        this.$message.error(err)
      })
    },
    handleSave() {
      this.$refs.checkRumorForm.validate(valid => {
        if (valid) {
          var checkPoints = []
          this.checkRumorForm.checkPoints.forEach(element => {
            checkPoints.push(element.value)
          })
          this.checkRumorForm.checkPoints = checkPoints
          var params = {
            ...this.rumor,
            ...this.checkRumorForm
          }
          saveOrUpdateCheck(params).then(rep => {
            if (rep.status === 'success') {
              this.$message.success('提交成功。')
            } else {
              this.$message.error(rep.msg)
            }
          }).catch(err => {
            this.$message.error(err)
          })
        }
      })
    },
    removeCheckPoint(item) {
      var index = this.checkRumorForm.checkPoints.indexOf(item)
      if (index !== -1) {
        this.checkRumorForm.checkPoints.splice(index, 1)
      }
    },
    addCheckPoint() {
      this.checkRumorForm.checkPoints.push({
        value: '',
        key: Date.now()
      })
    }
  }
}
</script>

<style>
  .checkRumor {
    line-height:1.7;
    margin:15px 300px;
    padding:15px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  }
  .title {
    font-size: 2.5rem;
    color: #000000;
    margin-bottom: 0.55rem;
    margin-top: 0.52rem;
  }
  .content {
    font-size: 1.25rem;
    color: #000000;
    margin-bottom: 0.55rem;
  }
</style>
