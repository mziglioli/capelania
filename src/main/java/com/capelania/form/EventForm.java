package com.capelania.form;

import com.capelania.model.EntityJpa;
import com.capelania.model.Event;
import com.capelania.utils.DateUtils;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventForm extends DefaultForm {

	@NotEmpty(message = "validator.invalid.title")
	private String title;
	@NotEmpty(message = "validator.invalid.description")
	private String description;
	@NotEmpty(message = "validator.invalid.text")
	private String text;
    private String img;
    @NotEmpty(message = "validator.invalid.day")
    private String date;

	@Override
	public EntityJpa convertToEntity() {
        Event event = new Event();
        if (getId() != 0) {
            event.setId(getId());
        }
        event.setDescription(description);
        event.setTitle(title);
        event.setActive(active);
        event.setText(text);
        event.setImg(img);
        event.setDate(date);
        return event;
	}
}