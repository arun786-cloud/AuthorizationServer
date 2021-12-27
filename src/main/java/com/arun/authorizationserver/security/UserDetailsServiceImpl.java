package com.arun.authorizationserver.security;

import com.arun.authorizationserver.entity.Users;
import com.arun.authorizationserver.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author arun on 12/26/21
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users userByEmail = userRepository.findByEmail(username);
        if (userByEmail == null) {
            throw new UsernameNotFoundException("User not found" + username);
        }

        return new User(userByEmail.getEmail(), userByEmail.getPassword(), userByEmail.getRoles());
    }
}
