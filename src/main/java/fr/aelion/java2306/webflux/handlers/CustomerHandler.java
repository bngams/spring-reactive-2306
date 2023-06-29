package fr.aelion.java2306.webflux.handlers;

import fr.aelion.java2306.webflux.entities.Customer;
import fr.aelion.java2306.webflux.repositories.CustomerRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;

@Log
@Component // <=> @Bean
public class CustomerHandler {

    private CustomerRepository cr;

    @Autowired
    CustomerHandler(CustomerRepository cr) {
        log.info("constructor");
        this.cr = cr;
    }

    @PostConstruct
    void init() {
        log.info("bean init");
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.cr.findAll(), Customer.class);
    }

    public Mono<ServerResponse> post(ServerRequest request) {
        return request.bodyToMono(Customer.class)
                .flatMap(customer -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.cr.save(customer), Customer.class)
                );
    }
}
