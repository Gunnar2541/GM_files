<template>
  <div class="reports">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Customer account balance</h1>
    Enter customer account number: <input v-model="custAccNr" placeholder="Customer account number"> <br>

    <button v-on:click="getCustAccBalance()">Get account balance</button><br>

    <table border="0"><br>
      <tr>
        <th>Query result</th>
      </tr>
     <tr>
       <td>{{balance}}</td>
      </tr>
    </table>
  </div>
</template>

<script>

let getAccountBalance = function () {
  //alert(this.custAccNr);
  this.$http.get("http://localhost:8080/bank/accountBalance?account_nr="+this.custAccNr)
      .then(response => this.balance = response.data)
      .catch(response => console.log(response))
}

export default {
  name: 'Reports',
  components: {},  //siia saaks "importida" mõne teise komponendi, nt search vmt.
  data: function (){
    return{
      accounts: {},
      custAccNr:"",
      balance: ""
    }
  },
  methods: {
    getCustAccBalance: getAccountBalance
  }
}
</script>