package com.GuruDev.ExamPortal.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GuruDev.ExamPortal.Payloads.ApiResponse;

@RestController
public class hello {

    @GetMapping("/")
    public ResponseEntity<ApiResponse> helloFunc() {
        return new ResponseEntity<>(new ApiResponse("This is the Exam Portal Application", "200"), HttpStatus.OK);
    }
}