package com.SE.gruppe9.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;

public class Heatmap {

	private Button backButton = new Button("back");
	private VerticalPanel verticalPanel = new VerticalPanel();
	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	final static RootLayoutPanel rp = RootLayoutPanel.get();

	private Map<String, Integer> countries = new HashMap<String, Integer>();

	/**
	 * creates a Panel and communicates with the HTML page
	 * 
	 * @param year
	 */
	public void createChart(final String year) {

		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rp.setVisible(false);
				countries.clear();

			}

		});

		horizontalPanel.add(backButton);
		verticalPanel.add(horizontalPanel);

		// Create a Dock Panel
		final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);

		// Add text all around
		dockLayoutPanel.addSouth(new HTML(""), 5);
		dockLayoutPanel.addWest(new HTML(""), 15);
		dockLayoutPanel.addNorth(new HTML(""), 5);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {

				GeoChart geoChart = new GeoChart();
				verticalPanel.add(geoChart);
				Heatmap worldMap = new Heatmap();
				worldMap.draw(geoChart, year);
				dockLayoutPanel.add(verticalPanel);
			}
		});
		rp.add(dockLayoutPanel);
	}

	/**
	 * Styles and draws a world map and also inserts data values into an array
	 * which the map then visualises.
	 * 
	 * @param geoChart
	 * @param year
	 */
	private void draw(GeoChart geoChart, String year) {
		countMovies(year);

		JsArrayString ColourArray = JavaScriptObject.createArray().cast();
		ColourArray.push("#ff6666");
		ColourArray.push("#ffff99");
		ColourArray.push("#66ff66");

		// Prepare the data
		DataTable dataTable = DataTable.create();

		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Number of movies");

		dataTable.addRows(countries.size());

		int i = 0;
		for (Map.Entry<String, Integer> entry : countries.entrySet()) {
			dataTable.setValue(i, 0, entry.getKey());
			dataTable.setValue(i, 1, entry.getValue());
			i++;
		}

		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors(ColourArray);
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("#ffebe5");
		options.setBackgroundColor("#e5ffff");
		options.setHeight(500);
		options.setWidth(900);

		// Draw the chart
		geoChart.draw(dataTable, options);
	}

	/**
	 * counts all movies for a specific year and country
	 * 
	 * @param year
	 */
	public void countMovies(String year) {

		Map<String, String> specificYear = new HashMap<String, String>();
		String[] movie;

		for (Map.Entry<String, String> entry : UserInterface.getAllYC()
				.entrySet()) {
			movie = entry.getValue().split("==");
			if (movie[0].equals(year)) {
				specificYear.put(entry.getKey(), movie[1]);
			}
		}

		for (Map.Entry<String, String> entry : UserInterface.getAllCountries()
				.entrySet()) {
			int count = 0;
			for (Map.Entry<String, String> entry1 : specificYear.entrySet()) {
				if (entry1.getValue().contains(entry.getKey())) {
					count++;
				}
			}

			if (count != 0) {
				countries.put(entry.getKey(), count);
			}
		}
	}
}