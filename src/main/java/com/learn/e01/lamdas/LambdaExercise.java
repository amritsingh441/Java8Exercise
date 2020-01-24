package com.learn.e01.lamdas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaExercise {

	private static List<String> countries;
	private static Map<String, String> countryCapitals;

	static {
		/* country list initialization */
		countries = new ArrayList<String>();
		countries.add("India");
		countries.add("Australia");
		countries.add("Pakistan");
		countries.add("China");
		countries.add("Canada");
		countries.add("BanglaDesh");
		/* country capital map initialization */
		countryCapitals = new HashMap<String, String>();
		countryCapitals.put("India", "New Delhi");
		countryCapitals.put("Australia", "Canberra");
		countryCapitals.put("Pakistan", "Islamabaad");
		countryCapitals.put("China", "Beijing");
		countryCapitals.put("Canada", "Ottawa");
		countryCapitals.put("Bangladesh", "Dhaka");
	}

	public static void displayCountries() {

		countries.forEach(s -> System.out.println(s));

	}

	public static void displayCountryCapitals() {

		countryCapitals.entrySet().forEach(e -> System.out.println(e));

	}

	public static void sortCountriesByName() {

		countries.sort((c1,c2) -> - c1.compareTo(c2));
		countries.forEach(System.out::println);

	}

	public static void sortCountriesByLength() {

		countries.sort((c1,c2) -> c1.length()>c2.length()? -1:c1.length()<c2.length()? 1:c1.compareTo(c2));
		countries.forEach(System.out::println);

	}

	public static void removeCountry(String name) {

		countries.removeIf(s -> s.length()>6 && s.equalsIgnoreCase(name));
		countries.forEach(System.out::println);

	}

	public static void main(String[] args) {
		displayCountries(); 
		displayCountryCapitals(); 
		sortCountriesByName(); 
		sortCountriesByLength();
		removeCountry("Australia");
	}

}
