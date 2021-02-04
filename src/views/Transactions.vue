<template>
  <div class="register">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Transactions</h1>
    Account number to take money from: <input v-model="custAccFrom" placeholder="EE1234"> <br><br>
    Account number to send money to: <input v-model="custAccTo" placeholder="EE1234"> <br><br>
    Amount to place/withdraw: <input v-model="custAmount" placeholder="Amount"><br><br>

    <button v-on:click="depositPlacementButton()">Execute new deposit</button><br><br>
    <button v-on:click="withdrawMoneyButton()">Execute new withdrawal</button><br><br>
    <button v-on:click="transferMoneyButton()">Execute new acc-to-acc transaction</button><br><br>
    <br>
    <table border="0" align="center">
      <tr>
        <th>Deposit placement confirmation</th>
      </tr>
      <tr>
        <td>{{depoActionResult}}</td>
      </tr>
    </table>
    <table border="0" align="center">
      <tr>
        <th>Wthdrawal confirmation</th>
      </tr>
      <tr>
        <td>{{withdrawActionResult}}</td>
      </tr>
    </table>
    <table border="0" align="center">
      <tr>
        <th>Transfer confirmation</th>
      </tr>
      <tr>
        <td>{{transferActionResult}}</td>
      </tr>
    </table>
  </div>
</template>
<script>

let placeDeposit = function (){
  // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
  this.$http.put("http://localhost:8080/bank/depositMoney?account_nr_to="
      +this.custAccTo+"&amount="+this.custAmount)
      .then(response => this.depoActionResult = response.data)
      .catch(error => this.depoActionResult=error.response.data.message)
}

//http://localhost:8080/bank/withdrawMoney?accountFrom=EE2222&amount=125
let withdrawMoney = function () {
  this.$http.put("http://localhost:8080/bank/withdrawMoney?accountFrom="
      + this.custAccFrom + "&amount=" + this.custAmount)
      .then(response => this.withdrawActionResult = response.data)
      .catch(error => this.withdrawActionResult=error.response.data.message)
}

//http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE3333&amount=130
let transactionOfMoney = function () {
  this.$http.put("http://localhost:8080/bank/transferMoney?accountFrom="
      + this.custAccFrom + "&accountTo=" + this.custAccTo + "&amount=" + this.custAmount)
      .then(response => this.transferActionResult = response.data)
      .catch(error => this.transferActionResult=error.response.data.message)
}

  export default {
  name: 'Transaction',
  data: function () {
    return {
      custAmount: "",
      custAccFrom: "",
      custAccTo: "",
      depoActionResult: "",
      withdrawActionResult: "",
      transferActionResult: ""
    }
  },
  methods: {
    depositPlacementButton: placeDeposit,
    withdrawMoneyButton: withdrawMoney,
    transferMoneyButton: transactionOfMoney
  }
}
</script>