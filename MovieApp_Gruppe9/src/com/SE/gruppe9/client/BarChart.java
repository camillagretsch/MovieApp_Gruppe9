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

/**
 * drawBarcharts for visualizing the filtered dataset
 */
public class BarChart {
	
		VerticalPanel verticalPanel = new VerticalPanel();
		UserInterface ui = new UserInterface();
		private List<Integer> numberOfFilmsCountry = new ArrayList<Integer>();
		private List<Integer> numberOfFilmsLanguage = new ArrayList<Integer>();
		private List<Integer> numberOfFilmsGenre = new ArrayList<Integer>();
		
		private ArrayList<String> allCountries = new ArrayList<String>();
		private ArrayList<String> uniqueCountries = new ArrayList<String>();
		private ArrayList<String> allLanguages = new ArrayList<String>();
		private ArrayList<String> uniqueLanguages = new ArrayList<String>();
		private ArrayList<String> allGenres = new ArrayList<String>();
		private ArrayList<String> uniqueGenres = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();

		/**
		*  initialize Barchart for visualizing number of movies per Country 
		*/
		void createChartCountry() {
		   	RootLayoutPanel rp = RootLayoutPanel.get();

		    // Create a Dock Panel
		    final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		    dockLayoutPanel.setStyleName("dockpanel");
		    dockLayoutPanel.getElement().getStyle().setProperty("border", "solid lightblue 4px");

		    // Add text all around
		    dockLayoutPanel.addNorth(new HTML("oberer Rand"), 5);
		    // dockPanel.addEast(col.asWidget(), 1);
		    dockLayoutPanel.addWest(new HTML("linker Rand"), 15);
		    dockLayoutPanel.addSouth(new HTML("unterer Rand"), 5);

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
			
		
		/** draw Barchart for Country
		 * @param chart
		 */
		void drawChartCountry(ColumnChart chart) {
				
		setNumberOfFilmsCountry();
		for(int i=0; i< numberOfFilmsCountry.size(); i++){
			System.out.println(numberOfFilmsCountry.get(i)+", ");
		}

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
		options.setHeight(450);
		options.setWidth(850);
	

		// Draw the chart
		chart.draw(dataTable, options);
			
		}
		

//public void setLanguage(){
//	for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()){
//		String[] tmp = entry.getValue().split("==");
//		if (tmp[4].isEmpty() == false) {
//		language.add((tmp[4]));
//		}
//	}
//}

	/**
	 * set all country entries of the filtred table from hashmap to array 
	 */
		public void setCountry() {
			for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
				String[] tmp = entry.getValue().split("==");
				if (tmp[5].isEmpty() == false && !tmp[5].equals("{}")) {
					String[] temp = tmp[5].split(",");
					allCountries.addAll(Arrays.asList(temp));
				}
			}
			for(int i=0; i<allCountries.size(); i++){
				(allCountries.get(i)).trim();
			}
		}
//public void setGenre(){
//	for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()){
//		String[] tmp = entry.getValue().split("==");
//		if (tmp[6].isEmpty() == false) {
//		genre.add((tmp[6]));
//		}
//	}
//}

	/**
	 * calculate number of movies for each country
	 */
	private void setNumberOfFilmsCountry(){
		setCountry();
		
		uniqueCountries.addAll(removeDuplicates(allCountries));
//	Set<String> resultCountrySet = new TreeSet<String>(allCountries);
//	this.uniqueCountries = new ArrayList<String>(resultCountrySet);
		
	int counter=0;
		
		
	for(int i=0; i<uniqueCountries.size(); i++){
		counter = Collections.frequency(allCountries, uniqueCountries.get(i));
		numberOfFilmsCountry.add(counter);
	}
//		counter=0;
//		for(int j=0; j<allCountries.size(); j++){
//			if((uniqueCountries.get(i)).equalsIgnoreCase(allCountries.get(j)));
//					counter++;
//		} 
//		numberOfFilmsCountry.add(counter);
//		}
//	}

	}
	private ArrayList<String> removeDuplicates(ArrayList<String> list){
		Set<String> set = new HashSet<String>(list);
		ArrayList<String> uniqueList = new ArrayList<String>(set);
		
		  return uniqueList;
		}
	}


