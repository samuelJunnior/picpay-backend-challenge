package br.com.samueljunnior;

import br.com.samueljunnior.core.LogStartApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final var context = SpringApplication.run(Application.class, args);

        LogStartApplication.showLogStartApplication(context);
    }
}
