// PROOVIN LISADA ROHKEM FUNKTSIOONE

package ee.bcs.valiit.tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("bank")
@RestController
public class BankControllerMore {

    @Autowired
    private BankService bankService;

    @PostMapping("createCustomerBody")
    public String createCustomerBody(@RequestBody BankCustomerData bankCustomer) {
        bankService.createCustomerBody(bankCustomer);
        return bankCustomer.getCustName() + " as a Bank customer has been created.";
    }

    //  http://localhost:8080/bank/createCustomerAccount
    @PostMapping("createCustomerAccount")
    public String createAccount(@RequestBody BankCustomerData bankCustomer) {
        bankService.createCustomerAccount(bankCustomer);
        return "New account for ID " + bankCustomer.getCustId() + " has been created.\n" +
                "This account is " + bankCustomer.getCustAccType() + ".";
    }

    // http://localhost:8080/bank/accountBalance?account_nr=EE6666
    @GetMapping("accountBalance")
    public String accountBalance(@RequestParam String account_nr) {
        return "Balance of the account nr: " + account_nr + ", is:"
                + bankService.accountBalance(account_nr);
    }

    // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
    @PutMapping("depositMoney")
    public String depositMoney(@RequestParam String account_nr_to, @RequestParam Integer amount) {
        return bankService.depositMoney(account_nr_to, amount);
    }

    // http://localhost:8080/bank/withdrawMoney?account_from=EE2222&amount=125
    @PutMapping("withdrawMoney")
    public String withdrawMoney(@RequestParam String accountFrom, @RequestParam int amount) {
        return bankService.withdrawMoney(accountFrom, amount);
    }
    // http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE3333&amount=130
    @PutMapping("transferMoney")
    public String transferMoney(@RequestParam String accountFrom, @RequestParam String accountTo, @RequestParam int amount ) {
        return bankService.transferMoney(accountFrom, accountTo, amount);
    }
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
