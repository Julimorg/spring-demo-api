package org.example.springdemoapi.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.springdemoapi.Entity.Role;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResGetUser {
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
     Set<RoleResponse> roles;
}
