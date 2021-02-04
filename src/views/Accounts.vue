<template>
  <div class="accounts">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Create new customer account page</h1>

    Enter your ID: <input v-model="account.accountCustId" placeholder="Customer Id"> <br>
    Enter account number in format "EE1234": <input v-model="account.custAccNr"
                                                    placeholder='account number in format "EE1234"'><br>

    Select the account type: <select v-model="account.custAccType">
    <option disabled value="">Please select account type</option>
    <option>Current account</option>
    <option>Deposit account</option>
    <option>Loan account</option>
  </select>

    <br><br>
    <button v-on:click="saveCustAccInHtml()">Save new customer account</button>
    <br>
    <table border="0"><br>
      <tr>
        <th>Account creation result</th>
      </tr>
      <tr>
        <td>{{ creationResult }}</td>
      </tr>
    </table>
  </div>
</template>

<script>

let saveInJs = function () {
  this.$http.post("http://localhost:8080/bank/createCustomerAccount", this.account)
      //.then(response => alert(response.data))
      .then(response => this.creationResult = response.data)
      .catch(error => this.creationResult=error.response.data.message)
  alert("Possibly duplicate account")
}

export default {
  name: 'Accounts',
  components: {},
  data: function () {
    return {
      account: {},
      creationResult: ""
    }
  },
  methods: {
    saveCustAccInHtml: saveInJs,
  }
}
</script>

<style scoped>

</style>