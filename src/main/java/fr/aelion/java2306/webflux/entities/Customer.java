package fr.aelion.java2306.webflux.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
    @Id
    private Long id;

    private final String firstName;

    private final String lastName;
}
