package com.capelania.response;

import com.capelania.model.Opening;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class OpeningDefaultResponseTest {

	@Test
	public void test_null() {
		OpeningDefaultResponse response = new OpeningDefaultResponse(null);

		assertFalse(response.isMondayToFridayTheSame());
		assertFalse(response.isAllDayTheSame());
	}

	@Test
	public void test_empty() {
		List<Opening> op = new ArrayList<>();
		OpeningDefaultResponse response = new OpeningDefaultResponse(op);

		assertFalse(response.isMondayToFridayTheSame());
		assertFalse(response.isAllDayTheSame());
	}

	@Test
	public void test_allDayTheSame() {
		List<Opening> op = build(true, true);
		OpeningDefaultResponse response = new OpeningDefaultResponse(op);

		assertTrue(response.isMondayToFridayTheSame());
		assertTrue(response.isAllDayTheSame());
	}

	@Test
	public void test_MondayToFridayTheSame() {
		List<Opening> op = build(true, false);
		OpeningDefaultResponse response = new OpeningDefaultResponse(op);

		assertTrue(response.isMondayToFridayTheSame());
		assertFalse(response.isAllDayTheSame());
	}

	private List<Opening> build(boolean isMondayToFridayTheSame, boolean isAllTheSame) {
		List<Opening> op = new ArrayList<>();

		op.add(build("Monday", "17:00"));
		op.add(build("Tuesday", "17:00"));
		op.add(build("Wednesday", "17:00"));
		op.add(build("Thursday", isMondayToFridayTheSame ? "17:00" : "18:00"));
		op.add(build("Friday", isMondayToFridayTheSame ? "17:00" : "18:00"));
		op.add(build("Saturday", isAllTheSame ? "17:00" : "20:00"));
		op.add(build("Sunday", isAllTheSame ? "17:00" : "20:00"));

		return op;
	}

	private Opening build(String day, String endPm) {
		Opening op = new Opening();
		op.setDay(day);
		op.setStartAm("08:00");
		op.setEndAm("12:00");
		op.setStartPm("14:00");
		op.setEndPm(endPm);
		return op;
	}

}