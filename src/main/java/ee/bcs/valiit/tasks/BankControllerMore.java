package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("bank")
@RestController
public class BankControllerMore {

    @Autowired
    private BankService bankService;

    @PostMapping("createCustomerB")
    public void createCustomerB(@RequestBody CustomerData bankCustomer) {
        bankService.createCustomerBody(bankCustomer);
    }

    //  http://localhost:8080/bank/createCustomerAccount
    @PostMapping("createCustomerAccount")
    public void createAccount(@RequestBody Accounts bankAccount) {
        bankService.createCustomerAccount(bankAccount);
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
