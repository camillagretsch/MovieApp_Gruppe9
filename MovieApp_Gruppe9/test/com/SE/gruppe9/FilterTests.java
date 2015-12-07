package com.SE.gruppe9;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import com.SE.gruppe9.server.*;

import java.util.ArrayList;
//import java.io.FileNotFoundException;
//import java.io.IOException;

public class FilterTests {
	
	static final String MOVIE_FILE_PATH = "/Users/gbellh/Documents/workspaceLuna/MovieApp_Gruppe9/war/movies_80000.csv";
	static final String WRONG_FILE = "/Users/carloschida/Apps/MovieApp_Gruppe9/war/movies_0.csv";
	DataImportServiceImpl dataImport = new DataImportServiceImpl();
	


	@Test
	public void intermediateFilter() throws Exception {
		Map<String, String> interFiltered = dataImport.intermediateFilter(MOVIE_FILE_PATH, "1996", 2);
		assertNotNull("Filtered LCG's are not null ", interFiltered);
	}
	
// Try and catch clause have to be removed (or commented) in class DataImportServiceImpl.java
//	@Test(expected=FileNotFoundException.class)
//	public void getAllYCGL() {
//		Map<String, String> getAll = dataImport.getAllYCGL();
//		assertNotNull("Movie count is not null ", getAll);
//	}
	
	// Try and catch clause have to be removed (or commented) in class DataImportServiceImpl.java
//		@Test(expected=FileNotFoundException.class)
//		public void filterData() {
//			Map<String, String> filterData = dataImport.filterData();
//			assertNotNull("Movie count is not null ", dilterData);
//		}
	
	@Test
	public void intermediateYLCG() throws Exception {
		Map<String, String> intermediateLCG = dataImport.intermediateYLCG(MOVIE_FILE_PATH);
		assertNotNull("intermediate count", intermediateLCG);
	}
	

	// Given that the File Path is static, a FileNotFoundException should never occur. Should it happen, it should be handled
	// at runtime.
	/*@Test(expected=FileNotFoundException.class)
	public void testWrongPath() throws Exception {
		Map<String, String> filteredData = dataImport.intermediateFilter("fake/path", "1996", 3);
		assertNotNull("Filtering by year yields no null", filteredData);
	}*/
	
	// The only testable expected exception if FileNotFound. IOExceptions are thrown only when the IOReader fails.
	/*@Test(expected=IOException.class)
	public void testWrongFile() throws Exception 
		Map<String, String> filteredData = dataImport.intermediateFilter(WRONG_FILE, "1996", 3);
		assertNotNull("Filtering by year yields no null", filteredData);
	}*/
	
	// If in a further increment of the development process we find method whose exceptions need to be tested,
	// it should be done in a similar fashion as the code below.
	// DUMMY TEST
	@Ignore
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException() {
		try {
			ArrayList emptyList = new ArrayList();
			Object o = emptyList.get(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	

}



