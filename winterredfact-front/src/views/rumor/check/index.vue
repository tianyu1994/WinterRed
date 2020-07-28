<template>
  <div class='checkRumor'>
    <div class='title' v-html="checkRumorForm.title"></div>
    <div class='content' v-html="checkRumorForm.abstractInfo"></div>
    <div class='content' v-html="checkRumorForm.professionalFieldName"></div>
    <el-divider content-position="right"></el-divider>
    <el-form ref='checkRumorForm' :model="checkRumorForm" :rules="formRule">
      <el-form-item label="鉴定" prop="status">
        <el-radio-group v-model="checkRumorForm.status">
          <el-radio label="待核查">待核查</el-radio>
          <el-radio label="尚无定论">尚无定论</el-radio>
          <el-radio label="谣言">谣言</el-radio>
          <el-radio label="确实如此">确实如此</el-radio>
        </el-radio-group>
      </el-form-item>
      <font>考证要点</font>
      <el-button @click="addQuotedContent">新增要点</el-button>
      <el-form-item
        v-for="(quotedContent, index) in checkRumorForm.quotedContents"
        :label="'要点' + (index + 1) "
        :key="quotedContent.key"
        :prop="'quotedContents.' + index + '.value'"
        :rules="{
          required: true, message: '要点不能为空', trigger: 'blur'
        }"
      >
        <el-input type="textarea" v-model="quotedContent.value" placeholder="请输入内容" rows=2></el-input>
        <el-button v-if="index>0" @click.prevent="removeQuotedContent(quotedContent)">删除</el-button>
      </el-form-item>
      <el-form-item label="信息来源" prop="source">
        <el-input v-model="checkRumorForm.source" placeholder="请输入内容"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSave">发 布</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: 'checkRumor',
  created() {
    const { id } = this.$route.query
    this.queryRumorInfo(id)
  },
  data() {
    return {
      professionalList: [
        {
          id: 1,
          fieldName: '计算机科学'
        },
        {
          id: 2,
          fieldName: '医学'
        }
      ],
      checkRumorForm: {
        title: '中国崛起',
        abstractInfo: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容',
        professionalFieldName: '计算机科学',
        status: '待核查',
        quotedContents: [
          {
            value: ''
          }
        ],
        source: ''
      },
      formRule: {}
    }
  },
  methods: {
    queryRumorInfo(id) {

    },
    handleSave() {

    },
    removeQuotedContent(item) {
      var index = this.checkRumorForm.quotedContents.indexOf(item)
      if (index !== -1) {
        this.checkRumorForm.quotedContents.splice(index, 1)
      }
    },
    addQuotedContent() {
      this.checkRumorForm.quotedContents.push({
        value: '',
        key: Date.now()
      })
    }
  }
}
</script>

<style>
  .checkRumor {
    padding: 0.55rem 11.20rem 0.55rem 11.20rem;
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
