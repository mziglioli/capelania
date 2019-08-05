package com.capelania.form;

import com.capelania.annotation.ValidEmail;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

	@ValidEmail
	private String email;
	@NotEmpty(message = "validator.invalid.password")
	private String password;
}