package com.SE.gruppe9.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.event.ReadyEvent;
import com.googlecode.gwt.charts.client.event.ReadyHandler;
import com.googlecode.gwt.charts.client.corechart.PieChart;



public class Piechart {
	private Table table = new Table();
	private Long[] bor;
	private Double[] runtime;
	private int[] numberOfFilmsBOR;
	private int[] numberOfFilmsRT;
	Map<String, String> map = new HashMap<String, String>();
	
		
		void createChartBOR() {
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
		            PieChart col = new PieChart();
		            verticalPanel.add(col);
		            Piechart piechart = new Piechart();
		            piechart.drawChartBOR(col);
		            dockLayoutPanel.add(verticalPanel);
		        }
		    });
		    rp.add(dockLayoutPanel);
		}
			

			void drawChartBOR(final PieChart chart) {
				
				//set the number of films per Range of Box Office Revenue
				Piechart piechart = new Piechart();
				piechart.setNumberOfFilmsBOR();
				
				// Prepare the data
				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "Box Office Revenue");
				dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
				dataTable.addRows(3);
				dataTable.setValue(0, 0, "<= 100'000");
				dataTable.setValue(0, 1, numberOfFilmsBOR[0]);
				dataTable.setValue(1, 0, "<= 1'000'000");
				dataTable.setValue(1, 1, numberOfFilmsBOR[1]);
				dataTable.setValue(2, 0, "> 1'000'000");
				dataTable.setValue(2, 1, numberOfFilmsBOR[2]);
			

				// Set options
				PieChartOptions options = PieChartOptions.create();
				options.setBackgroundColor("#f0f0f0");

				// options.setColors(colors);
				options.setFontName("Tahoma");
				options.setIs3D(false);
				options.setPieResidueSliceColor("#000000");
				options.setPieResidueSliceLabel("Others");
				options.setSliceVisibilityThreshold(0.1);
				options.setTitle("Number of Films per Range of Box Office Revenue");

				// Draw the chart
				chart.draw(dataTable, options);
				chart.addReadyHandler(new ReadyHandler() {

					@Override
					public void onReady(ReadyEvent event) {
						final JsArray<Selection> selectionArray = Selection.createArray().cast();
						selectionArray.setLength(1);

						final Selection selection = Selection.create(1, null).cast();
						selectionArray.set(0, selection);
				
					}
				});

			}
	
			
			void createChartRT() {
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
			            PieChart col = new PieChart();
			            verticalPanel.add(col);
			            Piechart piechart = new Piechart();
			            piechart.drawChartRT(col);
			            dockLayoutPanel.add(verticalPanel);
			        }
			    });
			    rp.add(dockLayoutPanel);
			}
				

				void drawChartRT(final PieChart chart) {
					
					//set the number of films per Range of Box Office Revenue
					Piechart piechart = new Piechart();
					piechart.setNumberOfFilmsRT();
					
					// Prepare the data
					DataTable dataTable = DataTable.create();
					dataTable.addColumn(ColumnType.STRING, "Box Office Revenue");
					dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
					dataTable.addRows(3);
					dataTable.setValue(0, 0, "<= 60 min");
					dataTable.setValue(0, 1, numberOfFilmsRT[0]);
					dataTable.setValue(1, 0, "<= 90 min");
					dataTable.setValue(1, 1, numberOfFilmsRT[1]);
					dataTable.setValue(2, 0, "> 90 min");
					dataTable.setValue(2, 1, numberOfFilmsRT[2]);
				

					// Set options
					PieChartOptions options = PieChartOptions.create();
					options.setBackgroundColor("#f0f0f0");

					// options.setColors(colors);
					options.setFontName("Tahoma");
					options.setIs3D(false);
					options.setPieResidueSliceColor("#000000");
					options.setPieResidueSliceLabel("Others");
					options.setSliceVisibilityThreshold(0.1);
					options.setTitle("Number of Films per Range of Runtime");

					// Draw the chart
					chart.draw(dataTable, options);
					chart.addReadyHandler(new ReadyHandler() {

						@Override
						public void onReady(ReadyEvent event) {
							final JsArray<Selection> selectionArray = Selection.createArray().cast();
							selectionArray.setLength(1);

							final Selection selection = Selection.create(1, null).cast();
							selectionArray.set(0, selection);
					
						}
					});

				}
	
	/**
	 *  set all box office revenue entries 
	 * 	from hashmap to array 	
	 */
	public void setBOR(){
		int i = 0;
		for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
			String[] tmp = entry.getValue().split("==");
			bor[i] = Long.parseLong(tmp[2]);
			}
			i++;
		}
	
	/**
	 * set all runtime entries 
	 * from hashmap to array 
	 */
	public void setRuntime(){
		int i = 0;
		for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
			String[] tmp = entry.getValue().split("==");
			runtime[i] = Double.parseDouble(tmp[3]);
			i++;
		}
	}
	
	/**
	 * calculate all movies with the same box office revenue range
	 */
	public void setNumberOfFilmsBOR(){
		int counter1 = 0;
		int counter2= 0;
		int counter3 = 0; 
		
		for (int i=0; i< bor.length; i++){
			if (bor[i]<=100000)
				counter1++;
			else if (bor[i]<=1000000)
				counter2++;
			else if (bor[i]>1000000)
				counter3++;
		}
		numberOfFilmsBOR[0]=counter1;
		numberOfFilmsBOR[1]=counter2;
		numberOfFilmsBOR[2]=counter3;
	}
	
	/**
	 * calculate all movies with the same runtime range
	 */
	public void setNumberOfFilmsRT(){
		int counter1 = 0;
		int counter2= 0;
		int counter3 = 0; 
		
		for (int i=0; i< runtime.length; i++){
			if (runtime[i]<=60)
				counter1++;
			else if (runtime[i]<=90)
				counter2++;
			else if (runtime[i]>90)
				counter3++;
		}
		numberOfFilmsRT[0]=counter1;
		numberOfFilmsRT[1]=counter2;
		numberOfFilmsRT[2]=counter3;
	}
}
