package org.example.springdemoapi.Repository;

import lombok.Data;
import org.example.springdemoapi.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRespository extends JpaRepository<Role, String> {
}
