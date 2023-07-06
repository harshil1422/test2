package com.example.test2.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
public class Details {
    private String sex;
    private LocalDate dob;
    private String nativePlace;

}
