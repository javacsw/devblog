package org.example.backend.Entity.pojp;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class User {
    private Integer id;
    private String  username,password,mail,avator;
    private Integer unReadNotiCnt;

    public User(){

    }

}
