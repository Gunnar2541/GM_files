package ee.bcs.valiit.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import javax.transaction.Transactional;


@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BankControllerMoreTest {

    @Autowired
    private MockMvc mockMvc;

    @Test  //ok
    void createCustomerB() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CustomerData customer = new CustomerData();
        customer.setCustId("7777");
        customer.setCustName("Ziiben Seven");
        customer.setCustAddress("Vilgu 5");
        //  http://localhost:8080/bank/createCustomerB
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/createCustomerB")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test  //ok
    void createAccount() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Accounts account = new Accounts();
        account.setCustAccNr("EE1111d");
        account.setAccountCustId("1111");
        account.setCustAccType("Deposit Account");
        account.setCustAccBalance(0);
        //  http://localhost:8080/bank/createCustomerAccount
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/createCustomerAccount")
                .contentType("application/json")
                .content(mapper.writeValueAsString(account)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test  //ok
    void accountBalance() throws Exception {
        // http://localhost:8080/bank/accountBalance?account_nr=EE2222
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/accountBalance?account_nr=EE4444")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test  //ok
    void depositMoney() throws Exception  {
        // http://localhost:8080/bank/depositMoney?account_nr_to=EE2222&amount=500
        mockMvc.perform(MockMvcRequestBuilders.put("/bank/depositMoney?account_nr_to=EE2222&amount=500")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void withdrawMoney() throws Exception {
        // http://localhost:8080/bank/withdrawMoney?accountFrom=EE2222&amount=125
        mockMvc.perform(MockMvcRequestBuilders.put("/bank/withdrawMoney?accountFrom=EE2222&amount=250")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void transferMoney() throws Exception {
        //http://localhost:8080/bank/transferMoney?accountFrom=EE2222&accountTo=EE1111&amount=275
        mockMvc.perform(MockMvcRequestBuilders.put("/bank/transferMoney?accountFrom=EE2222&accountTo=EE1111&amount=275")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}