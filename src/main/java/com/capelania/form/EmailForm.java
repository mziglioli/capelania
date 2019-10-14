package com.capelania.form;

import com.capelania.annotation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailForm {

    @ValidEmail
    private String email;
    @NotEmpty(message = "validator.invalid.name")
    private String name;
    @NotEmpty(message = "validator.invalid.subject")
    private String subject;
    @NotEmpty(message = "validator.invalid.text")
    private String text;
}