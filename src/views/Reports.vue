<template>
  <div class="reports">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Queries and reports</h1>
    Enter customer account number: <input v-model="custAccNr" placeholder="Customer account number"> <br><br>

    <button v-on:click="getCustAccBalance()">Get account balance</button>
    <br>
    <br>
    <table border="1" align="center">
      <tr>
        <th>Query result</th>
      </tr>
      <tr>
        <td>{{ balance }}</td>
      </tr>
    </table>
    <br>
    <button v-on:click="getCustAccAndBalancesButton()">Get all accounts and balances</button>
    <br><br>
    <table border="1" align="center">
      <tr>
        <th>Id</th>
        <th>Account type</th>
        <th>Account number</th>
        <th>Account balance</th>
      </tr>
      <tr v-for="row in customers">
        <td>{{ row.accountCustId }}</td>
        <td>{{ row.custAccType }}</td>
        <td>{{ row.custAccNr }}</td>
        <td>{{ row.custAccBalance }}</td>
      </tr>
    </table>
    <br>
    <button v-on:click="getTransHistoryButton()">Get all transaction history</button>
    <br><br>
    <table border="1" align="center">
      <tr>
        <th>Id</th>
        <th>Transaction amount</th>
        <th>Account number</th>
        <th>Transaction type</th>
        <th>Balance after transaction</th>
        <th>Transaction date/time</th>
      </tr>
      <tr v-for="row in fullTransactions">
        <td>{{ row.transId }}</td>
        <td>{{ row.transAmount }}</td>
        <td>{{ row.custAccNr }}</td>
        <td>{{ row.transType }}</td>
        <td>{{ row.balanceAfterTransaction }}</td>
        <td>{{ row.transDate }}</td>
      </tr>
    </table>
    <br>
    <button v-on:click="getCustomerTransHistoryButton()">Get Customer transaction history</button>
    <br><br>
    <table border="1" align="center">
      <tr>
        <th>Id</th>
        <th>Transaction amount</th>
        <th>Account number</th>
        <th>Transaction type</th>
        <th>Balance after transaction</th>
        <th>Transaction date/time</th>
      </tr>
      <tr v-for="row in customerTransactions">
        <td>{{ row.transId }}</td>
        <td>{{ row.transAmount }}</td>
        <td>{{ row.custAccNr }}</td>
        <td>{{ row.transType }}</td>
        <td>{{ row.balanceAfterTransaction }}</td>
        <td>{{ row.transDate }}</td>
      </tr>
    </table>
  </div>
</template>

<script>

let getAccountBalance = function () {
  //alert(this.custAccNr);
  this.$http.get("http://localhost:8080/bank/accountBalance?account_nr=" + this.custAccNr)
      .then(response => this.balance = response.data)
      .catch(response => console.log(response))
}

let getCustAccountsAndBalances = function () {
  this.$http.get("http://localhost:8080/bank/customersAccounts")
      .then(response => this.customers = response.data)
      .catch(response => console.log(response))
}
let getFullTransactionHistory = function () {
  this.$http.get("http://localhost:8080/bank/fullTransactionHistory")
      .then(response => this.fullTransactions = response.data)
      .catch(response => console.log(response))
}
let getCustomerTransactionHistory = function () {
  this.$http.get("http://localhost:8080/bank/customerTransactionHistory?custAccNr="+this.custAccNr)
      .then(response => this.customerTransactions = response.data)
      .catch(response => console.log(response))
}

export default {
  name: 'Reports',
  components: {},
  data: function () {
    return {
      accounts: {},
      custAccNr: "",
      balance: "",
      customers: [],
      fullTransactions: [],
      customerTransactions: []
    }
  },
  methods: {
    getCustAccBalance: getAccountBalance,
    getCustAccAndBalancesButton: getCustAccountsAndBalances,
    getTransHistoryButton: getFullTransactionHistory,
    getCustomerTransHistoryButton: getCustomerTransactionHistory
  }
}
</script>