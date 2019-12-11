<template>
  <div>
    <PhoneBookHeader></PhoneBookHeader>
    <PhoneBookSearch
      :phoneBooks="phoneBooks"></PhoneBookSearch>
    <PhoneBookList
      :letter="letter"
      :phoneBooks="phoneBooks"></PhoneBookList>
    <PhoneBookAlphabet
      @change="handleLetterChange"
      :phoneBooks="phoneBooks"></PhoneBookAlphabet>
  </div>
</template>

<script>
  import axios from 'axios'
  import {getServerUrl} from '@/config/sys.js'
  import PhoneBookHeader from './components/Header'
  import PhoneBookSearch from './components/Search'
  import PhoneBookList from './components/List'
  import PhoneBookAlphabet from './components/Alphabet'
  import PubSub from 'pubsub-js'

  export default {
    name: "PhoneBook",
    data() {
      return {
        phoneBooks: {},
        letter: ''
      }
    },
    components: {
      PhoneBookHeader,
      PhoneBookSearch,
      PhoneBookList,
      PhoneBookAlphabet
    },
    methods: {
      refreshToken() {
        let token = window.localStorage.getItem("token")
        axios.defaults.headers.common['token'] = token
        let url = getServerUrl("refreshToken");
        axios.get(url)
          .then(response => {
            console.log("token刷新：" + response.data.token)
            window.localStorage.setItem("token", response.data.token);
          }).catch(error => {
          console.log(error)
        })
      },
      getPhoneBooks() {
        let token = window.localStorage.getItem("token")
        axios.defaults.headers.common['token'] = token
        let url = getServerUrl("phoneBook/loadAll");
        axios.get(url)
          .then(response => {
            console.log(response)
            this.phoneBooks = response.data.data
          }).catch(error => {
          console.log(error)
        })
      },
      handleLetterChange(letter) {
        console.log('传来的字母:' + letter)
        this.letter = letter
      }
    },
    mounted() {
      setInterval(this.refreshToken, 1000 * 60 * 10); // 10分钟刷新一次token
      this.getPhoneBooks(); // 加载所有电话簿信息

      PubSub.subscribe('refreshPhoneBook', (msg, data) => {
//回调函数中data是传过来的数据
//回调函数中mes实际上指前面的名字，毫无作用，但必须写上
        console.log("收到消息：" + data)
        this.getPhoneBooks()
      })
    }
  }
</script>

<style scoped>

</style>
