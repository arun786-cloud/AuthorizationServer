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
                private Set<User> users;
                
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
          public class User {
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

class Diagram
![class Diagram for users and roles](https://github.com/arun786-cloud/AuthorizationServer/blob/main/src/main/resources/images/user_role.png)