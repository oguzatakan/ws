package com.hoaxify.ws;

import com.hoaxify.ws.hoax.Hoax;
import com.hoaxify.ws.hoax.HoaxService;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import com.hoaxify.ws.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    @Bean
    CommandLineRunner createInitialUsers(UserService userService, HoaxService hoaxService) {
        return (args) -> {
                for(int i = 1; i<=25; i++){
                    User user = new User();
                    user.setUsername("user"+i);
                    user.setDisplayName("display"+i);
                    user.setPassword("P4ssword");
                    userService.save(user);
                    for (int j = 1; j<=2; j++) {
                        Hoax hoax = new Hoax();
                        hoax.setContent("hoax ( " +j + " ) from user (" +i+")");
                        hoaxService.save(hoax,user);
                    }
                }

            };
    }

}
