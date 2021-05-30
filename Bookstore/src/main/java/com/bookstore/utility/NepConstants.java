package com.bookstore.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NepConstants {

public final static String Nep = "Nep";
	
	public final static Map<String, String> mapOfNepStates = new HashMap<String, String>() {
		{
			put("BR", "	Biratnagar");
			put("JK", "Janakpur");
			put("HTD", "Hetauda");
            put("PKR", "Pokhara");
            put("DKR", "Deukhuri");
            put("BIR", "Birendranagar");
            put("GD", "Godawari");
            put("KTM", "Kathmandu");
            
		}
	};
	
	public final static List<String> listOfNepStatesCode = new ArrayList<>(mapOfNepStates.keySet());
	public final static List<String> listOfNepStatesName = new ArrayList<>(mapOfNepStates.values());
}
