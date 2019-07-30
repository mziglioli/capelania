package com.capelania.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DefaultResponse {

	private boolean success;
	private List<Error> errors;
}