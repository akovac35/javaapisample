package integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import si.zpiz.sample.infrastructure.misc.EnvironmentPropertiesPrinter;

@Configuration
@EnableAutoConfiguration
@ComponentScan("si.zpiz.sample.*")
public class IntegrationTestConfiguration {
    @Autowired
    @SuppressWarnings("unused")
    private EnvironmentPropertiesPrinter environmentPropertiesPrinter;
}
