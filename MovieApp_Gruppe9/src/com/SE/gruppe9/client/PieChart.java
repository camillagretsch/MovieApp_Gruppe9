package com.SE.gruppe9.client;

import java.util.Map;

public class PieChart {
	private Table table = new Table();
	private Long[] bor;
	private Double[] runtime;
	
	public void setBOR(){
		int i = 0;
		for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
			String[] tmp = entry.getValue().split("==");
			bor[i] = Long.parseLong(tmp[2]);
			i++;
		}
	}
	
	public void setRuntime(){
		int i = 0;
		for (Map.Entry<String, String> entry : table.getResultMap().entrySet()){
			String[] tmp = entry.getValue().split("==");
			runtime[i] = Double.parseDouble(tmp[3]);
			i++;
		}
	}
}
