package com.SE.gruppe9;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import com.SE.gruppe9.server.*;

public class ManualTest {
	
	static final String WRONG_FILE = "/Users/carloschida/Apps/MovieApp_Gruppe9/war/movies_0.csv";

	public static void main(String[] args) throws Exception {
		DataImportServiceImpl dataImport = new DataImportServiceImpl();
		Map<String, String> filteredData = dataImport.intermediateFilter(WRONG_FILE, "1996", 3);
		assertNotNull("Filtering by year yields no null", filteredData);
		
		Map<String, String> filteredData1 = dataImport.intermediateColumnFilter(WRONG_FILE, 6);
		assertNotNull("Filtering by year yields no null", filteredData);
		System.out.println(filteredData);
		System.out.println(filteredData1);
	}

}
