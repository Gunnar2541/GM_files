// PROOVIN LISADA ROHKEM FUNKTSIOONE

package ee.bcs.valiit.tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("bank")
@RestController
public class BankControllerMore {

    @GetMapping("bankCust")
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

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    HashMap<String, BigDecimal> accountMap = new HashMap<>();
    String output = "";

    /*    {
    "custId":"1111",
    "custName":"Thomas Thol",
    "custAddress":"Talu 8",
    "custAccType":"Current Account",
    "custAccNr":"EE1111"
     }   */
//  http://localhost:8080/bank/createCustomerBody
    @PostMapping("createCustomerBody")
    public String createCustomerBody(@RequestBody BankCustomerData bankCustomer) {
        String sql = "INSERT INTO bank_accounts (customer_id, customer_address, cust_name) " +
                     "VALUES (:cust_id, :customer_addressKey, :cust_nameKey)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("cust_id", bankCustomer.getCustId());
        paraMap.put("cust_nameKey", bankCustomer.getCustName());
        paraMap.put("customer_addressKey", bankCustomer.getCustAddress());
        jdbcTemplate.update(sql, paraMap);


        String sql2 = "INSERT INTO customer_accounts (account_cust_id, account_type, account_number, " +
                      "account_balance) " + "VALUES (:acc_cust_id, :acc_type, :acc_nr, :acc_balance)";
        Map<String, Object> paraMap2 = new HashMap<>();
        paraMap2.put("acc_cust_id", bankCustomer.getCustId());
        paraMap2.put("acc_type", bankCustomer.getCustAccType());
        paraMap2.put("acc_nr", bankCustomer.getCustAccNr());
        paraMap2.put("acc_balance", 0);
        jdbcTemplate.update(sql2, paraMap2);

        return bankCustomer.getCustAccNr() + " for " + bankCustomer.getCustName() + " has been created.\n" +
                "This account is "+bankCustomer.getCustAccType()+".";
    }
    /*    {
    "custId":"1111",
    "custAccType":"Deposit Account",
    "custAccNr":"EE1111"
     }     */
    //  http://localhost:8080/bank/createAdditionalAccount
    @PostMapping("createAdditionalAccount")
    public String createAdditionalAccount(@RequestBody BankCustomerData bankCustomer) {
    String sql = "INSERT INTO customer_accounts (account_cust_id, account_type, account_number, account_balance) " +
            "VALUES (:acc_cust_id, :acc_type, :acc_nr, :acc_balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("acc_cust_id", bankCustomer.getCustId());
        paraMap.put("acc_type", bankCustomer.getCustAccType());
        paraMap.put("acc_nr", bankCustomer.getCustAccNr());
        paraMap.put("acc_balance", 0);
        jdbcTemplate.update(sql, paraMap);

        return "New account for ID " +bankCustomer.getCustId() + " has been created.\n" +
                "This account is "+bankCustomer.getCustAccType()+".";
}

/*
    // http://localhost:8080/bank/createAccount?customer_id=123&account_nr=EE222&cust_name=Toomas&account_balance=0
    @PostMapping("createAccount")
    public String createAccount(@RequestParam String customer_id,
                                @RequestParam String account_nr,
                                @RequestParam String cust_name,
                                @RequestParam Integer account_balance) {
        String sql = "INSERT INTO bank_accounts (customer_id, account_nr, cust_name, account_balance) " +
                "VALUES (:customer_id, :account_nr, :cust_name, :account_balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("customer_id", customer_id);
        paraMap.put("account_nr", account_nr);
        paraMap.put("cust_name", cust_name);
        paraMap.put("account_balance", account_balance);
        jdbcTemplate.update(sql, paraMap);
        return account_nr + " for " + cust_name + " has been created.";
    }
*/
    // http://localhost:8080/bank/accountBalance?account_nr=EE6666
    @GetMapping("accountBalance")
    public String accountBalance(@RequestParam String account_nr) {
        String sql = "SELECT account_balance FROM customer_accounts WHERE account_number = :accParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("accParam", account_nr);
        return "Balance of the account nr: "+account_nr+", is: "+jdbcTemplate.queryForObject(sql, paraMap, String.class);
        //annab vastuseks account_balance
    }

    // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
    @PutMapping("depositMoney")
    public String depositMoney(@RequestParam String account_nr_to, @RequestParam Integer amount) {
        String newbalance;
        if (amount < 0)
            return "Check your amount input, cant add negative amount";
        else {
            String sql = "SELECT account_balance FROM customer_accounts WHERE account_number = :accParam";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("accParam", account_nr_to);
            Integer oldAccBalance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);

            Integer newAccBalance = oldAccBalance + amount;
            String sql2 = "UPDATE customer_accounts SET account_balance = :newAccBalanceKey WHERE account_number = :accParam";
            Map<String, Object> paraMap2 = new HashMap<>();
            paraMap2.put("newAccBalanceKey", newAccBalance);
            paraMap2.put("accParam", account_nr_to);
            jdbcTemplate.update(sql2, paraMap2);
            newbalance = newAccBalance.toString();

            String sql3 =  "INSERT INTO transaction_history (transaction_account_to, transaction_type, transaction_amount) " +
                    "VALUES (:trans_acc, :trans_type, :trans_amount)";
            Map<String, Object> paraMap3 = new HashMap<>();
            paraMap3.put("trans_acc", account_nr_to);
            paraMap3.put("trans_type", "Deposit to account");
            paraMap3.put("trans_amount", amount);
            jdbcTemplate.update(sql3, paraMap3);
        }
        return amount+" was deposited to account nr: "+ account_nr_to +". The new balance is " + newbalance;
    }

