package si.zpiz.sample.infrastructure.misc;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EnvironmentPropertiesPrinter {
    private Environment env;

    public EnvironmentPropertiesPrinter(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void logApplicationProperties() throws Exception {
        log.info("{}={}", "spring.jpa.hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        log.info("{}={}", "spring.profiles.active", env.getProperty("spring.profiles.active"));
    }
}