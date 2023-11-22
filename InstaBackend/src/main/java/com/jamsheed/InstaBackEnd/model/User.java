package com.jamsheed.InstaBackEnd.model;


import com.jamsheed.InstaBackEnd.model.enums.AccountType;
import com.jamsheed.InstaBackEnd.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userHandle;
    private String userBio;

    @Email
    @Column(unique = true)
    private String userEmail;


   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$!%])[A-Za-z\\d@#$!%]{8,}$\n")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private boolean blueTick;




}
