package com.SE.gruppe9.client;

import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtextux.client.data.PagingMemoryProxy;
import com.gwtextux.client.widgets.image.Image;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MovieApp_Gruppe9 implements EntryPoint {
	
	private UserInterface user = new UserInterface();
	
	private Panel panel = new Panel();
	private TabPanel tabPanel = new TabPanel();
	private Panel tablePanel = new Panel("Table");
	private Panel chartsPanel = new Panel("Charts");
	private Panel mapPanel = new Panel("Map");
	
	@Override
	public void onModuleLoad() {
		
		panel.setBorder(false);
		panel.setPaddings(15);
		panel.setLayout(new FitLayout());
		panel.add(tabPanel);

		// create tab panel
		tabPanel.setTabPosition(Position.TOP);
		tabPanel.setHtml("<h>Movie Visualiser Tool</h>");
		tabPanel.setResizeTabs(true);
		tabPanel.setMinTabWidth(115);
		tabPanel.setTabWidth(135);
		tabPanel.setActiveTab(0);

		// tab for table
		tablePanel.setLayout(new FitLayout());
		tablePanel.setIconCls("tab.icon");
		tablePanel.add(createBorderPanelTable());
		tabPanel.add(tablePanel);
		
		// tab for charts
		chartsPanel.setLayout(new FitLayout());
		chartsPanel.setIconCls("tab.icon");
		chartsPanel.add(createBorderPanelCharts());
		tabPanel.add(chartsPanel);

		// tab for map
		mapPanel.setLayout(new FitLayout());
		mapPanel.setIconCls("tab.icon");
		mapPanel.add(createPanelMap()); 
		tabPanel.add(mapPanel);
		  
		Viewport viewport = new Viewport(panel);
	}
	
	/**
	 * layout for the table panel
	 * @return
	 */
	private Panel createBorderPanelTable() {

		Table table = user.choseEvents();
		
		Panel borderPanel = new Panel();
		Panel northPanel = new Panel();
		Panel southPanel = new Panel();
		Panel centerPanel = new Panel();
		Panel westPanel = new Panel();
		Panel eastPanel = new Panel();
		
		// border Layout
		borderPanel.setLayout(new BorderLayout());

		// add south panel
		southPanel.setHtml("<p>Source</p>");
		southPanel.setHeight(50);
		borderPanel.add(southPanel, new BorderLayoutData(RegionPosition.SOUTH));

		// add east panel
		eastPanel.setTitle("Advertisement");
		eastPanel.setWidth(200);
		Image imageE = new Image();
		imageE.setWidth(200);
		imageE.setHeight(500);
		imageE.setSrc("images/mockingjay.jpg");
		eastPanel.add(imageE);
		borderPanel.add(eastPanel, new BorderLayoutData(RegionPosition.EAST));

		// add west Panel
		westPanel.setTitle("Advertisement");
		westPanel.setWidth(200);
		Image imageW = new Image();
		imageW.setWidth(200);
		imageW.setHeight(500);
		imageW.setSrc("images/hobbit.jpg");
		westPanel.add(imageW);
		borderPanel.add(westPanel, new BorderLayoutData(RegionPosition.WEST));

		// add centre Panel
		centerPanel.setAutoScroll(true);
		centerPanel.add(user.getHPanel());
		centerPanel.add(user.getVPanel());
		centerPanel.add(table.createFlexTable());


		borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));

		return borderPanel;
	}
	
	/**
	 * layout for the charts panel 
	 * @return
	 */
	private Panel createBorderPanelCharts() {
		Panel chartExtension = new Panel();
		
		Panel borderPanel = new Panel();
		Panel northPanel = new Panel();
		Panel southPanel = new Panel();
		Panel centerPanel = new Panel();
		Panel westPanel = new Panel();
		Panel eastPanel = new Panel();
		
		Panel hPanel = new Panel();
		Button piechartButtonBOR = new Button("Create Piechart for Box Office Revenue");
		Button piechartButtonRT = new Button("Create Piechart for runtime");
		Button barchartButtonCountry = new Button("Create Barchart for Country");
		
		// border Layout
		borderPanel.setLayout(new BorderLayout());
		
		// add south panel
		southPanel.setHtml("<p>Source</p>");
		southPanel.setHeight(50);
		borderPanel.add(southPanel, new BorderLayoutData(RegionPosition.SOUTH));

		// add east panel
		eastPanel.setTitle("Advertisemen");
		eastPanel.setWidth(200);
		Image imageE = new Image();
		imageE.setWidth(200);
		imageE.setHeight(500);
		imageE.setSrc("images/mockingjay.jpg");
		eastPanel.add(imageE);
		borderPanel.add(eastPanel, new BorderLayoutData(RegionPosition.EAST));

		// add west Panel
		westPanel.setTitle("Advertisemen");
		westPanel.setWidth(200);
		Image imageW = new Image();
		imageW.setWidth(200);
		imageW.setHeight(500);
		imageW.setSrc("images/xman.jpg");
		westPanel.add(imageW);
		borderPanel.add(westPanel, new BorderLayoutData(RegionPosition.WEST));
		
		// add centre Panel
		centerPanel.expand();
		centerPanel.add(hPanel);
		borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));
		
		hPanel.setLayout(new HorizontalLayout(10));
		hPanel.add(piechartButtonBOR);
		hPanel.add(piechartButtonRT);
		hPanel.add(barchartButtonCountry);
		
		// add button for creating a Box Office Revenue piechart
		piechartButtonBOR.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Piechart piechart = new Piechart();
					piechart.createChartBOR();
				}
						
			});
		
		// add button for creating a Runtime piechart
	    	piechartButtonRT.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Piechart piechart = new Piechart();
					piechart.createChartRT();
				}
						
			});  
	    	
	    // add button for creating a Country barchart
		barchartButtonCountry.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BarChart barchart = new BarChart();
				barchart.createChartCountry();
			}
							
		}); 		
		
		 chartExtension.setLayout(new BorderLayout());
	     chartExtension.add(chartsPanel);
	     
	     return borderPanel;
	}
	
	/**
	 * layout for the map panel 
	 * @return
	 */
	private Panel createPanelMap(){
		Panel mapExtension = new Panel();

		Panel hPanel = new Panel();
		Button heatMapButton = new Button("Create Heatmap");
		
		hPanel.setLayout(new HorizontalLayout(10));
		hPanel.add(heatMapButton);
		
		heatMapButton.addClickHandler(new ClickHandler() { 
			 public void onClick(ClickEvent event) { 
				 Heatmap heatmap = new Heatmap();
				 heatmap.createChart(); }
		 
		 });
		
		 mapExtension.setLayout(new BorderLayout());
		 mapExtension.add(mapPanel);
		 
		return hPanel;
	}
}
