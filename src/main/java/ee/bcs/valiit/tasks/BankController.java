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


    BigDecimal accountBalance = BigDecimal.valueOf(0);
    BigDecimal bd = BigDecimal.valueOf(0);
    String account_nr = "";
    BigDecimal amount;

    // http://localhost:8080/bank/createAccount?customer_id=123&account_nr=EE222&cust_name=Toomas&account_balance=0
    @PostMapping ("createAccount")
    public String createAccount (@RequestParam String customer_id,
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
        return account_nr+ " for "+cust_name+" has been created.";
    }

    // http://localhost:8080/bank/accountBalance?account_nr=EE222
    @GetMapping("accountBalance")
    public BigDecimal accountBalance(@RequestParam String account_nr) {
        try {
            if ((BigDecimal.valueOf(0).compareTo(accountMap.get(account_nr)) <= 0))
                return accountMap.get(account_nr);
        } catch (NullPointerException e) {

        }return BigDecimal.ZERO;
    }

    // http://localhost:8080/bank/depositMoney?account_nr=EE222&amount=500
    @PutMapping("depositMoney")
    public String depositMoney(@RequestParam String account_nr, @RequestParam Integer amount) {
        if (amount < 0)
            return "Check your amount input, cant add negative amount";
        bd = new BigDecimal(amount);
        //try {
            //if ((BigDecimal.valueOf(0).compareTo(accountMap.get(account_nr)) <= 0)) {
                accountBalance = accountMap.get(account_nr).add(bd);
                Integer account_balance = accountBalance.intValue();

                accountMap.replace(account_nr, accountBalance);
                String sql = "UPDATE bank_accounts SET account_balance = account_balance WHERE account_nr = account_nr";
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("account_balance", account_balance);
                jdbcTemplate.update(sql, paraMap);
                return "New balance for account: " + account_nr + " is: " + accountMap.get(account_nr);
            //}
        //} catch (NullPointerException e) {
            //return "Account number does not exist-error";
       // }//return "Account number does not exist";
    }
    // http://localhost:8080/bank/withdrawMoney?accountNr=EE123&amount=12
        @PutMapping("withdrawMoney")
        public void withdrawMoney(@RequestParam String accounNr, @RequestParam int amount) {
            if (amount <= 0)
                System.out.println("Check your amount entry, must be positive nr");
            try {
                if ((BigDecimal.valueOf(0).compareTo(accountMap.get(account_nr))<=0)){
                    bd = new BigDecimal(amount);
                    if ((accountMap.get(account_nr).subtract(bd)).compareTo(BigDecimal.valueOf(0)) < 0) {
                        System.out.println("There is not enough cash in the account");
                    } else {
                        accountBalance = accountMap.get(account_nr).subtract(bd);
                        accountMap.replace(account_nr, accountBalance);
                        System.out.println("New balance for account nr: " + account_nr + " is: " + accountMap.get(account_nr));
                    }}
            } catch (NullPointerException e) {
                System.out.println("Account numbers does not exist");
            }
            //return output;
        }
    // http://localhost:8080/bank/transferMoney?fromAccount=EE123&toAccount=EE124&amount=12
            public void transferMoney(@PathVariable String fromAccount, @PathVariable String toAccount, @PathVariable int amount ) {
                if (amount < 0)
                    System.out.println("The amount must be positive number");
                bd = new BigDecimal(amount);
                try {
                    if ((BigDecimal.valueOf(0).compareTo(accountMap.get(fromAccount))<=0)&&
                            (BigDecimal.valueOf(0).compareTo(accountMap.get(toAccount)))<=0){
                        if ((accountMap.get(fromAccount).subtract(bd)).compareTo(BigDecimal.valueOf(0)) < 0)
                            System.out.println("There is not enough cash in the account");
                        else{
                            accountBalance = accountMap.get(fromAccount).subtract(bd);
                            accountMap.replace(fromAccount, accountBalance);
                            accountBalance = accountMap.get(toAccount).add(bd);
                            accountMap.replace(toAccount, accountBalance);
                            System.out.println( "New balance for " + fromAccount + "  account is: " + accountMap.get(fromAccount)+". " +
                                "New balance for " + toAccount + "  account is: " + accountMap.get(toAccount));
                        }}
                } catch (NullPointerException e) {
                    System.out.println("One or both of the account numbers do not exist");
                }
                //return output;
            }
}
