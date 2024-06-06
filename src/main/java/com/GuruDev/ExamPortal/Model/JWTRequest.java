package com.GuruDev.ExamPortal.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest {

    private String userName ;
    private String password ;

}
