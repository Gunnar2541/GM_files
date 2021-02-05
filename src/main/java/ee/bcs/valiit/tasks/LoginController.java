package ee.bcs.valiit.tasks;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


//@GetMapping("accountBalance")
//public String accountBalance(@RequestParam String account_nr) {
//        return "Balance of the account nr: " + account_nr + ", is:"
//        + bankService.accountBalance(account_nr);  }

@RestController
public class LoginController {
    //http://localhost:8080/public/bank/login?custId=1234&password=one
    @CrossOrigin
    @GetMapping("public/bank/login")
    public String login(@RequestParam String custId, @RequestParam String password){
        Date now = new Date();
        Date expiration = new Date(now.getTime()+1000*60*60);  //ms, sekund, minut
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setIssuer("test student")
                .signWith(SignatureAlgorithm.HS256, "c2VjcmV8") // päriselus keeruline püsiparool
                .claim("userName", "admin"); //admin asemele get meetod, et info saada.
        return builder.compact();
    }

    //On seda vaja?
    @Autowired
    private PasswordEncoder passwordEncoder;
    private BankService bankService;

    public boolean validate(String userName, String rawPassword){
        String encodedPassword = bankService.userPassword(userName); //from DB by username
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
//    public void savePassword(String password){     //seda pole mul vist vaja, juba tuleb baasi
//        String encodedPassword = passwordEncoder.encode(password); }


}
