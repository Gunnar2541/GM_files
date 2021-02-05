<template>
  <div class="home">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Welcome to our bank!</h1>
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->

    Customer id: <input v-model="custId" placeholder="Login id"> <br><br>
    Customer name: <input v-model="custPwUncoded" placeholder="Password"><br><br>
    <button v-on:click="getLoginButton()">Press to log in</button>
    <br><br>
  </div>
</template>

<script>
//http://localhost:8080/public/bank/login?custId=1234&password=one
let getLogin = function () {
  this.$http.get("http://localhost:8080/public/bank/login?custId=" + this.custId+"&password="+this.custPwUncoded)
      .then(response => this.login = response.data)  //(siit stored ära tokeni loginist)
        localStorage.setItem("user-token", this.login)      // store the token// localStorage.setItem('user-token', token)
        this.$http.defaults.headers.common["Authorization"] = "Bearer " + this.login
      .catch(response => console.log(response))
}
    .then(response => this.transferActionResult = response.data)
    .catch(error => this.transferActionResult=error.response.data.message)

export default {
  name: 'home',
  components: {},

  data: function () {
    return {
      login: "",
      token: "",
      custId: "",
      custPwUncoded: ""
    }
  },
  methods: {
    getLoginButton: getLogin
   }
}

// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
//export default {
//  name: 'Home',
//  components: {
//    HelloWorld }}
</script>
