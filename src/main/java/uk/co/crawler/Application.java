package uk.co.crawler;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Main class to run the application
 *
 * @author rsanghavi
 */
@SpringBootApplication
public class Application {

    /**
     * Main entry point to application. Uses default SpringApplication settings.
     *
     * @param args program arguments
     */
    public static void main(final String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebEnvironment(false);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return new Runner();
    }

}
