package com.example.test2.payloads;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotNull
    private String name;

    @NotNull
    private DetailsDto details;

    private String accountType;

    @NotNull
    private List<String> businessRequirements;

    @NotNull
    @Pattern(regexp = "^(fulltime|parttime)$", message = "Contract type should be either fulltime or parttime")
    private String contractType;

}
