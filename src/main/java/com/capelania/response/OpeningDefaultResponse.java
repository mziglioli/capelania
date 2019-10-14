package com.capelania.response;

import com.capelania.model.Opening;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Getter
@Setter
public class OpeningDefaultResponse {

	private List<Opening> openings;
	private boolean isMondayToFriday;
	private boolean isAllDay;

	public OpeningDefaultResponse(List<Opening> openings) {
		this.openings = openings;
		if (isAllDayTheSame()) {
			this.isAllDay = true;
			this.isMondayToFriday = true;
		} else if (isMondayToFridayTheSame()) {
			this.isMondayToFriday = true;
		}
	}

	boolean isAllDayTheSame() {
		if (isNotEmpty(openings)) {
			String values = this.openings.get(0).combineTime();
			return this.openings.stream().allMatch(op -> values.equalsIgnoreCase(op.combineTime()));
		}
		return false;
	}

	boolean isMondayToFridayTheSame() {
		if (isNotEmpty(openings)) {
			String values = this.openings.get(0).combineTime();
			return this.openings.stream()
					.filter(op -> !"Saturday".equalsIgnoreCase(op.getDay()) && !"Sunday".equalsIgnoreCase(op.getDay()))
					.allMatch(op -> values.equalsIgnoreCase(op.combineTime()));
		}
		return false;
	}
}