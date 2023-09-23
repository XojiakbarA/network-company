package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.repository.ClientRepository;
import uz.pdp.networkcompany.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByUsername(username);
        if (client.isEmpty()) {
            return employeeRepository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("User with username = " + username + " not found")
            );
        }
        return client.get();
    }
}
