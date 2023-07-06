package com.example.test2.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Details details;

    private String accountType;

    @ElementCollection
    private List<String> businessRequirements;

    private String contractType;


}
