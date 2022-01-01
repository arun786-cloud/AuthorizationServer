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
 * <p>
 * The below class will get the user details from the database and validate
 * if the user is present. It implements Spring framework UserDetailsService.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * the details of the user is retrieved from the database, which will be user
     * by Authentication Provider
     *
     * @param username this is the unique username in this case its the email of the user.
     * @return Spring UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users userByEmail = userRepository.findByEmail(username);
        if (userByEmail == null) {
            throw new UsernameNotFoundException("User not found" + username);
        }

        return new User(userByEmail.getEmail(), userByEmail.getPassword(), userByEmail.getRoles());
    }
}
