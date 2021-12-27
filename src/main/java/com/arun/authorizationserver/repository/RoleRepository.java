package com.arun.authorizationserver.repository;

import com.arun.authorizationserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author arun on 12/26/21
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
