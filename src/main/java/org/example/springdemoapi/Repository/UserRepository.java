package org.example.springdemoapi.Repository;

import org.example.springdemoapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //? Function nay check user da ton tai trong DB thong qua username
    boolean existsByUsername(String username);
}
