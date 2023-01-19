package com.example.springstudy;

import com.example.springstudy.config.SwaggerConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;

@Import({
        SwaggerConfiguration.class
})
@SpringBootApplication
public class SpringStudyApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringStudyApplication.class, args);
    }


}
