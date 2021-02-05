<template>
  <div class="register">
    <img alt="Bank logo" src="../assets/BankLogo.jpg"><br>
    <h1>Register new customer data page</h1>
    <!-- kliendi loomine -->
    Customer id: <input v-model="customer.custId" placeholder="Customer Id"> <br>
    Customer name: <input v-model="customer.custName" placeholder="Full name"><br>
    Customer address: <input v-model="customer.custAddress" placeholder="Address"><br>
    Desired password: <input v-model="customer.custPwUncoded" placeholder="Password"><br>
    <br>
    <button v-on:click="saveCustDataInHtml()">Save new Customer data</button>
    <br><br>
    <button v-on:click="getCustData()">Get customer list</button>
    <br><br>
    <!-- {{customers}} topelt loogelistes annab loetelu ehk array -->

    <table border="1" align="center">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Address</th>
      </tr>
      <tr v-for="row in customers">
        <td>{{ row.custId }}</td>
        <td>{{ row.custName }}</td>
        <td>{{ row.custAddress }}</td>
      </tr>
    </table>
  </div>
</template>

<script>

let getData = function () {
  this.$http.get("http://localhost:8080/bank/customerList")
      .then(response => this.customers = response.data) //(siit stored ära tokeni loginist)
      .catch(response => console.log(response))
}

let saveInJs = function () {
  this.$http.post("http://localhost:8080/bank/createCustomerB", this.customer)
      .then(response => alert(response.data))
      .catch(response => console.log(response))
  //alert('Salvesta info')
}

export default {
  name: 'register',
  components: {},  //siia saaks "importida" mõne teise komponendi, nt search vmt.
  data: function () {
    return {
      customer: {},   //tühi objekt, need muutujad nagu JAva klassid, kättesaadavad üle selle lehe(componendi) siin
      customers: []  //tühi array. mõlemal käib this. ees, kui tahame Javaga siduda.
    }                 //v-Model hakkab kohe objekti, arraysse elemente lisama (vt rida 6)
  },
  methods: {
    saveCustDataInHtml: saveInJs,
    getCustData: getData
//  },
//  mounted() {
//    this.getData();  //kui sellele lehele lähed, tõmba käima see funktsioon. So automaatne triger
  }
}
</script>