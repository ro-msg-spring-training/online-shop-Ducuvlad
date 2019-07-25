package ro.msg.learning.shop.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.UserDetailsDTO;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
    private CustomerRepository customerRepository;

    public UserDetailService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<UserDetailsDTO> findAll() {
        return  customerRepository.findAll().stream()
                .map(c->new UserDetailsDTO(c.getUsername(),c.getPassword()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        Optional<Customer> customer=customerRepository.findCustomerByUsername(username);
        Customer user=customer.get(); //todo throw some exceptions over here
        //UserDetails newUser=User.builder().username(user.getUsername()).password(user.getPassword()).roles("user").build();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoder.encode(user.getPassword()), getGrantedAuthorities(user));
        //return newUser;
    }
    private Collection<GrantedAuthority> getGrantedAuthorities(Customer user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }
}
