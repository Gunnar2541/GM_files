package ee.bcs.valiit.tasks;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void createCustomerBody(CustomerData bankCustomer) {
        String sql = "INSERT INTO customers (customers_id, customers_address, customers_name, customers_pw_uncoded, customers_pw_coded) " +
                "VALUES (:cust_id, :customer_addressKey, :cust_nameKey, :cust_pw_uncoded, :cust_pw_coded)";
        String codedPassword = passwordEncoder.encode(bankCustomer.getCustPwUncoded());
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("cust_id", bankCustomer.getCustId());
        paraMap.put("cust_nameKey", bankCustomer.getCustName());
        paraMap.put("customer_addressKey", bankCustomer.getCustAddress());
        paraMap.put("cust_pw_uncoded", bankCustomer.getCustPwUncoded());
        paraMap.put("cust_pw_coded", codedPassword);
        jdbcTemplate.update(sql, paraMap);
    }

    public void createCustomerAccount(Accounts bankAccount) {
        String sql = "INSERT INTO accounts (accounts_cust_id, accounts_type, accounts_number, accounts_balance) " +
                "VALUES (:acc_cust_id, :acc_type, :acc_nr, :acc_balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("acc_cust_id", bankAccount.getAccountCustId());
        paraMap.put("acc_type", bankAccount.getCustAccType());
        paraMap.put("acc_nr", bankAccount.getCustAccNr());
        paraMap.put("acc_balance", 0);
        jdbcTemplate.update(sql, paraMap);
    }

    // http://localhost:8080/bank/accountBalance?account_nr=EE6666
    public int accountBalance(@RequestParam String account_nr) {
        try {
            String sql = "SELECT accounts_balance FROM accounts WHERE accounts_number = :accParam";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("accParam", account_nr);
            return jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            throw new MyException("Account not existing. Your error!");
        }
    }

    // http://localhost:8080/bank/userPassword?cust_id=1234
    //https://bcrypt-generator.com/
    public String userPassword(@RequestParam String cust_id) {
        try {
            String sql = "SELECT customers_pw_coded FROM customers WHERE customers_id = :custId";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("custId", cust_id);
            return jdbcTemplate.queryForObject(sql, paraMap, String.class);
        } catch (EmptyResultDataAccessException e) {
            throw new MyException("Wrong entry. Your error!");
        }
    }

    public int updateAccountBalance(@RequestParam String account_nr_to, @RequestParam Integer newAccBalance) {
        String sql2 = "UPDATE accounts SET accounts_balance = :newAccBalanceKey WHERE accounts_number = :accParam";
        Map<String, Object> paraMap2 = new HashMap<>();
        paraMap2.put("newAccBalanceKey", newAccBalance);
        paraMap2.put("accParam", account_nr_to);
        return jdbcTemplate.update(sql2, paraMap2);
    }

    public void dataToTransHistory(@RequestParam String accountFrom,
                                   @RequestParam String accountTo,
                                   @RequestParam String transType,
                                   @RequestParam Integer transAmount,
                                   @RequestParam Integer accBalanceAfterTransaction,
                                   @RequestParam String dateTime) {

        String sql3 = "INSERT INTO transaction_history (transaction_history_account_from, " +
                "transaction_history_account_to, transaction_history_type, transaction_history_amount, " +
                "transaction_history_balance_after_transaction, transaction_history_date_time) " +
                "VALUES (:trans_acc_from, :trans_acc_to, :trans_type, :trans_amount, :trans_end_balance, :trans_date)";
        Map<String, Object> paraMap3 = new HashMap<>();
        paraMap3.put("trans_acc_from", accountFrom);
        paraMap3.put("trans_acc_to", accountTo);
        paraMap3.put("trans_type", transType);
        paraMap3.put("trans_amount", transAmount);
        paraMap3.put("trans_end_balance", accBalanceAfterTransaction);
        paraMap3.put("trans_date", dateTime);
        jdbcTemplate.update(sql3, paraMap3);
    }

    public List<CustomerData> bankCustomers() {
        String sql = "SELECT * FROM customers";
        List result = jdbcTemplate.query(sql, new HashMap<>(), new bankClientsRowMapper());
        return result;
    }

    private class bankClientsRowMapper implements RowMapper<CustomerData> {
        @Override
        public CustomerData mapRow(ResultSet resultSet, int i) throws SQLException {
            CustomerData clients = new CustomerData();
            clients.setCustId((resultSet.getString("customers_id")));
            clients.setCustName((resultSet.getString("customers_name")));
            clients.setCustAddress((resultSet.getString("customers_address")));
            return clients;
        }
    }

    public List<Accounts> bankAccounts() {
        String sql = "SELECT * FROM accounts";
        List result = jdbcTemplate.query(sql, new HashMap<>(), new customerAccountsRowMapper());
        return result;
    }

    private class customerAccountsRowMapper implements RowMapper<Accounts> {
        @Override
        public Accounts mapRow(ResultSet resultSet, int i) throws SQLException {
            Accounts accounts = new Accounts();
            accounts.setAccountCustId((resultSet.getString("accounts_cust_id")));
            accounts.setCustAccType((resultSet.getString("accounts_type")));
            accounts.setCustAccNr((resultSet.getString("accounts_number")));
            accounts.setCustAccBalance((resultSet.getInt("accounts_balance")));
            return accounts;
        }
    }

    public List<Accounts> transactionHistory() {
        String sql = "SELECT * FROM transaction_history";
        List result = jdbcTemplate.query(sql, new HashMap<>(), new transactionHistoryRowMapper());
        return result;
    }

    private class transactionHistoryRowMapper implements RowMapper<Accounts> {
        @Override
        public Accounts mapRow(ResultSet resultSet, int i) throws SQLException {
            Accounts transHistory = new Accounts();
            transHistory.setTransId((resultSet.getInt("transaction_history_id")));
            transHistory.setTransAmount((resultSet.getInt("transaction_history_amount")));
            if (resultSet.getInt("transaction_history_amount")<0)
                transHistory.setCustAccNr((resultSet.getString("transaction_history_account_from")));
            else
                transHistory.setCustAccNr((resultSet.getString("transaction_history_account_to")));
            transHistory.setTransType((resultSet.getString("transaction_history_type")));

            transHistory.setBalanceAfterTransaction((resultSet.getInt("transaction_history_balance_after_transaction")));
            transHistory.setTransDate((resultSet.getString("transaction_history_date_time")));
            return transHistory;
        }
    }

    public List<Accounts> customerTransactionHistory(String custAccNr) {
        String sql = "SELECT * FROM transaction_history WHERE transaction_history_account_from = :accParam " +
                "AND transaction_history_amount<0 OR transaction_history_account_to = :accParam AND transaction_history_amount>0";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accParam", custAccNr);
        List <Accounts> result = jdbcTemplate.query(sql, paramMap, new AccountsRowMapper());
        return result;
    }

    private class AccountsRowMapper implements RowMapper<Accounts> {
        @Override
        public Accounts mapRow(ResultSet resultSet, int i) throws SQLException {
            Accounts custTransHistory = new Accounts();
            custTransHistory.setTransId((resultSet.getInt("transaction_history_id")));
            custTransHistory.setTransAmount((resultSet.getInt("transaction_history_amount")));
            if (resultSet.getInt("transaction_history_amount")<0)
                custTransHistory.setCustAccNr((resultSet.getString("transaction_history_account_from")));
            else
                custTransHistory.setCustAccNr((resultSet.getString("transaction_history_account_to")));
            custTransHistory.setTransType((resultSet.getString("transaction_history_type")));

            custTransHistory.setBalanceAfterTransaction((resultSet.getInt("transaction_history_balance_after_transaction")));
            custTransHistory.setTransDate((resultSet.getString("transaction_history_date_time")));
            return custTransHistory;
        }
    }
}
