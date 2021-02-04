<template>
  <div class="home">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Welcome to our bank!</h1>
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>
