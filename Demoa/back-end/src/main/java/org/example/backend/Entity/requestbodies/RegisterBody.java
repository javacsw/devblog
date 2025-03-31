package org.example.backend.Entity.requestbodies;

import lombok.Data;

@Data
public class RegisterBody {
    private String username,password,email;

    public RegisterBody(String username,String password,String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }
}
