package com.example.test2.payloads;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class DetailsDto {
    @NotNull
    @Pattern(regexp = "^[MF]$", message = "Sex should be either M or F")
    private String sex;

    @NotNull
    private LocalDate dob;

    @NotNull
    private String nativePlace;

}
