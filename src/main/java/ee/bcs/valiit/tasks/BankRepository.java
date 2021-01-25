package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public BankCustomerData bankCustomer(
            @RequestParam("customer_id") String custId,
            @RequestParam("cust_name") String custName,
            @RequestParam("cust_address") String custAddrss,
            @RequestParam("account_type") String custAccType,
            @RequestParam("account_number") String custAccNr,
            @RequestParam("account_balance") int custAccBalance,
            @RequestParam("transaction_amount") int transAmt,
            @RequestParam("transaction_type") String transType) {

        BankCustomerData bankCustomer = new BankCustomerData();
        bankCustomer.setCustId(custId);
        bankCustomer.setCustName(custName);
        bankCustomer.setCustAddress(custAddrss);
        bankCustomer.setCustAccType(custAccType);
        bankCustomer.setCustAccNr(custAccNr);
        bankCustomer.setCustAccBalance(custAccBalance);
        bankCustomer.setTransAmount(transAmt);
        bankCustomer.setTransType(transType);
        return bankCustomer;
    }
    /*    {
    "custId":"1111",
    "custName":"Thomas Thol",
    "custAddress":"Talu 8",
    "custAccType":"Current Account",
    "custAccNr":"EE1111"
     }   */
//  http://localhost:8080/bank/createCustomerBody
    public void createCustomerBody(BankCustomerData bankCustomer) {
        String sql = "INSERT INTO bank_accounts (customer_id, customer_address, cust_name) " +
                "VALUES (:cust_id, :customer_addressKey, :cust_nameKey)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("cust_id", bankCustomer.getCustId());
        paraMap.put("cust_nameKey", bankCustomer.getCustName());
        paraMap.put("customer_addressKey", bankCustomer.getCustAddress());
        jdbcTemplate.update(sql, paraMap);
    }

//  http://localhost:8080/bank/createCustomerAccount
    public void createCustomerAccount(BankCustomerData bankCustomer) {
        String sql = "INSERT INTO customer_accounts (account_cust_id, account_type, account_number, account_balance) " +
                "VALUES (:acc_cust_id, :acc_type, :acc_nr, :acc_balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("acc_cust_id", bankCustomer.getCustId());
        paraMap.put("acc_type", bankCustomer.getCustAccType());
        paraMap.put("acc_nr", bankCustomer.getCustAccNr());
        paraMap.put("acc_balance", 0);
        jdbcTemplate.update(sql, paraMap);
    }

    // http://localhost:8080/bank/accountBalance?account_nr=EE6666
    public int accountBalance(@RequestParam String account_nr) {
        String sql = "SELECT account_balance FROM customer_accounts WHERE account_number = :accParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("accParam", account_nr);
        return jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
}
    //http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
    public int updateAccountBalance(@RequestParam String account_nr_to, @RequestParam Integer newAccBalance) {
        String sql2 = "UPDATE customer_accounts SET account_balance = :newAccBalanceKey WHERE account_number = :accParam";
        Map<String, Object> paraMap2 = new HashMap<>();
        paraMap2.put("newAccBalanceKey", newAccBalance);
        paraMap2.put("accParam", account_nr_to);
        return jdbcTemplate.update(sql2, paraMap2);
    }
}
