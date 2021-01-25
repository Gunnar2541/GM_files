package ee.bcs.valiit.tasks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

        public void createCustomerBody(BankCustomerData newCustomerUusMuutuja){
        bankRepository.createCustomerBody(newCustomerUusMuutuja);
    }
        public void createCustomerAccount(BankCustomerData newAccountUusMuutuja){
        bankRepository.createCustomerAccount(newAccountUusMuutuja);
    }
        public int accountBalance(String account_nr){
        return bankRepository.accountBalance(account_nr);
    }
    // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
        public String depositMoney(String account_nr_to, Integer amount) {
            Integer newAccBalance;
            if (amount < 0)
                return "Check your amount input, cant add negative amount";
            else {
                Integer oldAccBalance = bankRepository.accountBalance(account_nr_to);
                newAccBalance = oldAccBalance + amount;
                bankRepository.updateAccountBalance(account_nr_to, newAccBalance);
            }
            return amount + " was deposited to account nr: " + account_nr_to + ". The new balance is " + newAccBalance;
        }
            /*  Tehingute ajaloo kirjutamiseks- teen hiljem.
            String sql3 =  "INSERT INTO transaction_history (transaction_account_to, transaction_type, transaction_amount) " +
                    "VALUES (:trans_acc, :trans_type, :trans_amount)";
            Map<String, Object> paraMap3 = new HashMap<>();
            paraMap3.put("trans_acc", account_nr_to);
            paraMap3.put("trans_type", "Deposit to account");
            paraMap3.put("trans_amount", amount);
            jdbcTemplate.update(sql3, paraMap3);

             */
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
            }
            return "New balance for account nr: " + accountFrom + " is: " + newAccBalanceFrom;
                /* teen hiljem
                String sql3 =  "INSERT INTO transaction_history (transaction_account_from, transaction_type, transaction_amount) " +
                        "VALUES (:trans_acc, :trans_type, :trans_amount)";
                Map<String, Object> paraMap3 = new HashMap<>();
                paraMap3.put("trans_acc", accountFrom);
                paraMap3.put("trans_type", "Withdraw money from account");
                paraMap3.put("trans_amount", amount);
                jdbcTemplate.update(sql3, paraMap3);
                */
    }
    // http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE3333&amount=130
    public String transferMoney(String accountFrom, String accountTo, int amount ) {
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
        }
        return "The balance after transactions is: \n " +
                "for the account: " + accountFrom + ", the balance after outgoing transfer is: " + newAccBalanceFrom+"\n" +
                "for the account: " + accountTo + ", the balance after incoming transfer is: " + newAccBalanceTo;
        }

}
