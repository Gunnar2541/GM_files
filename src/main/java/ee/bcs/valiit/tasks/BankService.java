package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public String createCustomerBody(BankCustomerData bankCustomer) {
        bankRepository.createCustomerBody(bankCustomer);
        return bankCustomer.getCustName() + " as a Bank customer has been created.";
    }

    public String createCustomerAccount(BankCustomerData bankCustomer) {
        bankRepository.createCustomerAccount(bankCustomer);
        return "New account for ID " + bankCustomer.getCustId() + " has been created.\n" +
                "This account is " + bankCustomer.getCustAccType() + ".";
    }

    public int accountBalance(String account_nr) {
        return bankRepository.accountBalance(account_nr);
    }

    // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
    public String depositMoney(String accountNrTo, Integer amount) {
        Integer newAccBalance;
        if (amount < 0)
            return "Check your amount input, cant add negative amount";
        else {
            Integer oldAccBalance = bankRepository.accountBalance(accountNrTo);
            newAccBalance = oldAccBalance + amount;
            bankRepository.updateAccountBalance(accountNrTo, newAccBalance);
            bankRepository.dataToTransHistory(null, accountNrTo, "Money deposit to account", amount);
        }
        return amount + " was deposited to account nr: " + accountNrTo + ". The new balance is " + newAccBalance;
    }

    // http://localhost:8080/bank/withdrawMoney?accountFrom=EE5555&amount=125
    public String withdrawMoney(String accountFrom, int amount) {
        Integer newAccBalanceFrom;
        if (amount <= 0)
            return "Check your amount input, cant add negative amount";
        Integer oldAccBalanceFrom = bankRepository.accountBalance(accountFrom);
        if ((oldAccBalanceFrom - amount) < 0) {
            return "There is not enough cash in the account";
        } else {
            newAccBalanceFrom = oldAccBalanceFrom - amount;
            bankRepository.updateAccountBalance(accountFrom, amount);
            bankRepository.dataToTransHistory(accountFrom, null, "Money withrawal from account", amount);
        }
        return "New balance for account nr: " + accountFrom + " is: " + newAccBalanceFrom;
    }

    // http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE3333&amount=130
    public String transferMoney(String accountFrom, String accountTo, int amount) {
        Integer newAccBalanceFrom;
        Integer newAccBalanceTo;
        if (amount <= 0)
            return "Check your amount input, cant add negative amount";
        Integer oldAccBalance = bankRepository.accountBalance(accountFrom);
        if ((oldAccBalance - amount) < 0) {
            return "There is not enough cash in the account";
        } else {
            newAccBalanceFrom = oldAccBalance - amount;
            bankRepository.updateAccountBalance(accountFrom, newAccBalanceFrom);

            Integer oldAccBalanceTo = bankRepository.accountBalance(accountTo);
            newAccBalanceTo = oldAccBalanceTo + amount;
            bankRepository.updateAccountBalance(accountTo, newAccBalanceTo);
            bankRepository.dataToTransHistory(accountFrom, accountTo, "acc to acc trancfer", amount);
        }
        return "The balance after transactions is: \n " +
                "for the account: " + accountFrom + ", the balance after outgoing transfer is: " + newAccBalanceFrom + "\n" +
                "for the account: " + accountTo + ", the balance after incoming transfer is: " + newAccBalanceTo;
    }

}
