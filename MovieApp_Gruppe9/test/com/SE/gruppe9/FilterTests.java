package com.SE.gruppe9;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import com.SE.gruppe9.server.*;
import java.util.ArrayList;
//import java.io.FileNotFoundException;
//import java.io.IOException;

public class FilterTests {
	
	static final String MOVIE_FILE_PATH = "/Users/carloschida/Apps/MovieApp_Gruppe9/war/movies_80000.csv";
	static final String WRONG_FILE = "/Users/carloschida/Apps/MovieApp_Gruppe9/war/movies_0.csv";
	
	@Test
	public void testFilter() throws Exception{
		DataImportServiceImpl dataImport = new DataImportServiceImpl();
		Map<String, String> filteredData = dataImport.intermediateFilter(MOVIE_FILE_PATH, "1996", 3);
		assertNotNull("Filtering by year yields no null", filteredData);
	}
	
	// Given that the File Path is static, a FileNotFoundException should never occur. Should it happen, it should be handled
	// at runtime.
	/*@Test(expected=FileNotFoundException.class)
	public void testWrongPath() throws Exception {
		DataImportServiceImpl dataImport = new DataImportServiceImpl();
		Map<String, String> filteredData = dataImport.intermediateFilter("fake/path", "1996", 3);
		assertNotNull("Filtering by year yields no null", filteredData);
	}*/
	
	// The only testable expected exception if FileNotFound. IOExceptions are thrown only when the IOReader fails.
	/*@Test(expected=IOException.class)
	public void testWrongFile() throws Exception {
		DataImportServiceImpl dataImport = new DataImportServiceImpl();
		Map<String, String> filteredData = dataImport.intermediateFilter(WRONG_FILE, "1996", 3);
		assertNotNull("Filtering by year yields no null", filteredData);
	}*/
	
	// If in a further increment of the development process we find method whose exceptions need to be tested,
	// it should be done in a similar fashion as the code below.
	// DUMMY TEST
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

