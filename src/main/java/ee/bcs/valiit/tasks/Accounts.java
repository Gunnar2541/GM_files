package ee.bcs.valiit.tasks;

public class Accounts {


    private String accountCustId;
    private String custAccNr;
    private String custAccType;
    private int custAccBalance;
    private int transAmount;
    private String transType;


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
