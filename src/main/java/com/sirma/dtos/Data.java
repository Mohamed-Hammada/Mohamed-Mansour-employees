package com.sirma.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class Data {

private	Long empId;
private	Long projectId;
private	Period period;

};