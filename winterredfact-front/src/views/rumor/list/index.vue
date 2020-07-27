<template>
  <div style='margin:15px 300px; padding:15px;'>
    <div>
      <div>
        <!--首页顶端-->
        <div class='background'>
          <img :src='imgSrc' width='100%' height='100%' alt />
        </div>
        <div style='height: 70px'></div>
        <div style='width: 100%; text-align: center'>
          <div style='width: 25%; display: inline'></div>
          <el-input placeholder='请输入内容' style='width: 60%;'>
            <template slot='append' style='background-color: #bbbb;'>搜索</template>
          </el-input>
          <el-button type="info">订阅</el-button>
          <el-button type="info" @click="checkerRegist">专家注册</el-button>
        </div>
        <div style='height: 80px'></div>
      </div>
    </div>
    <div>
      <ul v-for='(item,index) in tableData' :key='index'>
        <li style='list-style: none; float:left; width: 100%; border-bottom: 0.6px solid purple;'>
          <div style="display: inline-block">
            <div style='height: 20px'></div>
            <div style='height: 30px'>
              <span style="font-size: 20px;">{{item.info}}</span>
              <span> {{item.result}} </span>
            </div>
            <div style='height: 30px'>{{item.date}}</div>
            <div style='height: 20px'></div>
          </div>
          <div style="display: inline-block; height: 100px; width: 100px; float: right;">
            <div style="height: 10%; "></div>
            <img :src = 'img' width="80%" height="80%" style="transform: rotate(15deg)"  alt />
          </div>

        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'List',
    data() {
      return {
        input: '',
        words: '',
        img: require('@/assets/100false.png'),
        imgSrc: require('@/assets/bg.jpg'),
        tableData: [
          {
            date: '2016-05-03',
            result: '谣言',
            info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
          },
          {
            date: '2016-05-02',
            result: '确实如此',
            info: '西班牙从去年3月的废水检出新冠病毒相关研究漏洞大'
          },
          {
            date: '2016-05-04',
            result: '谣言',
            info: '因疫情原因，中国驻纳米比亚使馆组织包机回国'
          },
          {
            date: '2016-05-01',
            result: '谣言',
            info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
          },
          {
            date: '2016-05-08',
            result: '谣言',
            info: '钟南山院士已抵达新疆乌鲁木齐抗击疫情'
          }
        ]
      }
    },
    mounted() {
    },
    methods: {
      checkerRegist() {
        this.$router.push('/rumor/checkerRegist')
      }
    }
  }
</script>

<style lang='scss'>
  .middle {
    height: 180px;
    width: 800px;
    align-content: center;
    position: relative;
    background-repeat: none;
  }
  .background {
    width: 60%;
    height: 25%; /**宽高100%是为了图片铺满屏幕 */
    z-index: -1;
    position: absolute;
  }
  .page {
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  }
  .searchBar {
    .isFixed {
      position: fixed;
      background-color: #fff;
      top: 0;
      z-index: 999;
    }
    ul {
      width: 100%;
      height: 40px;
      line-height: 40px;
      display: flex;
      li {
        font-size: 0.8rem;
        text-align: center;
        flex: 1;
        i {
          font-size: 0.9rem;
          padding-left: 5px;
          color: #ccc;
        }
      }
      border-bottom: 1px solid #ddd;
    }
    .el-select .el-input {
      width: 70%;
    }
    .input-with-select .el-input-group__prepend {
      background-color: #fff;
    }
  }
</style>
