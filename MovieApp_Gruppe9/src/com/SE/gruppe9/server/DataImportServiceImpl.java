package com.SE.gruppe9.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import com.SE.gruppe9.client.DataImportService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataImportServiceImpl extends RemoteServiceServlet implements
		DataImportService {

	// Index of the wiki ID
	private static final int WIKIID = 0;

	// Index of the movie name
	private static final int NAME = 2;

	// Index of the release year
	private static final int RELEASE_YEAR = 3;

	// Index of the box office revenue
	private static final int BOX_OFFICE_REVENUE = 4;

	// Index of the duration
	private static final int RUNTIME = 5;

	// Index of the language
	private static final int LANGUAGE = 6;

	// Index of the country
	private static final int COUNTRY = 7;

	// Index of the genre
	private static final int GENRE = 8;

	// Defines where the String split
	private static final String cvsSplitBy = ";";

	// Data document
	private static final String movieDoc = "movies_80000.csv";

	// Map to save the filtered data
	private Map<String, String> maps = new HashMap<String, String>();

	private Map<String, String> mapLanguage = new HashMap<String, String>();
	private Map<String, String> mapCountry = new HashMap<String, String>();
	private Map<String, String> mapGenre = new HashMap<String, String>();

	// Array to go through each row of the data file
	private String[] movie;

	private static String line = "";
	private BufferedReader br = null;

	/**
	 * cut freebaseID out of the String
	 */
	public void cutFreebaseID() {
		line = line.replaceAll("\"/m/?.*?\"", "").replace("\": \"", "")
				.replace("\"{\"", "").replace("\"}\"", "").replace("\"", "");
	}

	/**
	 * fills the array with a line of the data document
	 */
	public void setMovie(String[] movie) {
		this.movie = movie;
	}

	/**
	 * put the filtered in the map
	 */
	public void putDataInMap() {

		maps.put(movie[WIKIID], movie[NAME] + "==" + movie[RELEASE_YEAR] + "=="
				+ movie[BOX_OFFICE_REVENUE] + "==" + movie[RUNTIME] + "=="
				+ movie[LANGUAGE] + "==" + movie[COUNTRY] + "==" + movie[GENRE]);
	}

	/**
	 * gets the path of the csv file
	 * 
	 * @param name
	 * @return
	 */
	public Map<String, String> filterData(String name, int column) {
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath(movieDoc);
		try {
			maps = intermediateFilter(fullPath, name, column);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return maps;
	}

	/**
	 * reads in the csv file and filters the data
	 * 
	 * @param fullPath
	 * @param name
	 * @param column
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> intermediateFilter(String fullPath, String name,
			int column) throws Exception {

		try {
			br = new BufferedReader(new FileReader(fullPath));

			maps.clear();
			while ((line = br.readLine()) != null /* && i < 101 */) {

				cutFreebaseID();

				// use semicolon as separator
				setMovie(line.split(cvsSplitBy));

				switch (column) {
				// filters the movie name
				case 2:
					if (movie[NAME].trim().toUpperCase()
							.contains(name.trim().toUpperCase())) {
						putDataInMap();
					}
					break;

				// filters the release year
				case 3:
					if (movie[RELEASE_YEAR].equals(name)) {
						putDataInMap();
					}
					break;

				// filters the box office revenue
				case 4:
					if (name.equalsIgnoreCase("< 100'000")) {
						if (movie[BOX_OFFICE_REVENUE].length() > 0) {
							if (Long.parseLong(movie[BOX_OFFICE_REVENUE]) < 100000) {
								putDataInMap();
							}
						}
					} else if (name.equalsIgnoreCase("100'000-1'000'000")) {
						if (movie[BOX_OFFICE_REVENUE].length() > 0) {
							if (Long.parseLong(movie[BOX_OFFICE_REVENUE]) < 1000000
									&& Long.parseLong(movie[BOX_OFFICE_REVENUE]) >= 100000) {
								putDataInMap();
							}
						}
					} else if (name.equalsIgnoreCase("> 1'000'000")) {
						if (movie[BOX_OFFICE_REVENUE].length() > 0) {
							if (Long.parseLong(movie[BOX_OFFICE_REVENUE]) > 1000000) {
								putDataInMap();
							}
						}
					}
					break;

				// filters the runtime
				case 5:
					if (name.equals("≤ 60")) {
						// check if there is no empty string
						if (movie[RUNTIME].length() > 0) {
							if (Double.parseDouble(movie[RUNTIME]) <= 60) {
								putDataInMap();
							}
						}
					} else if (name.equals("≤ 90")) {
						// check if there is no empty string
						if (movie[RUNTIME].length() > 0) {
							if (Double.parseDouble(movie[RUNTIME]) <= 90) {
								putDataInMap();
							}
						}
					} else if (name.equals("> 90")) {
						// check if there is no empty string
						if (movie[RUNTIME].length() > 0) {
							if (Double.parseDouble(movie[RUNTIME]) > 90) {
								putDataInMap();
							}
						}
					}
					break;

				// filters the language
				case 6:
					if (movie[LANGUAGE].toUpperCase().contains(
							name.toUpperCase())) {
						putDataInMap();
						// i++;
					}

					// filters the country
				case 7:
					if (movie[COUNTRY].toUpperCase().contains(
							name.toUpperCase())) {
						putDataInMap();
						// i++;
					}
					break;

				// filters the genre
				case 8:
					if (movie[GENRE].toUpperCase().contains(name.toUpperCase())) {
						putDataInMap();
						// i++;
					}
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return maps;
	}

	/**
	 * get all data for the filter option 
	 * 
	 * @param constant
	 * @return
	 */
	public Map<String, String> getAllLCG() {

		ServletContext context = getServletContext();
		String fullPath = context.getRealPath(movieDoc);
		try {
			mapLanguage = intermediateLCG(fullPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapLanguage;
	}
	
	/**
	 * reads in the csv file and store all languages, countries and genres in an hashmap
	 * @param fullPath
	 * @return
	 */
	public Map<String, String> intermediateLCG(String fullPath) {
		try {
			br = new BufferedReader(new FileReader(fullPath));

			maps.clear();
			while ((line = br.readLine()) != null) {
				cutFreebaseID();

				// use semicolon as separator
				setMovie(line.split(cvsSplitBy));
				
				maps.put(movie[WIKIID], movie[LANGUAGE] + "==" + movie[COUNTRY] + "==" + movie[GENRE]);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return maps;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> countMovies() {

		ServletContext context = getServletContext();
		String fullPath = context.getRealPath(movieDoc);
		try {
			maps = intermediateCount(fullPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return maps;
	}
	
	/**
	 * 
	 * @param fullpath
	 * @return
	 */
	public Map<String,String> intermediateCount(String fullpath) {

		try {
			br = new BufferedReader(new FileReader(movieDoc));
			
			maps.clear();
			while ((line = br.readLine()) != null) {
				cutFreebaseID();
				
				setMovie(line.split(cvsSplitBy));
				
				maps.put(movie[WIKIID], movie[RELEASE_YEAR] + "==" + movie[COUNTRY]);
				
				}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return maps;
	}
}
