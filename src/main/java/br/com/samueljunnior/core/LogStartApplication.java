package br.com.samueljunnior.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class LogStartApplication{
    public static void showLogStartApplication(ConfigurableApplicationContext context){
        final var env = context.getEnvironment();
        String serverPort = env.getProperty("server.port");
        String applicationName = env.getProperty("spring.application.name");
        log.info("\n\n***\n\tAplicação {} iniciada com sucesso!\n\tSwagger: http://localhost:{}/swagger-ui.html\n\tDesenvolvido por: Samuel Junnior \n***\n\n", applicationName, serverPort);
    }
}
