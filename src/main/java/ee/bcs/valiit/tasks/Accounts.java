package ee.bcs.valiit.tasks;

public class Accounts {

    private int transId;
    private String accountCustId;
    private String custAccNr;
    private String custAccType;
    private int custAccBalance;
    private int transAmount;
    private String transType;
    private String transDate;
    private int balanceAfterTransaction;

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(int balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }




    public String getAccountCustId() {
        return accountCustId;
    }

    public void setAccountCustId(String accountCustId) {
        this.accountCustId = accountCustId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
    public String getCustAccNr() {
        return custAccNr;
    }

    public void setCustAccNr(String custAccNr) {
        this.custAccNr = custAccNr;
    }

    public Integer getCustAccBalance() {
        return custAccBalance;
    }

    public void setCustAccBalance(Integer custAccBalance) {
        this.custAccBalance = custAccBalance;
    }
    public String getCustAccType() {
        return custAccType;
    }

    public void setCustAccType(String custAccType) {
        this.custAccType = custAccType;
    }

    public void setCustAccBalance(int custAccBalance) {
        this.custAccBalance = custAccBalance;
    }

    public int getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(int transAmount) {
        this.transAmount = transAmount;
    }

}
