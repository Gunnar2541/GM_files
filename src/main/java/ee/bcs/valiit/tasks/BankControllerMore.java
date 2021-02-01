package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("bank")
@RestController
public class BankControllerMore {

    @Autowired
    private BankService bankService;

     /*  {
    "custId":"7777",
    "custName":"Thomas Dwing",
    "custAddress":"Dingeling 7"
     }   */
    //  http://localhost:8080/bank/createCustomerB
    @PostMapping("createCustomerB")
    public String createCustomerB(@RequestBody CustomerData bankCustomer) {
        bankService.createCustomerBody(bankCustomer);
        return "Bank customer has been created.";
    }
    /*    {
    "accountCustId":"7777",
    "custAccType":"Current Account",
    "custAccNr":"EE7777"
     }   */
    //  http://localhost:8080/bank/createCustomerAccount
    @PostMapping("createCustomerAccount")
    public String createAccount(@RequestBody Accounts bankAccount) {
        bankService.createCustomerAccount(bankAccount);
        return "Account has been created";
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

    // http://localhost:8080/bank/withdrawMoney?accountFrom=EE2222&amount=125
    @PutMapping("withdrawMoney")
    public String withdrawMoney(@RequestParam String accountFrom, @RequestParam Integer amount) {
        return bankService.withdrawMoney(accountFrom, amount);
    }

    // http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE3333&amount=130
    @PutMapping("transferMoney")
    public String transferMoney(@RequestParam String accountFrom, @RequestParam String accountTo, @RequestParam int amount) {
        return bankService.transferMoney(accountFrom, accountTo, amount);
    }

    // http://localhost:8080/bank/customerList
    @GetMapping("customerList")
    public List <CustomerData> customerList() {
        return bankService.getCustomerList();
    }


    // http://localhost:8080/bank/customersAccounts
    @GetMapping("customersAccounts")
    public List <Accounts> accounts() {
        return bankService.getAccounts();
    }
}
