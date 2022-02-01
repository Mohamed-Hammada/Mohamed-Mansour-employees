package com.sirma.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultsData {
	private Long empIdOne;
	private Long empIdTwo;
	private Long overlap;
};
