package com.SE.gruppe9.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
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
	
	VerticalPanel verticalPanel = new VerticalPanel();
	UserInterface ui = new UserInterface();
	
	private Map<String, String> resultMap = new HashMap<String, String>();
	private Map<String, String> tmpMap = new HashMap<String, String>();
	private boolean tableIsEmpty;
	private DataImportServiceAsync filter = GWT.create(DataImportService.class);

	// creates a Panel and communicates with the HTML page
	public void createChart() {
	
		RootLayoutPanel rp = RootLayoutPanel.get();
		
	    // Create a Dock Panel
	    final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
	    dockLayoutPanel.setStyleName("dockpanel");
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
	}
	

	// Styles and draws a world map and also inserts data values into an array which
	// the map then visualises.
	private void draw(GeoChart geoChart) {
		
		
		JsArrayString ColourArray = JavaScriptObject.createArray().cast();
		ColourArray.push("#ff6666");
		ColourArray.push("#ffff99");
		ColourArray.push("#66ff66");
		
		// Prepare the data
		DataTable dataTable = DataTable.create();
		
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
						
		dataTable.addRows(1);
		dataTable.setValue(0, 0, "Germany");
		dataTable.setValue(0, 1, 600);
//		
//		dataTable.setValue(1, 0, "United Kingdom");
//		dataTable.setValue(1, 1, 300);
//		
//		dataTable.setValue(2, 0, "Brazil");
//		dataTable.setValue(2, 1, 400);
//		
//		dataTable.setValue(3, 0, "Canada");
//		dataTable.setValue(3, 1, 500);
//		
//		dataTable.setValue(4, 0, "France");
//		dataTable.setValue(4, 1, 600);
//		
//		dataTable.setValue(5, 0, "RU");
//		dataTable.setValue(5, 1, 700);

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
}
