package com.SE.gruppe9.client;

import java.util.Map;
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
	
		private Table table = new Table();
		private String[] language;
		private String[] country;
		private String[] genre;
		private int[] numberOfFilmsCountry;
		private int[] numberOfFilmsLanguage;
		private int[] numberOfFilmsGenre;
		
		
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
		            VerticalPanel verticalPanel = new VerticalPanel();
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
				
							
				//setNumberOfFilmsCountry();

				// Prepare the data
				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "Country");
				dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
				dataTable.addRows(country.length);

				for (int i = 0; i < country.length; i++) {
					dataTable.setValue(i, 0, country[i]);
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
			
		/**
		 * set all language entries 
		 * from hashmap to array 
		 */
		public void setLanguage(){
			int i = 0;
			for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
				String[] tmp = entry.getValue().split("==");
				language[i] = tmp[4];
				i++;
			}
		}
		
		/**
		 * set all country entries 
		 * from hashmap to array 
		 */
		public void setCountry(){
			int i = 0;
			for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
				String[] tmp = entry.getValue().split("==");
				country[i] = tmp[5];
				i++;
			}
		}
		
		/**
		 * set all genre entries 
		 * from hashmap to array 
		 */
		public void setGenre(){
			int i = 0;
			for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
				String[] tmp = entry.getValue().split("==");
				genre[i] = tmp[5];
				i++;
			}
		}
		
		
//		private void setNumberOfFilmsCountry(){
//			
//		}
}

