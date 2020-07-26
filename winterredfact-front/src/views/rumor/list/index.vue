<template>
  <div>
    <span>辟谣首页</span>
    <button @click="handleClick">跳转</button>
  </div>
</template>

<script>
export default {
  name: 'List',
  methods: {
    handleClick() {
      this.$router.push({
        path: '/rumor/detail'
      })
    }
  }
}
</script>

<style>

</style>
