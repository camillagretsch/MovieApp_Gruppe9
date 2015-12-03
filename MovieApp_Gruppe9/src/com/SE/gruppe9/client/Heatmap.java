package com.SE.gruppe9.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.params.AllClientPNames;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.VerticalLayout;




public class Heatmap  {
	
	private Button backButton = new Button("back");
	private VerticalPanel verticalPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	final static RootLayoutPanel rp = RootLayoutPanel.get();
	
	private List<String> countries = new ArrayList<String>();
	private int[] nrOfMovies = new int[UserInterface.getAllCountries().size()];
	
	// creates a Panel and communicates with the HTML page
	public void createChart() {
		
		hPanel.add(backButton);
		// leave map
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rp.setVisible(false);
			}
		});
				
	    // Create a Dock Panel
	    final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
	    dockLayoutPanel.getElement().getStyle().setProperty("border", "solid lightblue 4px");

	    // Add text all around
	    dockLayoutPanel.addNorth(new HTML(""), 5);
	    dockLayoutPanel.addWest(new HTML(""), 15);
		
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				
				GeoChart geoChart = new GeoChart();
				verticalPanel.add(geoChart);
				Heatmap worldMap = new Heatmap();
				worldMap.draw(geoChart);
				dockLayoutPanel.add(verticalPanel);
			}
		});
		
		rp.add(dockLayoutPanel);
		rp.add(hPanel);
	}


	// Styles and draws a world map and also inserts data values into an array which
	// the map then visualises.
	private void draw(GeoChart geoChart) {
		countMovies("2015");
		DataTable dataTable = DataTable.create();
		
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(countries.size());
		for (int i = 0; i < countries.size(); i++){
			dataTable.setValue(i, 0, countries.get(i));
			dataTable.setValue(i, 1, nrOfMovies[i]);
		}
		
		
		
		JsArrayString ColourArray = JavaScriptObject.createArray().cast();
		ColourArray.push("#ff6666");
		ColourArray.push("#ffff99");
		ColourArray.push("#66ff66");
		
		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors(ColourArray);
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("#ffebe5");
		options.setBackgroundColor("#e5ffff");
		options.setHeight(450);
	

		// Draw the chart
		geoChart.draw(dataTable, options);
	}
	
	public void countMovies(String year) {
		Map<String, String> tmp = new HashMap<String, String>();
		
		for (Map.Entry<String, String> entry : UserInterface.getAllCountries().entrySet()) {
			countries.add(entry.getKey());
		}
		
		tmp.clear();
		for (Map.Entry<String, String> entry : UserInterface.getAllYears().entrySet()) {
			String[] tmp1 = entry.getValue().split("==");
			
			if (tmp1[1].equals(year)){
				tmp.put(entry.getKey(), tmp1[0]);
			}
		}
		
		for (int i = 0; i < countries.size(); i++) {
			
			int count = 0;
			
			for (Map.Entry<String, String> entry : tmp.entrySet()) {
				
				if (entry.getValue().contains(countries.get(i))) {
					count++;
				}
			}
			nrOfMovies[i] = count;
		}
		
	}
	
//	public void countMovieForCountries(String year) {
//		// Initialize the service proxy.
//		if (filter == null) {
//			filter = GWT.create(DataImportService.class);
//		}
//		// Set up the callback object.
//		final AsyncCallback<Map<String, String>> callback = new AsyncCallback<Map<String, String>>() {
//			public void onFailure(Throwable caught) {
//
//			}
//
//			public void onSuccess(Map<String, String> result){
//				for (int i = 0; i < countries.size(); i++){
//					int count = 0;
//					for (Map.Entry<String, String> entry : result.entrySet()) {
//						String[] tmp = entry.getValue().split("==");
//						
//						if (tmp[1].contains(countries.get(i))){
//							count++;
//						}
//					}
//					nrOfMovies[i] = count;
//				}		
//			}
//		};
//		filter.countMovies(year, callback);
//	}
}
