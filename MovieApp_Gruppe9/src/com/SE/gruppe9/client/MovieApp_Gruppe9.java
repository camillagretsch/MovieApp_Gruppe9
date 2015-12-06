package com.SE.gruppe9.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtextux.client.widgets.image.Image;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MovieApp_Gruppe9 implements EntryPoint {

	private UserInterface user = new UserInterface();
    private String year = null;
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
	 * 
	 * @return
	 */
	private Panel createBorderPanelTable() {

		Table table = user.choseEvents();

		Panel borderPanel = new Panel();
		Panel southPanel = new Panel();
		Panel centerPanel = new Panel();
		Panel westPanel = new Panel();
		Panel eastPanel = new Panel();

		// border Layout
		borderPanel.setLayout(new BorderLayout());

		// add south panel
		southPanel.setTitle("Source");
		southPanel.setHeight(45);
		southPanel
				.setHtml("David Bamman, Brendan O'Connor and Noah Smith, \"Learning Latent Personas of Film Characters,\" in: Proceedings of the Annual Meeting of the Association for Computational Linguistics (ACL 2013), Sofia, Bulgaria, August 2013.");

		BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);
		southData.setMinHeight(45);
		southData.setMaxSize(100);
		southData.setSplit(true);
		borderPanel.add(southPanel, southData);

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

		borderPanel.add(centerPanel,
				new BorderLayoutData(RegionPosition.CENTER));

		return borderPanel;
	}

	/**
	 * layout for the charts panel
	 * 
	 * @return
	 */
	private Panel createBorderPanelCharts() {

		Panel borderPanel = new Panel();
		Panel southPanel = new Panel();
		Panel centerPanel = new Panel();
		Panel westPanel = new Panel();
		Panel eastPanel = new Panel();

		Panel hPanel = new Panel();

		Button piechartButtonBOR = new Button(
				"Create Piechart for Box Office Revenue");
		Button piechartButtonRT = new Button("Create Piechart for runtime");
		Button barchartButtonCountry = new Button("Create Barchart for Country");
		Button barchartButtonLanguage = new Button(
				"Create Barchart for Language");
		Button barchartButtonGenre = new Button("Create Barchart for Genre");

		// border Layout
		borderPanel.setLayout(new BorderLayout());

		// add south panel
		southPanel.setTitle("Source");
		southPanel.setHeight(45);
		southPanel
				.setHtml("David Bamman, Brendan O'Connor and Noah Smith, \"Learning Latent Personas of Film Characters,\" in: Proceedings of the Annual Meeting of the Association for Computational Linguistics (ACL 2013), Sofia, Bulgaria, August 2013.");

		BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);
		southData.setMinHeight(45);
		southData.setMaxSize(100);
		southData.setSplit(true);
		borderPanel.add(southPanel, southData);

		// add east panel
		eastPanel.setTitle("Advertisement");
		eastPanel.setWidth(200);
		Image imageE = new Image();
		imageE.setWidth(200);
		imageE.setHeight(500);
		imageE.setSrc("images/banana.gif");
		eastPanel.add(imageE);
		borderPanel.add(eastPanel, new BorderLayoutData(RegionPosition.EAST));

		// add west Panel
		westPanel.setTitle("Advertisement");
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

		borderPanel.add(centerPanel,
				new BorderLayoutData(RegionPosition.CENTER));

		hPanel.setLayout(new HorizontalLayout(10));
		hPanel.add(piechartButtonBOR);
		hPanel.add(piechartButtonRT);
		hPanel.add(barchartButtonLanguage);
		hPanel.add(barchartButtonCountry);
		hPanel.add(barchartButtonGenre);

		// add button for creating a Box Office Revenue piechart
		piechartButtonBOR.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Piechart.rp.clear();
				Piechart.rp.setVisible(true);
				Piechart piechart = new Piechart();
				piechart.createChartBOR();
			}

		});

		// add button for creating a Runtime piechart
		piechartButtonRT.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Piechart.rp.clear();
				Piechart.rp.setVisible(true);
				Piechart piechart = new Piechart();
				piechart.createChartRT();
			}

		});

		// add button for creating a Country barchart
		barchartButtonCountry.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BarChart.rp.clear();
				BarChart.rp.setVisible(true);
				BarChart barchart = new BarChart();
				barchart.createChartCountry();
			}

		});

		// add button for creating a Language barchart
		barchartButtonLanguage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BarChart.rp.clear();
				BarChart.rp.setVisible(true);
				BarChart barchart = new BarChart();
				barchart.createChartLanguage();
			}
		});

		// add button for creating a Genre barchart
		barchartButtonGenre.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BarChart.rp.clear();
				BarChart.rp.setVisible(true);
				BarChart barchart = new BarChart();
				barchart.createChartGenre();
			}
		});

		return borderPanel;
	}

	/**
	 * layout for the map panel
	 * 
	 * @return
	 */
	private Panel createPanelMap() {

		Panel borderPanel = new Panel();
		Panel southPanel = new Panel();
		Panel centerPanel = new Panel();

		Panel hPanel = new Panel();
	
		final ListBox listBoxYear = new ListBox();
		Button heatMapButton = new Button("Create Heatmap");
		
		// border Layout
		borderPanel.setLayout(new BorderLayout());

		// add south panel
		southPanel.setTitle("Source");
		southPanel.setHeight(45);
		southPanel.setHtml("David Bamman, Brendan O'Connor and Noah Smith, \"Learning Latent Personas of Film Characters,\" in: Proceedings of the Annual Meeting of the Association for Computational Linguistics (ACL 2013), Sofia, Bulgaria, August 2013.");

		BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);
		southData.setMinHeight(45);
		southData.setMaxSize(100);
		southData.setSplit(true);
		borderPanel.add(southPanel, southData);

		// add centre Panel
		centerPanel.expand();
		centerPanel.add(hPanel);
		borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));
		
		hPanel.setLayout(new HorizontalLayout(10));
		listBoxYear.addItem("Year");
		listBoxYear.setVisibleItemCount(1);
		for (int i = 2016; i >= 1888; i--) {
			listBoxYear.addItem(Integer.toString(i));
		}
		hPanel.add(listBoxYear);
		hPanel.add(heatMapButton);
		
		// change event for listbox year
		listBoxYear.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int itemSelected = listBoxYear.getSelectedIndex();
				year = listBoxYear.getValue(itemSelected);
			}

		});
		heatMapButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Heatmap.rp.clear();
				Heatmap.rp.setVisible(true);
				Heatmap heatmap = new Heatmap();
				heatmap.createChart(year);
				listBoxYear.setSelectedIndex(0);
				year = null;
			}

		});

		return borderPanel;
	}
}
