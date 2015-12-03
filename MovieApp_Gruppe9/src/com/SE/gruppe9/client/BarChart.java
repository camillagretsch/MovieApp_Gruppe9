package com.SE.gruppe9.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.ColumnChart;
import com.googlecode.gwt.charts.client.corechart.ColumnChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;

/**
 * drawBarcharts for visualizing the filtered dataset
 */
public class BarChart {

	// to count the movies
	private List<Integer> numberOfFilmsCountry = new ArrayList<Integer>();
	private List<Integer> numberOfFilmsLanguage = new ArrayList<Integer>();
	private List<Integer> numberOfFilmsGenre = new ArrayList<Integer>();

	// save the all an the unique
	private List<String> allCountries = new ArrayList<String>();
	private List<String> uniqueCountries = new ArrayList<String>();
	private List<String> allLanguages = new ArrayList<String>();
	private List<String> uniqueLanguages = new ArrayList<String>();
	private List<String> allGenres = new ArrayList<String>();
	private List<String> uniqueGenres = new ArrayList<String>();

	final static RootLayoutPanel rp = RootLayoutPanel.get();
	private Button exportVisualButton = new Button("export Visuals");
	private Button backButton = new Button("back");
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanel = new VerticalPanel();

	/**
	 * 
	 */
	public void createExport() {
		hPanel.add(exportVisualButton);
		hPanel.add(backButton);
		vPanel.add(hPanel);
		// add Button to export the visuals
		exportVisualButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Screenshot");
			}
		});

		// leave chart
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rp.setVisible(false);
			}
		});
	}

	/**
	 * initialize Barchart for visualizing number of movies per Country
	 */
	void createChartCountry() {

		createExport();

		// Create a Dock Panel
		final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.getElement().getStyle()
				.setProperty("border", "solid lightblue 4px");

		// Add text all around
		dockLayoutPanel.addNorth(new HTML(""), 7);
		dockLayoutPanel.addWest(new HTML(""), 14);
		dockLayoutPanel.addSouth(new HTML(""), 5);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {

				ColumnChart col = new ColumnChart();
				vPanel.add(col);
				BarChart barchart = new BarChart();
				barchart.drawChartCountry(col);
				dockLayoutPanel.add(vPanel);
			}
		});
		rp.add(dockLayoutPanel);
	}

	/**
	 * draw Barchart for Country
	 * 
	 * @param chart
	 */
	void drawChartCountry(ColumnChart chart) {

		setNumberOfFilmsCountry();

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(uniqueCountries.size());

		for (int i = 0; i < uniqueCountries.size(); i++) {
			dataTable.setValue(i, 0, uniqueCountries.get(i));
		}
		for (int row = 0; row < numberOfFilmsCountry.size(); row++) {
			dataTable.setValue(row, 1, numberOfFilmsCountry.get(row));
		}

		// Set options
		ColumnChartOptions options = ColumnChartOptions.create();
		options.setFontName("Tahoma");
		options.setTitle("Number of films per Country for filtered Data");
		options.setHAxis(HAxis.create("Country"));
		options.setVAxis(VAxis.create("Number of Films"));
		options.setHeight(400);
		options.setWidth(835);

		// Draw the chart
		chart.draw(dataTable, options);

	}

	/**
	 * initialize Barchart for visualizing number of movies per Genre
	 */
	void createChartGenre() {

		createExport();

		// Create a Dock Panel
		final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.getElement().getStyle()
				.setProperty("border", "solid lightblue 4px");

		// Add text all around
		dockLayoutPanel.addNorth(new HTML(""), 7);
		dockLayoutPanel.addWest(new HTML(""), 14);
		dockLayoutPanel.addSouth(new HTML(""), 5);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {

				ColumnChart col = new ColumnChart();
				vPanel.add(col);
				BarChart barchart = new BarChart();
				barchart.drawChartGenre(col);
				dockLayoutPanel.add(vPanel);
			}
		});
		rp.add(dockLayoutPanel);
	}

	/**
	 * draw Barchart for Genre
	 * 
	 * @param chart
	 */
	void drawChartGenre(ColumnChart chart) {

		setNumberOfFilmsGenre();

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Genre");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(uniqueGenres.size());

		for (int i = 0; i < uniqueGenres.size(); i++) {
			dataTable.setValue(i, 0, uniqueGenres.get(i));
		}
		for (int row = 0; row < numberOfFilmsGenre.size(); row++) {
			dataTable.setValue(row, 1, numberOfFilmsGenre.get(row));
		}

		// Set options
		ColumnChartOptions options = ColumnChartOptions.create();
		options.setFontName("Tahoma");
		options.setTitle("Number of films per Genre for filtered Data");
		options.setHAxis(HAxis.create("Genre"));
		options.setVAxis(VAxis.create("Number of Films"));
		options.setHeight(400);
		options.setWidth(835);

		// Draw the chart
		chart.draw(dataTable, options);

	}

	/**
	 * initialize Barchart for visualizing number of movies per Language
	 */
	void createChartLanguage() {

		createExport();

		// Create a Dock Panel
		final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.getElement().getStyle()
				.setProperty("border", "solid lightblue 4px");

		// Add text all around
		dockLayoutPanel.addNorth(new HTML(""), 7);
		dockLayoutPanel.addWest(new HTML(""), 14);
		dockLayoutPanel.addSouth(new HTML(""), 5);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {

				ColumnChart col = new ColumnChart();
				vPanel.add(col);
				BarChart barchart = new BarChart();
				barchart.drawChartLanguage(col);
				dockLayoutPanel.add(vPanel);
			}
		});
		rp.add(dockLayoutPanel);
	}

	/**
	 * draw Barchart for Language
	 * 
	 * @param chart
	 */
	void drawChartLanguage(ColumnChart chart) {

		setNumberOfFilmsLanguage();

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Language");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(uniqueLanguages.size());

		for (int i = 0; i < uniqueLanguages.size(); i++) {
			dataTable.setValue(i, 0, uniqueLanguages.get(i));
		}
		for (int row = 0; row < numberOfFilmsLanguage.size(); row++) {
			dataTable.setValue(row, 1, numberOfFilmsLanguage.get(row));
		}

		// Set options
		ColumnChartOptions options = ColumnChartOptions.create();
		options.setFontName("Tahoma");
		options.setTitle("Number of films per Language for filtered Data");
		options.setHAxis(HAxis.create("Language"));
		options.setVAxis(VAxis.create("Number of Films"));
		options.setHeight(400);
		options.setWidth(835);

		// Draw the chart
		chart.draw(dataTable, options);

	}

	/**
	 * set all Genre entries of the filtered table from hashmap to list
	 */
	public void setGenre() {
		for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
			String[] tmp = entry.getValue().split("==");
			if (tmp[6].isEmpty() == false && !tmp[6].equals("{}")) {
				String[] temp = tmp[6].split(", ");
				allGenres.addAll(Arrays.asList(temp));
			}
		}
		for (int i = 0; i < allGenres.size(); i++) {
			(allGenres.get(i)).trim();
		}
	}

	/**
	 * calculate number of movies for each Genre
	 */
	private void setNumberOfFilmsGenre() {
		setGenre();

		uniqueGenres.addAll(removeDuplicates(allGenres));

		int counter = 0;
		for (int i = 0; i < uniqueGenres.size(); i++) {
			counter = Collections.frequency(allGenres, uniqueGenres.get(i));
			numberOfFilmsGenre.add(counter);
		}
	}

	/**
	 * set all Language entries of the filtered table from hashmap to list
	 */
	public void setLanguage() {
		for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
			String[] tmp = entry.getValue().split("==");
			if (tmp[4].isEmpty() == false && !tmp[4].equals("{}")) {
				String[] temp = tmp[4].split(", ");
				allLanguages.addAll(Arrays.asList(temp));
			}
		}
		for (int i = 0; i < allLanguages.size(); i++) {
			(allLanguages.get(i)).trim();
		}
	}

	/**
	 * calculate number of movies for each Language
	 */
	private void setNumberOfFilmsLanguage() {
		setLanguage();

		uniqueLanguages.addAll(removeDuplicates(allLanguages));

		int counter = 0;
		for (int i = 0; i < uniqueLanguages.size(); i++) {
			counter = Collections.frequency(allLanguages,
					uniqueLanguages.get(i));
			numberOfFilmsLanguage.add(counter);
		}

	}

	/**
	 * set all country entries of the filtered table from hashmap to list
	 */
	public void setCountry() {
		for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
			String[] tmp = entry.getValue().split("==");
			if (tmp[5].isEmpty() == false && !tmp[5].equals("{}")) {
				String[] temp = tmp[5].split(", ");
				allCountries.addAll(Arrays.asList(temp));
			}
		}
		for (int i = 0; i < allCountries.size(); i++) {
			(allCountries.get(i)).trim();
		}
	}

	/**
	 * calculate number of movies for each country
	 */
	private void setNumberOfFilmsCountry() {
		setCountry();

		uniqueCountries.addAll(removeDuplicates(allCountries));

		int counter = 0;
		for (int i = 0; i < uniqueCountries.size(); i++) {
			counter = Collections.frequency(allCountries,
					uniqueCountries.get(i));
			numberOfFilmsCountry.add(counter);
		}
	}

	/**
	 * Takes a list of Strings with duplicate values and creates a list with
	 * unique values
	 * 
	 * @param list
	 * @return list
	 */
	private ArrayList<String> removeDuplicates(List<String> list) {
		Set<String> set = new HashSet<String>(list);
		ArrayList<String> uniqueList = new ArrayList<String>(set);

		return uniqueList;
	}
}
