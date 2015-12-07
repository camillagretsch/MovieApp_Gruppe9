package com.SE.gruppe9.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.widgets.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwtTableToExcel.client.TableToExcelClient;

public class Export {
	// Names of the Columns for the Excel Table
	private String [] columnLabel = {"Wikipedia ID", "Movie Name", "Release Year", "Box Office Revenue", 
			"Runtime", "Language", "Country", "Genre"};
	private static final String SOURCE = "David Bamman, Brendan O'Connor and Noah Smith, \"Learning Latent Personas of Film Characters,\" in: Proceedings of the Annual Meeting of the Association for Computational Linguistics (ACL 2013), Sofia, Bulgaria, August 2013.";
	private static final int NUMBER_OF_COLUMNS = 8;
	public  DockLayoutPanel excelPanel = new DockLayoutPanel(Unit.EM);
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private final Button backButton = new Button("Back");
	final static RootLayoutPanel rp = RootLayoutPanel.get();

	public DockLayoutPanel export(){
		System.out.println("Export initialition");
		Grid gridToExport = DataTableToGrid();
//		@SuppressWarnings("deprecation")
		TableToExcelClient tableToExcelClient = new TableToExcelClient(gridToExport);
//		excelPanel.add(tableToExcelClient.getExportFormWidget());
		excelPanel.getElement().getStyle().setBackgroundColor("white");
		excelPanel.getElement().getStyle().setColor("red");
		excelPanel.addNorth(tableToExcelClient.getExportFormWidget(), 15);
		excelPanel.addNorth(backButton, 10);
//		excelPanel.addButton(backButton);
		
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rp.clear();
				rp.setVisible(false);
			}
		});
		
		
		System.out.println("Export has been initialised.");
		return excelPanel;
	}
	public Grid DataTableToGrid() {
		System.out.println("Export will be started.");
		Grid gridToExport = new Grid(Table.getResultMap().size() + 2, 8);
		
		for (int i=0; i < NUMBER_OF_COLUMNS; i++) {
			gridToExport.setText(0, i, columnLabel[i]);
			System.out.println(gridToExport.getText(0, i));
		}
	System.out.println("------Movies: --------");
	int i = 0;
		for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
			String[] tmp1 = entry.getValue().split("==");
			String[] tmp2 = new String[NUMBER_OF_COLUMNS];
			tmp2 [0] = entry.getKey().toString();
			for(int j = 0; j < NUMBER_OF_COLUMNS - 1; j++){
				tmp2[j+1] = tmp1[j];
				// System.out.println(tmp2[k]);
			}
			 //System.out.println("--------------------");
			for(int k = 0; k < NUMBER_OF_COLUMNS; k++){
				gridToExport.setText(i+1, k, tmp2[k]);
				System.out.println(gridToExport.getText(i+1, k));
			}
			System.out.println("---------------");
			i++;
		}
		
		gridToExport.setText(gridToExport.getRowCount()-1, 0, "Source:");
		gridToExport.setText(gridToExport.getRowCount()-1, 1, SOURCE);
		System.out.println(gridToExport.getText(gridToExport.getRowCount()-1, 0));
		System.out.println("GridSize: " + gridToExport.getRowCount());
		return gridToExport;
	}
}
