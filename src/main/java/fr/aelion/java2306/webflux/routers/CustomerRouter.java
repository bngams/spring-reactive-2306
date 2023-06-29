package fr.aelion.java2306.webflux.routers;

import fr.aelion.java2306.webflux.handlers.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class CustomerRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CustomerHandler customerHandler) {
        return RouterFunctions
                .route(GET("/customer").and(accept(MediaType.APPLICATION_JSON)), customerHandler::index);
    }

    @Bean
    public RouterFunction<ServerResponse> routePost(CustomerHandler customerHandler) {
        return RouterFunctions
                .route(POST("/customer").and(accept(MediaType.APPLICATION_JSON)), customerHandler::post);
    }
}
