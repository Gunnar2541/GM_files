package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public UserDetails loadUserByUsername(String cust_id) throws UsernameNotFoundException {
        String password = bankRepository.userPassword(cust_id);
        return User.withUsername(cust_id)
                .password(password)
                .roles("USER").build();
    }
}
