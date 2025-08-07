package org.example.springdemoapi.Dto.Request;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
     String username;
     String firstName;
     String lastName;
     LocalDate dob;
}
