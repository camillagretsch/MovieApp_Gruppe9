package com.SE.gruppe9;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.SE.gruppe9.client.*;
import com.google.gwt.junit.GWTMockUtilities;

public class TableTests {

	@BeforeClass
	public static void disarm() {

		GWTMockUtilities.disarm();
	}

	@AfterClass
	public static void rearm() {

		GWTMockUtilities.restore();
	}

	/**
	 * 
	 * Testing Methods of the Table-Class create conflicts with GWT,
	 * 
	 * since there are some visualisations included
	 */
	@Ignore
	@Test
	public void secondFilterTest() {

		Table table = new Table();

		Map<String, String> mapBeforeSecondFilter = new HashMap<String, String>();

		Map<String, String> mapAfterSecondFilter = new HashMap<String, String>();

		table.setTestMap();

		for (Map.Entry<String, String> entry : table.getResultMap().entrySet()) {

			mapBeforeSecondFilter.put(entry.getKey(), entry.getValue());

		}

		table.secondFilter("English Language", 4);

		for (Map.Entry<String, String> entry : table.getResultMap().entrySet()) {

			mapAfterSecondFilter.put(entry.getKey(), entry.getValue());

		}

		assertFalse(
				"mapAfterSorting should not be the same as mapAfterSorting",
				equalMaps(mapAfterSecondFilter, mapBeforeSecondFilter));

		// assertFalse("mapAfterSorting must be smaller then mapBeforeSorting",
		// mapAfterSecondFilter.size() < mapBeforeSecondFilter.size());

		// assertTrue(equalMaps(mapBeforeSecondFilter, mapAfterSecondFilter));

	}

	boolean equalMaps(Map<String, String> m1, Map<String, String> m2) {

		if (m1.size() != m2.size())

			return false;

		for (String key : m1.keySet())

			if (!m1.get(key).equals(m2.get(key)))

				return false;

		return true;

	}

}