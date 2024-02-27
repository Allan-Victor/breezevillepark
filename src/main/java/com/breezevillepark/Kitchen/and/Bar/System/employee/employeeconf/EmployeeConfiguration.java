package com.breezevillepark.Kitchen.and.Bar.System.employee.employeeconf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfiguration {
    //used for setups when app starts or adding something to db as soon as app starts

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            System.out.println("Hooray");
        };
    }
}
