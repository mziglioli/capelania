package com.capelania.form;

import com.capelania.annotation.ValidEmail;
import com.capelania.model.User;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm extends DefaultForm{

	@NotEmpty(message = "validator.invalid.name")
	private String name;
	@ValidEmail
	private String email;
	@NotEmpty(message = "validator.invalid.password")
	private String pass;
	@NotEmpty(message = "validator.invalid.roles")
	private String allRoles;

	@Override
	public User convertToEntity() {
		User user = new User();
		if (getId() != 0) {
			user.setId(getId());
		} else {
            user.setPassword(pass);
        }
		user.setEmail(email);
		user.setName(name);
		return user;
	}
}