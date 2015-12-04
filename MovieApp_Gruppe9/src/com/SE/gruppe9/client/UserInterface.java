package com.SE.gruppe9.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.LayoutData;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class UserInterface {

	// Arrays to get the data for the Listboxes
	private final String[] BoxOfficeRevenue = { "Box Office Revenue", "≤ 100'000", "100'000 - 1'000'000", "≥ 1'000'000" };
	private final String[] Runtime = { "Runtime", "≤ 60", "≤ 90", "> 90" };
	private Map<String, String> allLanguages = new HashMap<String, String>();
	static Map<String, String> allCountries = new HashMap<String, String>();
	private Map<String, String> allGenres = new HashMap<String, String>();
	static Map<String, String> allYears = new HashMap<String, String>();
	
	private DataImportServiceAsync filter = GWT.create(DataImportService.class);
	
	// all Buttons Tetxtbox and Listboxes
	private final Button goButton = new Button("GO");
	private final TextBox searchMovieField = new TextBox();
	private final ListBox listBoxYear = new ListBox();
	private final ListBox listBoxOffice = new ListBox();
	private final ListBox listBoxRuntime = new ListBox();
	private final ListBox listBoxLanguage = new ListBox();
	private final ListBox listBoxCountry = new ListBox();
	private final ListBox listBoxGenre = new ListBox();
	private final Button deleteButton = new Button("Delete");
	private final Button nextButton = new Button("Next 100");
	private final Button exportTabelButton = new Button("export Table as ...");

	private Panel hPanel = new Panel();
	private Panel h1Panel = new Panel();
	private Panel vPanel = new Panel();
	private Table table = new Table();

	private int count = 0;
	
	public static Map<String, String> getAllCountries(){
		return allCountries;
	}
	
	public static Map<String, String> getAllYears(){
		return allYears;
	}
	/**
	 * 
	 * @return
	 */
	public Panel getHPanel() {
		return hPanel;
	}

	/**
	 * 
	 * @return
	 */
	public Panel getVPanel() {
		return vPanel;
	}

	/**
	 * Create all Buttons, Listboxes, TextBox
	 */
	private void createAll() {

		hPanel.setLayout(new HorizontalLayout(10));
		vPanel.setLayout(new VerticalLayout());
		h1Panel.setLayout(new HorizontalLayout(10));
		hPanel.setBorder(false);
		vPanel.setBorder(false);
		h1Panel.setBorder(false);

		// Textbox for movie name
		searchMovieField.setText("Entre a movie name");
		hPanel.add(searchMovieField);

		// go button for movie name
		hPanel.add(goButton);

		// listbox for release year
		listBoxYear.addItem("Year");
		for (int i = 2016; i >= 1888; i--) {
			listBoxYear.addItem(Integer.toString(i));
		}
		listBoxYear.setVisibleItemCount(1);
		hPanel.add(listBoxYear);

		// listbox for box office revenue
		for (int i = 0; i < BoxOfficeRevenue.length; i++) {
			listBoxOffice.addItem(BoxOfficeRevenue[i]);
		}
		listBoxOffice.setVisibleItemCount(1);
		hPanel.add(listBoxOffice);

		// listbox for runtime
		for (int i = 0; i < Runtime.length; i++) {
			listBoxRuntime.addItem(Runtime[i]);
		}
		listBoxRuntime.setVisibleItemCount(1);
		hPanel.add(listBoxRuntime);
	
		hPanel.add(exportTabelButton);
		// listbox for language
		importAllEntries();
		listBoxLanguage.addItem("Language");
		listBoxLanguage.setVisibleItemCount(1);
		h1Panel.add(listBoxLanguage);

		// listbox for country
		listBoxCountry.addItem("Country");
		listBoxCountry.setVisibleItemCount(1);
		h1Panel.add(listBoxCountry);

		// listbox for genre
		listBoxGenre.addItem("Genre");
		listBoxGenre.setVisibleItemCount(1);
		h1Panel.add(listBoxGenre);
		
		// button to load the next 100 entries
		h1Panel.add(nextButton);
		
		// button to delete filter
		h1Panel.add(deleteButton);

		vPanel.add(h1Panel);
	}

	/**
	 * all change and click events
	 * 
	 * @return
	 */
	public Table choseEvents() {
		createAll();
		searchMovieField.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					String itemStringSelected = searchMovieField.getValue();

					if (count < 1) {
						table.firstFilter(itemStringSelected, 2);
					} else {
						table.clearFlexTable();
						table.setFlexTableHeader();
						table.secondFilter(itemStringSelected, 0);
					}
					count++;

				}
			}
		});

		goButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String itemStringSelected = searchMovieField.getValue();

				if (count < 1) {
					table.firstFilter(itemStringSelected, 2);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 0);
				}
				count++;

			}
		});

		// change event for listbox year
		listBoxYear.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxYear.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxYear.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 3);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 1);
				}
				count++;
			}

		});

		// change event for listbox box office revenue
		listBoxOffice.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxOffice.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxOffice
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 4);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 2);
				}
				count++;
			}

		});

		// change event for listbox runtime
		listBoxRuntime.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxRuntime.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxRuntime
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 5);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 3);
				}
				count++;
			}
		});

		// change event for listbox language
		listBoxLanguage.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxLanguage.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxLanguage
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 6);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 4);
				}
				count++;
			}
		});

		// change event for listbox country
		listBoxCountry.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxCountry.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxCountry
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 7);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 5);
				}
				count++;
			}
		});

		// change event for listbox genre
		listBoxGenre.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxGenre.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxGenre.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 8);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 6);
				}
				count++;
			}
		});
		
		// click event for delete Button 
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				count = 0;
				table.clearFlexTable();
				table.clearMap();
				listBoxYear.setSelectedIndex(0);
				listBoxOffice.setSelectedIndex(0);
				listBoxRuntime.setSelectedIndex(0);
				listBoxLanguage.setSelectedIndex(0);
				listBoxCountry.setSelectedIndex(0);
				listBoxGenre.setSelectedIndex(0);
				searchMovieField.setText("Entre a movie name");
				table.setFlexTableHeader();

			}
		});
		
		// click event for next Button 
		nextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			table.clearFlexTable();
			table.setFlexTableHeader();
			

			}
		});
		return table;
	}
	
	/**
	 * get all filter options
	 */
	public void importAllEntries() {
		
		// Initialize the service proxy.
		if (filter == null) {
			filter = GWT.create(DataImportService.class);
		}
		// Set up the callback object.
		final AsyncCallback<Map<String, String>> callback = new AsyncCallback<Map<String, String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Map<String, String> result) {
				String[] movies;
				
				// all years and countries in HashMap
				for (Map.Entry<String, String> entry : result.entrySet()) {
					movies = entry.getValue().split("==");
					
					allYears.put(entry.getKey(), movies[0] + "==" + movies[2]);
				}
				
				// all Languages in HashMap - no Duplicates
				for (Map.Entry<String, String> entry : result.entrySet()) {
					movies = entry.getValue().split("==");
					
					String[] tmp = movies[1].split(", ");

					int i = 0;
					while (i < tmp.length) {
						if (!tmp[i].equals("{}") && !tmp[i].equals("France") && tmp[i].length() < 40) {
							allLanguages.put(tmp[i], "");
						}
						i++;
					}
				}
				
				// all Countries in HashMap - no Duplicates
				for (Map.Entry<String, String> entry : result.entrySet()) {
					movies = entry.getValue().split("==");

					String[] tmp = movies[2].split(", ");

					int i = 0;
					while (i < tmp.length) {
						if (!tmp[i].equals("{}") && !tmp[i].contains("Language") && !tmp[i].equals("Crime")) {
							allCountries.put(tmp[i], "");
						}
						i++;
					}
				}
				
				// all Genres in HashMap - no Duplicates
				for (Map.Entry<String, String> entry : result.entrySet()) {
					movies = entry.getValue().split("==");
					
					String[] tmp = movies[3].split(", ");
					
					int i = 0;
					while (i < tmp.length) {
						if (!tmp[i].equals("{}")) {
							allGenres.put(tmp[i], "");
						}
						i++;
					}
				}
				
				// create a new array, fill it with the map keys and sort it
				String[] languagesSorted = new String[allLanguages.size()];
				int countL = 0;
				for (Map.Entry<String, String> entry : allLanguages.entrySet()) {
					languagesSorted[countL] = entry.getKey();
					countL++;
				}
				languagesSorted = sortArray(languagesSorted);
				
				// add Languages to ListBox
				for (int i = 0; i < languagesSorted.length; i++) {
					listBoxLanguage.addItem(languagesSorted[i]);
				}
				
				// create a new array, fill it with the map keys and sort it
				String[] countriesSorted = new String[allCountries.size()];
				int countC = 0;
				for (Map.Entry<String, String> entry : allCountries.entrySet()) {
					countriesSorted[countC] = entry.getKey();
					countC++;
				}
				countriesSorted = sortArray(countriesSorted);
				
				// add Countries to ListBox
				for (int i = 0; i < countriesSorted.length; i++) {
					listBoxCountry.addItem(countriesSorted[i]);
				}
				
				// create a new array, fill it with the map keys and sort it
				String[] genresSorted = new String[allGenres.size()];
				int countG = 0;
				for (Map.Entry<String, String> entry : allGenres.entrySet()) {
					genresSorted[countG] = entry.getKey();
					countG++;
				}
				genresSorted = sortArray(genresSorted);
				
				// add Genres to ListBox
				for(int i = 0; i < genresSorted.length; i++){
					listBoxGenre.addItem(genresSorted[i]);
				}	
			}
		};
		filter.getAllLCG(callback);
	}
	
	public String[] sortArray(String[] arrayToSort) {	

		boolean flag = true; // will determine when the sort is finished
		String temp;
		while (flag) {
			flag = false;
			for (int i = 0; i < arrayToSort.length - 1; i++ ) {
				if (arrayToSort [i].compareToIgnoreCase( arrayToSort[i + 1] ) > 0) {	// ascending sort
					temp = arrayToSort[i];
					arrayToSort[i] = arrayToSort[i + 1]; // swapping
					arrayToSort [i + 1] = temp;
					flag = true;
				}
			}
		}
		return arrayToSort;
	}
}
