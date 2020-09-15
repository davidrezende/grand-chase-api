package br.com.gc.api;

import br.com.gc.api.config.property.GCApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
@EnableConfigurationProperties(GCApiProperty.class)
@SpringBootApplication
public class GcApiApplication {
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
    public static void main(String[] args) {
        SpringApplication.run(GcApiApplication.class, args);
    }
}
