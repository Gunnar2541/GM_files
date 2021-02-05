package ee.bcs.valiit.tasks;

public class CustomerData {

    private String custId;
    private String custName;
    private String custAddress;
    private String custPwCoded;
    private String custPwUncoded;

    public String getCustPwCoded() {
        return custPwCoded;
    }

    public void setCustPwCoded(String custPwCoded) {
        this.custPwCoded = custPwCoded;
    }

    public String getCustPwUncoded() {
        return custPwUncoded;
    }

    public void setCustPwUncoded(String custPwUncoded) {
        this.custPwUncoded = custPwUncoded;
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

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

}

