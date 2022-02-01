package com.sirma.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.dtos.Data;
import com.sirma.dtos.Period;

@Component
public class LoadEmployeeDataUtiles {

	public List<Data> loadData(MultipartFile file) throws IOException {
		List<Data> data = new ArrayList<Data>();
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			for (CSVRecord csvRecord : csvParser) {
				Long empID = StringConversion.getLongCellValue(csvRecord.get("EmpID"));
				Long projectID = StringConversion.getLongCellValue(csvRecord.get("ProjectID"));
				LocalDate dateFrom = DateUtiles.getLocalDate(csvRecord.get("DateFrom"));
				LocalDate dateTo = DateUtiles.getLocalDate(csvRecord.get("DateTo"));
				data.add(new Data(empID, projectID, new Period(dateFrom, dateTo)));
			}
		}
		return data;
	}
}
