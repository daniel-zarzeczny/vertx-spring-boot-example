package pl.danielzarzeczny.config;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "pl.danielzarzeczny")
class ApplicationContext {

    @Bean
    Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    EventBus eventBus() {
        return vertx().eventBus();
    }
}
