package uz.pdp.networkcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NetworkCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkCompanyApplication.class, args);
    }

}
