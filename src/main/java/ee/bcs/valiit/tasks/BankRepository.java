package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /*    {
    "custId":"1111",
    "custName":"Thomas Thol",
    "custAddress":"Talu 8"
     }   */
//  http://localhost:8080/bank/createCustomerB
    public void createCustomerBody(BankCustomerData bankCustomer) {
        String sql = "INSERT INTO bank_accounts (customer_id, customer_address, cust_name) " +
                "VALUES (:cust_id, :customer_addressKey, :cust_nameKey)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("cust_id", bankCustomer.getCustId());
        paraMap.put("cust_nameKey", bankCustomer.getCustName());
        paraMap.put("customer_addressKey", bankCustomer.getCustAddress());
        jdbcTemplate.update(sql, paraMap);
    }

    /*    {
    "custId":"7777",
    "custAccType":"Current Account",
    "custAccNr":"EE7777"
     }   */
    //  http://localhost:8080/bank/createCustomerAccount
    public void createCustomerAccount(BankCustomerData bankAccount) {
        String sql = "INSERT INTO customer_accounts (account_cust_id, account_type, account_number, account_balance) " +
                "VALUES (:acc_cust_id, :acc_type, :acc_nr, :acc_balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("acc_cust_id", bankAccount.getCustId());
        paraMap.put("acc_type", bankAccount.getCustAccType());
        paraMap.put("acc_nr", bankAccount.getCustAccNr());
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

    public void dataToTransHistory(@RequestParam String accountFrom,
                                   @RequestParam String accountTo,
                                   @RequestParam String transType,
                                   @RequestParam Integer transAmount) {

        String sql3 = "INSERT INTO transaction_history (transaction_account_from, transaction_account_to, " +
                "transaction_type, transaction_amount) " +
                "VALUES (:trans_acc_from, :trans_acc_to, :trans_type, :trans_amount)";
        Map<String, Object> paraMap3 = new HashMap<>();
        paraMap3.put("trans_acc_from", accountFrom);
        paraMap3.put("trans_acc_to", accountTo);
        paraMap3.put("trans_type", transType);
        paraMap3.put("trans_amount", transAmount);
        jdbcTemplate.update(sql3, paraMap3);
    }
}
