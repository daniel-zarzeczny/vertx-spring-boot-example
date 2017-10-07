package pl.danielzarzeczny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.danielzarzeczny.verticle.DeploymentManager;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VertxSpringBootExampleApplication {

    private final DeploymentManager deploymentManager;

    @Autowired
    VertxSpringBootExampleApplication(final DeploymentManager deploymentManager) {
        this.deploymentManager = deploymentManager;
    }

    public static void main(final String[] args) {
        SpringApplication.run(VertxSpringBootExampleApplication.class, args);
    }

    @PostConstruct
    public void deployVerticles() {
        deploymentManager.deployVerticles();
    }

}