// http://localhost:8080/bank/withdrawMoney?account_from=EE5555&amount=125
        @PutMapping("withdrawMoney")
        public String withdrawMoney(@RequestParam String account_from, @RequestParam int amount) {
            if (amount <= 0)
                return "Check your amount input, cant add negative amount";
            else {
                String sql = "SELECT account_balance FROM customer_accounts WHERE account_number = :accParam" ;
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("accParam", account_from);
                Integer oldAccBalance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
                try {
                    if ((oldAccBalance - amount) < 0) {
                        return "There is not enough cash in the account";
                    } else {
                        Integer newAccBalance = oldAccBalance - amount;
                        String sql2 = "UPDATE customer_accounts SET account_balance = :newAccBalanceKey WHERE account_number = :accParamKey";
                        Map<String, Object> paraMap2 = new HashMap<>();
                        paraMap2.put("newAccBalanceKey", newAccBalance);
                        paraMap2.put("accParamKey", account_from);
                        jdbcTemplate.update(sql2, paraMap2);
                        output = newAccBalance.toString();

                        String sql3 =  "INSERT INTO transaction_history (transaction_account_from, transaction_type, transaction_amount) " +
                                "VALUES (:trans_acc, :trans_type, :trans_amount)";
                        Map<String, Object> paraMap3 = new HashMap<>();
                        paraMap3.put("trans_acc", account_from);
                        paraMap3.put("trans_type", "Withdraw money from account");
                        paraMap3.put("trans_amount", amount);
                        jdbcTemplate.update(sql3, paraMap3);

                        return "New balance for account nr: " + account_from + " is: " + output;
                    }
                } catch (NullPointerException e) {
                    return "There is not enough cash in the account (Error)";
                }
            }
    }

    //http://localhost:8080/bank/mahaMoneyB
   /*    {
        "custAcc":"EE888",
        "transAmount":100
    }
        @PutMapping("mahaMoneyB")
        public String mahaMoneyB(@RequestBody BankCustomerData bankCustomer) {
        if (bankCustomer.getTransAmount() <= 0)
            return "Check your amount input, cant calculate with negative amount";
        else {
            String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :accParam";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("accParam", bankCustomer.getCustAccNr());
            Integer oldAccBalance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
            try {
                if ((oldAccBalance - bankCustomer.getTransAmount()) < 0) {
                    return "There is not enough cash in the account";
                } else {
                    Integer newAccBalance = oldAccBalance - bankCustomer.getTransAmount();
                    String sql2 = "UPDATE bank_accounts SET account_balance = :newAccBalanceKey WHERE account_nr = :accParamKey";
                    Map<String, Object> paraMap2 = new HashMap<>();
                    paraMap2.put("newAccBalanceKey", newAccBalance);
                    paraMap2.put("accParamKey", bankCustomer.getCustAccNr());
                    jdbcTemplate.update(sql2, paraMap2);
                    return "New balance for account nr: " + bankCustomer.getCustAccNr() + " is: " + newAccBalance;
                }
            } catch (NullPointerException e) {
                return "There is not enough cash in the account (Error)";
            }
        }
    }
*/
    // http://localhost:8080/bank/transferMoney?fromAccount=EE6666&toAccount=EE4444&amount=130
    @PutMapping("transferMoney")
    public String transferMoney(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam int amount ) {
            Integer newAccBalanceFrom;
            Integer newAccBalanceTo;
            if (amount <= 0)
                return "Check your amount input, cant add negative amount";
            else {
                String sql = "SELECT account_balance FROM customer_accounts WHERE account_number = :accParam" ;
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("accParam", fromAccount);
                Integer oldAccBalanceFrom = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
                try {
                    if ((oldAccBalanceFrom - amount) < 0) {
                        return "There is not enough cash in the account";
                    } else {
                        newAccBalanceFrom = oldAccBalanceFrom - amount;
                        String sql2 = "UPDATE customer_accounts SET account_balance = :newAccBalanceKey WHERE account_number = :accParamKey";
                        Map<String, Object> paraMap2 = new HashMap<>();
                        paraMap2.put("newAccBalanceKey", newAccBalanceFrom);
                        paraMap2.put("accParamKey", fromAccount);
                        jdbcTemplate.update(sql2, paraMap2);

                        String sql3 =  "INSERT INTO transaction_history (transaction_account_from, transaction_account_to, transaction_type, transaction_amount) " +
                                "VALUES (:trans_acc_from, :trans_acc, :trans_type, :trans_amount)";
                        Map<String, Object> paraMap3 = new HashMap<>();
                        paraMap3.put("trans_acc_from", fromAccount);
                        paraMap3.put("trans_acc", toAccount);
                        paraMap3.put("trans_type", "Transfer money to another account");
                        paraMap3.put("trans_amount", amount);
                        jdbcTemplate.update(sql3, paraMap3);

                        try {
                            String sql4 = "SELECT account_balance FROM customer_accounts WHERE account_number = :accParam";
                            Map<String, Object> paraMap4 = new HashMap<>();
                            paraMap4.put("accParam", toAccount);
                            Integer oldAccBalanceTo = jdbcTemplate.queryForObject(sql4, paraMap4, Integer.class);

                            newAccBalanceTo = oldAccBalanceTo + amount;
                            String sql5 = "UPDATE customer_accounts SET account_balance = :newAccBalanceKey WHERE account_number = :accParam";
                            Map<String, Object> paraMap5 = new HashMap<>();
                            paraMap5.put("newAccBalanceKey", newAccBalanceTo);
                            paraMap5.put("accParam", toAccount);
                            jdbcTemplate.update(sql5, paraMap5);

                            String sql6 =  "INSERT INTO transaction_history (transaction_account_from, transaction_account_to, transaction_type, transaction_amount) " +
                                    "VALUES (:trans_acc_from, :trans_acc, :trans_type, :trans_amount)";
                            Map<String, Object> paraMap6 = new HashMap<>();
                            paraMap6.put("trans_acc_from", fromAccount);
                            paraMap6.put("trans_acc", toAccount);
                            paraMap6.put("trans_type", "Transfer from another account");
                            paraMap6.put("trans_amount", amount);
                            jdbcTemplate.update(sql6, paraMap6);

                        } catch (NullPointerException e) {
                            return "Receiving account not existing (Error)";
                        }
                    }
                } catch (NullPointerException e) {
                    return "There is not enough cash on the transferring account or account not existing (Error)";
                    }
            return "The balance after transactions is: \n " +
                    "for the account: " + fromAccount + ", the balance after outgoing transfer is: " + newAccBalanceFrom+"\n" +
                    "for the account: " + toAccount + ", the balance after incoming transfer is: " + newAccBalanceTo;
            }
        }
    }

