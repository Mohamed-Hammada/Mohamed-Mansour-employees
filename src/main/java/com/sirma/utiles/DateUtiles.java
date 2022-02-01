package com.sirma.utiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.sirma.dtos.Period;

public class DateUtiles {

	public static LocalDate getLocalDate(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate lDate = null;
		if (date == null || date.isEmpty()) {
			lDate = LocalDate.now();
		} else {
			lDate = LocalDate.parse(date, format);
		}
		return lDate;
	}

	public static long overlap(Period p1, Period p2) {
		LocalDate start = p1.getDateFrom().isBefore(p2.getDateFrom()) ? p2.getDateFrom() : p1.getDateFrom();
		LocalDate end = p1.getDateTo().isBefore(p2.getDateTo()) ? p1.getDateTo() : p2.getDateTo();
		if (end.compareTo(start) >= 0) {
			// difference
			return ChronoUnit.DAYS.between(start, end) + 1;

		}
		return 0;
	}
}
