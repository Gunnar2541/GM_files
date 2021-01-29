package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BankService {
    Date todaysDate = new Date();
    DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String strDate = df2.format(todaysDate);


    @Autowired
    private BankRepository bankRepository;

    @Transactional
    public String createCustomerBody(CustomerData bankCustomer) {
        bankRepository.createCustomerBody(bankCustomer);
        return bankCustomer.getCustName() + " as a Bank customer has been created.";
    }
    /*    {
    "accountCustId":"7777",
    "custAccType":"Current Account",
    "custAccNr":"EE7777"
     }   */
    //  http://localhost:8080/bank/createCustomerAccount
    @Transactional
    public String createCustomerAccount(Accounts bankAccount) {
        bankRepository.createCustomerAccount(bankAccount);
        return "New account for ID " + bankAccount.getAccountCustId() + " has been created.\n" +
                "This account is " + bankAccount.getCustAccType() + ".";
    }

    // http://localhost:8080/bank/accountBalance?account_nr=EE6666
    public int accountBalance(String account_nr) {
            return bankRepository.accountBalance(account_nr);
    }

    // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
    @Transactional
    public String depositMoney(String accountNrTo, Integer amount) {
        int newAccBalanceTo;
        if (amount < 0)
            throw new MyException("Check your amount input, cant add negative amount");
        else {
            int oldAccBalance = bankRepository.accountBalance(accountNrTo);
            newAccBalanceTo = oldAccBalance + amount;
            bankRepository.updateAccountBalance(accountNrTo, newAccBalanceTo);
            bankRepository.dataToTransHistory(null, accountNrTo, "Money deposit to account", amount, newAccBalanceTo, strDate);
        }
        return amount + " was deposited to account nr: " + accountNrTo + ". The new balance is " + newAccBalanceTo;
    }

    // http://localhost:8080/bank/withdrawMoney?accountFrom=EE5555&amount=250
    @Transactional
    public String withdrawMoney(String accountFrom, Integer amount) {
        int newAccBalanceFrom;
        if (amount <= 0) {
            throw new MyException("Check your amount input, cant add negative amount");
        }
        int oldAccBalanceFrom = bankRepository.accountBalance(accountFrom);
        if ((oldAccBalanceFrom - amount) < 0) {
            throw new MyException("There is not enough cash in the account");
        } else {
            newAccBalanceFrom = oldAccBalanceFrom - amount;
            bankRepository.updateAccountBalance(accountFrom, newAccBalanceFrom);
            bankRepository.dataToTransHistory(accountFrom, null, "Money withrawal from account", -amount, newAccBalanceFrom, strDate);
        }
        return "New balance for account nr: " + accountFrom + " is: " + newAccBalanceFrom;
    }

    // http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE3333&amount=130
    @Transactional
    public String transferMoney(String accountFrom, String accountTo, int amount) {
        int newAccBalanceFrom;
        int newAccBalanceTo;
        if (amount <= 0)
            throw new MyException("Check your amount input, cant add negative amount");
        int oldAccBalance = bankRepository.accountBalance(accountFrom);
        if ((oldAccBalance - amount) < 0) {
            throw new MyException("There is not enough cash in the account");
        } else {
            newAccBalanceFrom = oldAccBalance - amount;
            bankRepository.updateAccountBalance(accountFrom, newAccBalanceFrom);
            bankRepository.dataToTransHistory(accountFrom, accountTo, "FROM acc to acc transfer", -amount, newAccBalanceFrom, strDate);
            int oldAccBalanceTo = bankRepository.accountBalance(accountTo);
            newAccBalanceTo = oldAccBalanceTo + amount;
            bankRepository.updateAccountBalance(accountTo, newAccBalanceTo);
            bankRepository.dataToTransHistory(accountFrom, accountTo, "TO acc to acc transfer", amount, newAccBalanceTo, strDate);
        }
        return "The balance after transactions is: \n " +
                "for the account: " + accountFrom + ", the balance after outgoing transfer is: " + newAccBalanceFrom + "\n" +
                "for the account: " + accountTo + ", the balance after incoming transfer is: " + newAccBalanceTo;
    }
    //http://localhost:8080/bank/
    public List<CustomerData> getCustomerList() {
        return bankRepository.bankCustomers();
    }

    public List<Accounts> getAccounts() {
        return bankRepository.bankAccounts();
    }



}
