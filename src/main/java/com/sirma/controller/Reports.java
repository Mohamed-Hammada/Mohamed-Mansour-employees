package com.sirma.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.dtos.ResultsData;
import com.sirma.services.ReportsService;
import com.sirma.utiles.LoadEmployeeDataUtiles;

@RestController
@RequestMapping("/reports")
public class Reports {

	@Autowired
	private ReportsService reportsService;

	@Autowired
	LoadEmployeeDataUtiles loadEmployeeDataUtiles;

	@PostMapping("/longestEmployeePairs")
	public Map<Long, ResultsData> getLongestEmployeePairs(@RequestParam("file") MultipartFile file) throws IOException {
		return reportsService.getLongestEmployeePairs(file);
	}

}
