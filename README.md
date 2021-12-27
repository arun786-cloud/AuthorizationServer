# OAuth2.0 Authorization Server

## Spring security

Add the below dependency in build.gradle

    implementation 'org.springframework.boot:spring-boot-starter-security:2.6.1'

Create 2 entities, Roles and Users, the relationship between the 2 are many to many

        import lombok.Getter;
        import lombok.Setter;
        import org.springframework.security.core.GrantedAuthority;
        
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.ManyToMany;
        import javax.persistence.Table;
        import java.util.Set;

          @Entity
          @Table(name = "role")
          @Getter
          @Setter
          public class Role implements GrantedAuthority {
                @Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                private long id;
                private String name;
                
                @ManyToMany(mappedBy = "roles")
                private Set<Users> users;
                
                @Override
                public String getAuthority() {
                    return name;
                }
          }


        import lombok.Getter;
        import lombok.Setter;
        
        import javax.persistence.Entity;
        import javax.persistence.FetchType;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.JoinColumn;
        import javax.persistence.JoinTable;
        import javax.persistence.ManyToMany;
        import javax.persistence.Table;
        import java.util.Set;

          @Entity
          @Table(name = "User")
          @Getter
          @Setter
          public class Users {
              @Id
              @GeneratedValue(strategy = GenerationType.AUTO)
              private Long id;
              private String firstName;
              private String lastName;
              private String email;
              private String password;
            
              @ManyToMany(fetch = FetchType.EAGER)
              @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
              private Set<Role> roles;
          }

Class Diagram
![class Diagram for users and roles](https://github.com/arun786-cloud/AuthorizationServer/blob/main/src/main/resources/images/user_role.png)

Important classes to be configured in Spring Security, we implement the UserDetailsService interface to get the details of user from the DB.

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
