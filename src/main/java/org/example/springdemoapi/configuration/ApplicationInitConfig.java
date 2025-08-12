package org.example.springdemoapi.configuration;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemoapi.Entity.User;
import org.example.springdemoapi.Enum.Role.UserRole;
import org.example.springdemoapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder ;

    @Bean
    //? Application này sẽ được chạy khi Project này được chạy
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            //? check xem application khi chạy đã có admin chưa
            if(userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(UserRole.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .roles(roles)
                        .build();

                userRepository.save(user);

                log.warn("ADMIN HAS BEEN CREATED WITH DEFAULT PASSWORD: 123456");
            }

        };
    }

}
