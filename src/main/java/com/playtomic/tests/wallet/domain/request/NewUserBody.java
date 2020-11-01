package com.playtomic.tests.wallet.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class NewUserBody {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
}
