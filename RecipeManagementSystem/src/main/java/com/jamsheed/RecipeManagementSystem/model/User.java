package com.jamsheed.RecipeManagementSystem.model;
import com.jamsheed.RecipeManagementSystem.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Pattern(regexp = "^[A-Z][a-z A-Z]*")
    private String firstName;
    @Pattern(regexp = "^[A-Z][a-z A-Z]*")
    private String lastName;

    @Column(unique = true)
    @Email
    private String userEmail;



   // @Pattern(regexp = " ^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!*_])[A-Za-z\\d@#$%^&+=!*_]{8,}$")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Gender userGender;

    @Past
    private LocalDate userDOB;


    @Pattern(regexp = "^\\+91\\d{10}$")
    private String userPhoneNumber;

}
