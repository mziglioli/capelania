package com.capelania.form;

import com.capelania.model.EntityJpa;
import com.capelania.model.Event;
import com.capelania.model.Opening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpeningForm extends DefaultForm {

	@NotEmpty(message = "validator.invalid.day")
	private String day;
	@NotEmpty(message = "validator.invalid.startAm")
	private String startAm;
	@NotEmpty(message = "validator.invalid.endAm")
	private String endAm;
    @NotEmpty(message = "validator.invalid.startPm")
    private String startPm;
    @NotEmpty(message = "validator.invalid.endPm")
    private String endPm;

    @Override
	public EntityJpa convertToEntity() {
        Opening opening = new Opening();
        if (getId() != 0) {
            opening.setId(getId());
        }
        opening.setDay(day);
        opening.setStartAm(startAm);
        opening.setEndAm(endAm);
        opening.setStartPm(startPm);
        opening.setEndPm(endPm);
        opening.setActive(active);

        return opening;
	}
}