// Create this file: src/main/java/com/keydraft/reporting_software/security/repository/RoleRepository.java
package com.keydraft.reporting_software.security.repository;

import com.keydraft.reporting_software.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
}