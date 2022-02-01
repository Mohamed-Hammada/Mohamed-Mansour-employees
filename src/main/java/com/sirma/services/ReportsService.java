package com.sirma.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sirma.dtos.ResultsData;


public interface ReportsService {

	Map<Long, ResultsData> getLongestEmployeePairs(MultipartFile file) throws IOException;
}
