package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("bank")
@RestController
public class BankController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    HashMap<String, BigDecimal> accountMap = new HashMap<>();


    Integer accountBalance = 0;
    //BigDecimal bd = BigDecimal.valueOf(0);
    String account_nr = "";
    BigDecimal amount;
    String output = "";
    Integer outputNr;

    // http://localhost:8080/bank/createAccount?customer_id=123&account_nr=EE222&cust_name=Toomas&account_balance=0
    @PostMapping("createAccount")
    public String createAccount(@RequestParam String customer_id,
                                @RequestParam String account_nr,
                                @RequestParam String cust_name,
                                @RequestParam Integer account_balance) {
        accountMap.putIfAbsent(account_nr, BigDecimal.valueOf(0));
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

    // http://localhost:8080/bank/accountBalance?account_nr=EE999
    @GetMapping("accountBalance")
    public Integer accountBalance(@RequestParam String account_nr) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :accParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("accParam", account_nr);
        System.out.println("Balance of the account is: ");
        return jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
        //annab vastuseks account_balance
    }

    // http://localhost:8080/bank/depositMoney?account_nr=EE222&amount=500
    @PutMapping("depositMoney")
    public String depositMoney(@RequestParam String account_nr, @RequestParam Integer amount) {
        if (amount < 0)
            return "Check your amount input, cant add negative amount";
        else {
            String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :accParam";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("accParam", account_nr);
            Integer oldAccBalance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);

            Integer newAccBalance = oldAccBalance + amount;
            String sql2 = "UPDATE bank_accounts SET account_balance = :newAccBalanceKey WHERE account_nr = :accParam";
            Map<String, Object> paraMap2 = new HashMap<>();
            paraMap2.put("newAccBalanceKey", newAccBalance);
            paraMap2.put("accParam", account_nr);
            jdbcTemplate.update(sql2, paraMap2);
            output = newAccBalance.toString();
        }
        return"New account balance for "+account_nr+ " is " +output;
    }

    // http://localhost:8080/bank/withdrawMoney?account_nr=EE999&amount=250
        @PutMapping("withdrawMoney")
        public String withdrawMoney(@RequestParam String account_nr, @RequestParam int amount) {
            if (amount <= 0)
                return "Check your amount input, cant add negative amount";
            else {
                String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :accParam";
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("accParam", account_nr);
                Integer oldAccBalance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
                try {
                    if ((oldAccBalance - amount) < 0) {
                        return "There is not enough cash in the account";
                    } else {
                        Integer newAccBalance = oldAccBalance - amount;
                        String sql2 = "UPDATE bank_accounts SET account_balance = :newAccBalanceKey WHERE account_nr = :accParamKey";
                        Map<String, Object> paraMap2 = new HashMap<>();
                        paraMap2.put("newAccBalanceKey", newAccBalance);
                        paraMap2.put("accParamKey", account_nr);
                        jdbcTemplate.update(sql2, paraMap2);
                        output = newAccBalance.toString();
                        return "New balance for account nr: " + account_nr + " is: " + output;
                    }
                } catch (NullPointerException e) {
                    return "There is not enough cash in the account (Error)";
                }
            }
    }

    // http://localhost:8080/bank/transferMoney?fromAccount=EE444&toAccount=EE999&amount=125
        public String transferMoney(@PathVariable String fromAccount, @PathVariable String toAccount, @PathVariable int amount ) {
            Integer newAccBalance;
            Integer newAccBalance2;
            if (amount <= 0)
                return "Check your amount input, cant add negative amount";
            else {
                String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :accParam";
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("accParam", fromAccount);
                Integer oldAccBalance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
                try {
                    if ((oldAccBalance - amount) < 0) {
                        return "There is not enough cash in the transferer account";
                    } else {
                        newAccBalance = oldAccBalance - amount;
                        String sql2 = "UPDATE bank_accounts SET account_balance = :newAccBalanceKey WHERE account_nr = :accParamKey";
                        Map<String, Object> paraMap2 = new HashMap<>();
                        paraMap2.put("newAccBalanceKey", newAccBalance);
                        paraMap2.put("accParamKey", fromAccount);
                        jdbcTemplate.update(sql2, paraMap2);
                        output = newAccBalance.toString();
                        //return "New balance for transferer account nr: " + account_nr + " is: " + output;
                    }
                } catch (NullPointerException e) {
                    return "There is not enough cash in the account or account not existing (Error)";
                }
                String sql3 = "SELECT account_balance FROM bank_accounts WHERE account_nr = :accParam";
                Map<String, Object> paraMap3 = new HashMap<>();
                paraMap3.put("accParam", toAccount);
                Integer oldAccBalance2 = jdbcTemplate.queryForObject(sql3, paraMap3, Integer.class);
                try {
                    newAccBalance2 = oldAccBalance2 + amount;
                    String sql4 = "UPDATE bank_accounts SET account_balance = :newAccBalanceKey WHERE account_nr = :accParamKey";
                    Map<String, Object> paraMap4 = new HashMap<>();
                    paraMap4.put("newAccBalanceKey", newAccBalance2);
                    paraMap4.put("accParamKey", toAccount);
                    jdbcTemplate.update(sql4, paraMap4);
                    output = newAccBalance2.toString();
                    //return "New balance for account nr: " + toAccount + " is: " + output;
                } catch (NullPointerException e) {
                    return "Receiving account not existing (Error)";
                }
            }
            return "The balance after transactions is: \n " +
                    "for the account: " + fromAccount + ", the balance is: " + newAccBalance+
                    "\nfor the account: " + toAccount + ", the balance is: " + newAccBalance2;
        }
    }

