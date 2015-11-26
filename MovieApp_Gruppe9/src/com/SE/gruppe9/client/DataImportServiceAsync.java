package com.SE.gruppe9.client;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>DataImportService</code>.
 */
public interface DataImportServiceAsync {
	void filterData(String name, int column,
			AsyncCallback<Map<String, String>> callback);

	void getAllLanguages(
			AsyncCallback<Map<String, String>> callback);
	
	void getAllCountries(
			AsyncCallback<Map<String, String>> callback);
	
	void getAllGenres(
			AsyncCallback<Map<String, String>> callback);
}
