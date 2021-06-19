package com.tomlott.bookshop.user.registration;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String email;
    private final String password;

    public RegistrationRequest(String firstName, String email, String password){
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

}