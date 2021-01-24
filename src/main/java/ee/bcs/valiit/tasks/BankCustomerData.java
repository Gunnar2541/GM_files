package ee.bcs.valiit.tasks;

public class BankCustomerData {

    private String custId;
    private String custName;
    private String custAddress;
    private String custAccNr;
    private String custAccType;
    private int custAccBalance;
    private int transAmount;
    private String transType;


    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
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

}
