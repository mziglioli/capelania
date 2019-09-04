package com.capelania.form;

import com.capelania.model.EntityJpa;
import com.capelania.model.Feed;
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
public class FeedForm extends DefaultForm {

	@NotEmpty(message = "validator.invalid.title")
	private String title;
	@NotEmpty(message = "validator.invalid.description")
	private String description;
	@NotEmpty(message = "validator.invalid.header")
	private String header;
	@NotEmpty(message = "validator.invalid.text")
	private String text;
    @NotEmpty(message = "validator.invalid.footer")
    private String footer;
    @NotEmpty(message = "validator.invalid.date")
    private String date;
	@Min(value = 1, message = "validator.invalid.expire")
	private String expire;

	@Override
	public EntityJpa convertToEntity() {
        Feed feed = new Feed();

        return feed;
	}
}