package com.SE.gruppe9.client;

import java.util.Map;

public class BarChart {
		private Table table = new Table();
		private String[] language;
		private String[] country;
		private String[] genre;
		
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
}
