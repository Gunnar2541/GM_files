/* TAGAVARA VERSIOON, EI TEA, KAS TÖÖTAB.

package ee.bcs.valiit.tasks.controller;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;


@RequestMapping("bank/command")
@RestController

public class BankControlPostman {
    // Store account nr as a key and account balance as value
    static HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();

    BigDecimal accountBalance = BigDecimal.valueOf(0);
    BigDecimal bd = BigDecimal.valueOf(0);
    String output = "";
    BigDecimal temp= BigDecimal.valueOf(0);


    // http://localhost:8080/bank/command/quit
    @GetMapping("quit")
    public String quit(@PathVariable String z) {
        output = "You cant really quit! Unless you close your browser!";
        return output;
    }
//createAccount with 0 balance
//http://localhost:8080/bank/command/createAccount/EE123
//http://localhost:8080/bank/command/createAccount/EE456

    @GetMapping("createAccount/{accNr}")
    public String createAccount(@PathVariable String accNr ){
        accountBalanceMap.putIfAbsent(accNr, BigDecimal.valueOf(0));
        return  "Account number has been set to: " +accNr+ ", with amount of 0.00 .";
    }
    //getBalance has to display account balance of specific account
//http://localhost:8080/bank/command/getBalance/EE123
//http://localhost:8080/bank/command/getBalance/EE456
    @GetMapping("getBalance/{accNr}")
    public String getBalance(@PathVariable String accNr ) {
        try {
            if (accountBalanceMap.get(accNr).equals(BigDecimal.valueOf(0)))
                return "Account number does not exist";
            else
                return "Account balance for acc " + accNr + " is " + accountBalanceMap.get(accNr);
        } catch (NullPointerException e) {
            return "Account number does not exist";
        }
    }
    // depositMoney has to add specified amount of money to account and check that amount is positive number
//http://localhost:8080/bank/command/depositMoney/EE123/500
//http://localhost:8080/bank/command/depositMoney/EE456/100
    @GetMapping("depositMoney/{accNr}/{amount}")
    public String depositMoney(@PathVariable String accNr, @PathVariable int amount ){
        if (amount < 0)
            return "Check your amount input, cant add negative amount";
        bd = new BigDecimal(amount);
        try {
            if (accountBalanceMap.get(accNr).equals(0)) //Kuidas see õigesti teha???
                output= "Account number does not exist";
            else {
                accountBalance = accountBalanceMap.get(accNr).add(bd);
                accountBalanceMap.replace(accNr, accountBalance);
                return "New balance for account: " + accNr + " is: " + accountBalanceMap.get(accNr);
            }
        } catch (NullPointerException e) {
            output= "Account number does not exist";
        }return output;
    }
    //withdrawMoney, has to remove specified amount of money from account
//check that amount is positive number, transaction not good if account balance would become negative
//http://localhost:8080/bank/command/withdrawMoney/EE123/150
//http://localhost:8080/bank/command/withdrawMoney/EE456/75
    @GetMapping("withdrawMoney/{accNrFrom}/{amount}")
    public String withdrawMoney(@PathVariable String accNrFrom, @PathVariable int amount ) {
        if (amount <= 0)
            return "Check your amount entry, must be positive nr";
        try {
            if (accountBalanceMap.get(accNrFrom).equals(BigDecimal.valueOf(0)))
                output= "Account number does not exist";
            bd = new BigDecimal(amount);
            if ((accountBalanceMap.get(accNrFrom).subtract(bd)).compareTo(BigDecimal.valueOf(0)) < 0) {
                return "There is not enough cash in the account";
            } else {
                accountBalance = accountBalanceMap.get(accNrFrom).subtract(bd);
                accountBalanceMap.replace(accNrFrom, accountBalance);
                return "New balance for account nr: " + accNrFrom + " is: " + accountBalanceMap.get(accNrFrom);
            }
        } catch (NullPointerException e) {
            output= "Account numbers does not exist";
        }
        return output;
    }

    //"transfer- has to remove specified amount from fromAccount and add it to toAccount
// Your application needs to check that toAccount is positive, check that account has enough money to do that transaction
//http://localhost:8080/bank/command/transfer/EE123/EE456/250
//http://localhost:8080/bank/command/transfer/EE123/EE456/750
    @GetMapping("transfer/{accNrFrom}/{accNrTo}/{amount}")
    public String transfer(@PathVariable String accNrFrom, @PathVariable String accNrTo, @PathVariable int amount ) {
        if (amount < 0)
            return "The amount must be positive number";
        bd = new BigDecimal(amount);
        try {
            if ((BigDecimal.valueOf(0).compareTo(accountBalanceMap.get(accNrFrom))<=0)||   // Ei ole õige lahendus!
                    (BigDecimal.valueOf(0).compareTo(accountBalanceMap.get(accNrTo)))<=0){
                return "account nr does not exist";
            } else if ((accountBalanceMap.get(accNrFrom).subtract(bd)).compareTo(BigDecimal.valueOf(0)) < 0){
                return  "There is not enough cash in the account";
            } else {
                accountBalance = accountBalanceMap.get(accNrFrom).subtract(bd);
                accountBalanceMap.replace(accNrFrom, accountBalance);
                accountBalance = accountBalanceMap.get(accNrTo).add(bd);
                accountBalanceMap.replace(accNrTo, accountBalance);
                return  "New balance for " + accNrFrom + "  account is: " + accountBalanceMap.get(accNrFrom) +
                        "New balance for " + accNrTo + "  account is: " + accountBalanceMap.get(accNrTo);
            }
        } catch (NullPointerException e) {
            output = "One or both of the account numbers do not exist";
        }
        return output;
    }
}


 */