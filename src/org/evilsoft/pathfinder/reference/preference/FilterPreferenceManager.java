package org.evilsoft.pathfinder.reference.preference;

import java.util.ArrayList;
import java.util.List;

import org.evilsoft.pathfinder.reference.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class FilterPreferenceManager {
	
	static Context context;

	public static String getSourceFilter(List<String> args, String conjunction) {
		StringBuffer filter = new StringBuffer();
		ArrayList<String> sourceList = new ArrayList<String>();
		
		// The default values must be set here to bypass a bug in Android
		// See http://stackoverflow.com/questions/3907830/android-checkboxpreference-default-value
		PreferenceManager.setDefaultValues(context, R.xml.source_filter, false);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		
		if(preferences.getBoolean("source_APG", true) == false) {
			sourceList.add("Advanced Player's Guide");
		}
		if(preferences.getBoolean("source_ARG", true) == false) {
			sourceList.add("Advanced Race Guide");
		}
		if(preferences.getBoolean("source_UC", true) == false) {
			sourceList.add("Ultimate Combat");
		}
		if(preferences.getBoolean("source_UM", true) == false) {
			sourceList.add("Ultimate Magic");
		}
		if(preferences.getBoolean("source_B1", true) == false) {
			sourceList.add("Bestiary");
		}
		if(preferences.getBoolean("source_B2", true) == false) {
			sourceList.add("Bestiary 2");
		}
		if(preferences.getBoolean("source_B3", true) == false) {
			sourceList.add("Bestiary 3");
		}
		if(preferences.getBoolean("source_GMG", true) == false) {
			sourceList.add("Game Mastery Guide");
		}
		if(sourceList.size() > 0) {
			// Create the start of the WHERE/AND clause
			filter.append(" ");
			filter.append(conjunction);
			filter.append(" source NOT IN (");
			String comma = "";
			for(int numFilters = 0; numFilters < sourceList.size(); numFilters++) {
				filter.append(comma);
				filter.append("?");
				comma = ", ";
			}
			// End the WHERE/AND clause
			filter.append(")");
		}
		args.addAll(sourceList);
		return filter.toString();
	}
	
	public static void setContext(Context setContext) {
		context = setContext;
	}
	
}
