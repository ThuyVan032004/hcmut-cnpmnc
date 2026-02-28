package vn.edu.hcmut.cse.adse.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StudentManagementApplication.class);
        app.run(args);
    }
}