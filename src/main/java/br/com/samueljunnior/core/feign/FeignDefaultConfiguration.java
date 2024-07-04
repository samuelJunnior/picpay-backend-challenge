package br.com.samueljunnior.core.feign;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class FeignDefaultConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(Duration.ofSeconds(5L), Duration.ofSeconds(30L), true);
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(Duration.ofSeconds(1).toMillis(), Duration.ofSeconds(5).toMillis(), 3);
    }

}
