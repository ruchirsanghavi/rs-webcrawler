package uk.co.crawler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Main class to run the application
 *
 * @author rsanghavi
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * Main entry point to application. Uses default SpringApplication settings.
     * This is executed when the war file is executed.
     *
     * @param args program arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return new Runner();
    }

}
