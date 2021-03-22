//package com.ssafy.ssokauth;
//
//import javafx.application.Application;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//
//@SpringBootApplication
//public class SsokauthApplication {
//
//    private static final String PROPERTIES = "spring.config.location=classpath:/google.yml";
//
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(Application.class).properties(PROPERTIES).run(args);
//    }
//
//}

package com.ssafy.ssokauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsokauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsokauthApplication.class, args);
    }

}
