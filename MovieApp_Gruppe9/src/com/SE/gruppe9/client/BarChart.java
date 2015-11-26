package com.SE.gruppe9.client;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
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


public class BarChart {
	
		VerticalPanel verticalPanel = new VerticalPanel();
		UserInterface ui = new UserInterface();
		private static Table table = new Table();
		private String[] language;
		private  String[] country;
		private String[] genre;
		private int[] numberOfFilmsCountry;
		private int[] numberOfFilmsLanguage;
		private int[] numberOfFilmsGenre;
		private String[] uniqueCountry;
		
		private static Map<String,String> allCountries = new HashMap<String,String>();
		private static DataImportServiceAsync filter = GWT.create(DataImportService.class);
		
		void createChartCountry() {
		   	RootLayoutPanel rp = RootLayoutPanel.get();

		    // Create a Dock Panel
		    final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		    dockLayoutPanel.setStyleName("dockpanel");
		    dockLayoutPanel.getElement().getStyle().setProperty("border", "solid lightblue 4px");

		    // Add text all around
		    dockLayoutPanel.addNorth(new HTML("oberer Rand"), 15);
		    // dockPanel.addEast(col.asWidget(), 1);
		    dockLayoutPanel.addWest(new HTML("linker Rand"), 15);

		    ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		    chartLoader.loadApi(new Runnable() {

		        @Override
		        public void run() {
		            
		            ColumnChart col = new ColumnChart();
		            verticalPanel.add(col);
		            BarChart barchart = new BarChart();
		            barchart.drawChartCountry(col);
		            dockLayoutPanel.add(verticalPanel);
		        }
		    });
		    rp.add(dockLayoutPanel);
		}
			

		void drawChartCountry(ColumnChart chart) {
				
		setNumberOfFilmsCountry();

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(uniqueCountry.length);

		for (int i = 0; i < uniqueCountry.length; i++) {
			dataTable.setValue(i, 0, uniqueCountry[i]);
		}
		for (int row = 0; row < numberOfFilmsCountry.length; row++) {
				dataTable.setValue(row, 1, numberOfFilmsCountry[row]);
			}
		

		// Set options
		ColumnChartOptions options = ColumnChartOptions.create();
		options.setFontName("Tahoma");
		options.setTitle("Number of films per Country");
		options.setHAxis(HAxis.create("Country"));
		options.setVAxis(VAxis.create("Number of Films"));

		// Draw the chart
		chart.draw(dataTable, options);
	}

public void setLanguage(){
	int i = 0;
	for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
		String[] tmp = entry.getValue().split("==");
		language[i] = tmp[4];
		i++;
	}
}

public void setCountry(){
	int i = 0;
	for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
		String[] tmp = entry.getValue().split("==");
		country[i] = tmp[5];
		i++;
	}
}

public void setGenre(){
	int i = 0;
	for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
		String[] tmp = entry.getValue().split("==");
		genre[i] = tmp[5];
		i++;
	}
}

private void setNumberOfFilmsCountry(){
	setCountry();
	List<String> allResultCountries = Arrays.asList(country);
	Set<String> resultCountrySet = new TreeSet<String>(allResultCountries);
	uniqueCountry = (String[])resultCountrySet.toArray();

	
	int counter=0;
	for(int i=0; i< uniqueCountry.length; i++){
		for(int j=0; j<country.length; i++){
			if(uniqueCountry[i]==country[j])
				counter++;
		}
		numberOfFilmsCountry[i]=counter;
	}
}


}

