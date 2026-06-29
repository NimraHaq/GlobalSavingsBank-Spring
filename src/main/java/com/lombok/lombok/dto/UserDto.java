package com.lombok.lombok.dto;

import com.lombok.lombok.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


//this class could have been a record
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;

    private String username;

    private String password;

    private String role;

    private String firstName;

    private String lastName;

    private String email;


    @Builder.Default //"Y" remains the default value for this field
    private String isActive = Constants.IS_ACTIVE;

    private String chId;

    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
}
