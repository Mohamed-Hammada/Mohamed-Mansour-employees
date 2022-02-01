package com.sirma.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.dtos.Data;
import com.sirma.dtos.ResultsData;
import com.sirma.services.ReportsService;
import com.sirma.utiles.DateUtiles;
import com.sirma.utiles.LoadEmployeeDataUtiles;

@Component
public class ReportsServiceImp implements ReportsService {

	@Autowired
	private LoadEmployeeDataUtiles loadEmployeeDataUtiles;

	@Override
	public Map<Long, ResultsData> getLongestEmployeePairs(MultipartFile file) throws IOException {
		List<Data> currentData = loadEmployeeDataUtiles.loadData(file);

		Map<Long, ResultsData> result = new HashMap<Long, ResultsData>();
		for (int i = 0; i < currentData.size(); i++) {
			Data emploeeOne = currentData.get(i);
			ResultsData resultsData = null;
			long longestTime = 0;
			for (int j = 0; j < currentData.size(); j++) {
				Data emploeeTwo = currentData.get(j);
				if (i != j && emploeeOne.getProjectId() == emploeeTwo.getProjectId()) {
					long currentOverlap = DateUtiles.overlap(emploeeOne.getPeriod(), emploeeTwo.getPeriod());
					if (currentOverlap > longestTime) {
						longestTime = currentOverlap;
						resultsData = new ResultsData(emploeeOne.getEmpId(), emploeeTwo.getEmpId(), currentOverlap);
					}
				}
			}

			ResultsData resultsDataIfItsExist = result.get(emploeeOne.getProjectId());

			if (resultsDataIfItsExist != null) {
				if (resultsDataIfItsExist.getOverlap() < resultsData.getOverlap()) {
					result.put(emploeeOne.getProjectId(), resultsDataIfItsExist);
				}
			} else {
				if (resultsData != null)
					result.put(emploeeOne.getProjectId(), resultsData);
			}

		}

		return result;
	}

}
