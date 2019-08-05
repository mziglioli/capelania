package com.capelania.form;

import com.capelania.model.EntityJpa;
import com.capelania.model.Mass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MassForm extends DefaultForm {

	@NotEmpty(message = "validator.invalid.title")
	private String title;
	@NotEmpty(message = "validator.invalid.description")
	private String description;
	@NotEmpty(message = "validator.invalid.day")
	private String day;
	@NotEmpty(message = "validator.invalid.start")
	private String start;
	//	duration in minutes
	@Min(value = 1, message = "validator.invalid.duration")
	private Integer duration;

	@Override
	public EntityJpa convertToEntity() {
		Mass mass = new Mass();
		if (getId() != 0) {
			mass.setId(getId());
		}
		mass.setDay(day);
		mass.setDescription(description);
		mass.setDuration(duration);
		mass.setStart(start);
		mass.setTitle(title);
		return mass;
	}
}