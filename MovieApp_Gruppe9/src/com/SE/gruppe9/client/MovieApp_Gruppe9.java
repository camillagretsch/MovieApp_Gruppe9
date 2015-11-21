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
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MovieApp_Gruppe9 implements EntryPoint {
	private Panel panel = new Panel();
	private TabPanel tabPanel = new TabPanel();
	private Panel tablePanel = new Panel("Table");
	private Panel chartsPanel = new Panel("Charts");
	private Panel mapPanel = new Panel("Map");
	private Panel borderPanel = new Panel();
	private Panel northPanel = new Panel(); 
	private Panel southPanel = new Panel();  
	private Panel centerPanel = new Panel();
	private Panel westPanel = new Panel();
	private Panel eastPanel = new Panel();
	
	public void onModuleLoad() {
		
	        panel.setBorder(false);  
	        panel.setPaddings(15);  
	        panel.setLayout(new FitLayout());  
	        panel.add(tabPanel);
	        
	        // create tab panel 
	        tabPanel.setTabPosition(Position.TOP);  
	        tabPanel.setResizeTabs(true);  
	        tabPanel.setMinTabWidth(115);  
	        tabPanel.setTabWidth(135);  
	        tabPanel.setActiveTab(0);  
	        
	        // tab for table
	        tablePanel.setLayout(new FitLayout());  
	        tablePanel.setIconCls("tab.icon");
	        tabPanel.add(tablePanel);
	        
	        // tab for charts
	        chartsPanel.setLayout(new FitLayout());
	        chartsPanel.setIconCls("tab.icon");
	        tabPanel.add(chartsPanel); 
	        
	        // tab for map
	        mapPanel.setLayout(new FitLayout());
	        mapPanel.setIconCls("tab.icon");
	        tabPanel.add(mapPanel);
	        
	  
	        // border Layout
	        borderPanel.setLayout(new BorderLayout());  
	  
	        //add north panel  
	        northPanel.setHtml("<h>Movie Visualiser Tool</h>");  
	        northPanel.setHeight(32);    
	        borderPanel.add(northPanel, new BorderLayoutData(RegionPosition.NORTH));  
	  
	        //add south panel   
	        southPanel.setHtml("<p>Source</p>");  
	        southPanel.setHeight(10);   
	        borderPanel.add(southPanel, new BorderLayoutData(RegionPosition.SOUTH));  
	  
	        //add east panel   
	        eastPanel.setHtml("<p>Advertisement</p>");  
	        eastPanel.setWidth(40);
	        borderPanel.add(eastPanel, new BorderLayoutData(RegionPosition.EAST));  
	        
	        //add west Panel
	        westPanel.setHtml("<p>Advertisement</p>");  
	        westPanel.setWidth(40);  
	        borderPanel.add(westPanel, new BorderLayoutData(RegionPosition.WEST));  
	        
	        //add centre Panel
	        borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));  
	  
	       tablePanel.add(borderPanel);  
	        
	        Viewport viewport = new Viewport(panel);  
	    }    	
}
