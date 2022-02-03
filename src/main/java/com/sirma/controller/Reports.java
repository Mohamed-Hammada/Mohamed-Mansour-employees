package com.sirma.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sirma.dtos.ResultsData;
import com.sirma.services.ReportsService;
import com.sirma.utiles.LoadEmployeeDataUtiles;


@Controller
public class Reports {

	@Autowired
	private ReportsService reportsService;

	@Autowired
	LoadEmployeeDataUtiles loadEmployeeDataUtiles;

	@GetMapping("/")
	public String homepage() {
		return "index";
	}

	@PostMapping("/longestEmployeePairs")
	public String getLongestEmployeePairs(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			Model model) throws IOException {
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/";
		}
		Map<Long, ResultsData> results = reportsService.getLongestEmployeePairs(file);
		model.addAttribute("form", results);
		return "view";

	}

}
