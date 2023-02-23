package com.example.postgresql.security;

import com.example.postgresql.model.Customer;
import com.example.postgresql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<Customer> customer = customerRepository.findByUserName(userName);

       customer.orElseThrow(() -> new UsernameNotFoundException("Not found: "+ userName));

       return customer.map(MyUserDetails::new).get();
    }
}

