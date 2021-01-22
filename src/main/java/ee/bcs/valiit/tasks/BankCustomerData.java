package ee.bcs.valiit.tasks;

public class BankCustomerData {

    private String custName;
    private String custId;
    private String custAcc;
    private int custAccBalance;
    private int transAmount;

    public void setCustAccBalance(int custAccBalance) {
        this.custAccBalance = custAccBalance;
    }

    public int getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(int transAmount) {
        this.transAmount = transAmount;
    }


    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustAcc() {
        return custAcc;
    }

    public void setCustAcc(String custAcc) {
        this.custAcc = custAcc;
    }

    public Integer getCustAccBalance() {
        return custAccBalance;
    }

    public void setCustAccBalance(Integer custAccBalance) {
        this.custAccBalance = custAccBalance;
    }


}
