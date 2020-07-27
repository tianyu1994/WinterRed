<template>
  <div class='checkRumor'>
    <div class='title' v-html="checkRumorForm.title"></div>
    <div class='content' v-html="checkRumorForm.abstractInfo"></div>
    <div class='content' v-html="checkRumorForm.professionalFieldName"></div>
    <el-divider content-position="right"></el-divider>
    <el-radio-group v-model="checkRumorForm.status">
      <el-radio label="待核查">待核查</el-radio>
      <el-radio label="尚无定论">尚无定论</el-radio>
      <el-radio label="谣言">谣言</el-radio>
      <el-radio label="确实如此">确实如此</el-radio>
    </el-radio-group>
    <br/>
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="handleSave">提 交</el-button>
  </div>
</template>

<script>

export default {
  name: 'checkRumor',
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
        status: '待核查'
      }
    }
  },
  methods: {
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
