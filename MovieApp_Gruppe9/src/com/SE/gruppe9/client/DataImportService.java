package com.SE.gruppe9.client;



import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("datas")
public interface DataImportService extends RemoteService {
	
	Map<String, String> filterData(String name, int column);
	
	Map<String, String> getAllLCG();

	Map<String, String> countMovies();
	
	
}
