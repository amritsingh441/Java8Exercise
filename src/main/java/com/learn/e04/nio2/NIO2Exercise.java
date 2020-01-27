package com.learn.e04.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIO2Exercise {

	public static Map<String, String> getExpiredTablets(String fileName, String manufacturer) {

		//try(Stream<String> fileData = Files.lines(Paths.get("data","input.txt")))
		try(Stream<String> fileData = Files.newBufferedReader(Paths.get("src/data",fileName)).lines())
		{
			List<String []> records = fileData.map(input ->{String [] str = input.split(","); 
			return str;}).collect(Collectors.toList());

			Date date = new Date();
			SimpleDateFormat formatter = new  SimpleDateFormat("dd/MM/yyyy");
			String todayDate = formatter.format(date);
			//records.forEach(str -> System.out.println(str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]));
			Map<String,String> resMap =	records.stream().filter(str ->str[1].equalsIgnoreCase(manufacturer) && dateCompare(todayDate,str[3]))
					.collect(Collectors.toMap(str -> str[0], str -> str[3]));
			return resMap;
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return null;

	}
	
	public static boolean dateCompare(String todayDate,String expiryDate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if(sdf.parse(todayDate).after(sdf.parse(expiryDate))) {
				return true;
			}
		}catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}

		return false;
	}

	public static void getAllJavaFilesFromDirectory() {

		try(Stream<Path> path = Files.walk(Paths.get("src")))
		{
			path.filter(s -> s.toString().endsWith(".java")).forEach(System.out::println);

		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

	public static boolean checkIfFileIsPresent(String fileName, String absolutePath) {

		try(Stream<Path> path = Files.walk(Paths.get("src")))
		{
			Long totalCount = path.filter(s -> s.toString().contains(fileName) && s.toString().contains(absolutePath)).count();
			if(totalCount>0) {
				return true;
			}
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		return false;
	}

	public static void main(String[] args) {

		getAllJavaFilesFromDirectory();
		System.out.println("--------------------------");
		Map<String, String> expiredTabletData = getExpiredTablets("input.txt","gsk");
		for (String str : expiredTabletData.keySet()) {
			System.out.println(str+" "+expiredTabletData.get(str));

		}
		System.out.println("--------------------------");
		boolean present = checkIfFileIsPresent("input.txt","src/data");
		System.out.println("File present status :: "+present);

	}

}
