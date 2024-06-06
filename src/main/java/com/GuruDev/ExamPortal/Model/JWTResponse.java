package com.GuruDev.ExamPortal.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTResponse {

    private  String token ;
    private String userName ;
    private Long userId ;
    private String userRole;
}
