package com.capelania.form;

import com.capelania.model.EntityJpa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultForm {

	private long id;

	public EntityJpa convertToEntity() {
		return new EntityJpa() {
			@Override
			public Long getId() {
				return 0L;
			}
		};
	}
}