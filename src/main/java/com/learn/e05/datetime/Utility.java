package com.learn.e05.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {
	
	/*
	 * - getBusSchedule(String start, Duration frequency) : List<String> - Method
	 * should return a List of String containing the bus timings for a day given the
	 * start time and duration as parameters. - The timing in the list should be in
	 * 24 hour format hh:mm
	 */
	
	public static List<String>  getBusSchedule(String start, Duration frequency) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH::mm]");
		LocalTime startTime = LocalTime.parse(start);
		LocalTime endTime = LocalTime.MIDNIGHT;
		int gap = (int) frequency.toMinutes();
		List<String> busTimings = new ArrayList<String>();
		int startMinVal = startTime.getHour()*60 + startTime.getMinute();
		int endMinVal = endTime.minusHours(1).getHour()*60 + endTime.minusMinutes(1).getMinute();
		  while(endMinVal - startMinVal > gap) {
		  busTimings.add(startTime.format(formatter));
		  startTime = startTime.plusMinutes(gap);
		  startMinVal = startMinVal+gap;
		  
		  }
		  busTimings.add(startTime.format(formatter));
		  busTimings.forEach(System.out::println);
		
		return busTimings;
	}
	
	public static void main(String[] args) {
		
		getBusSchedule(LocalTime.of(5, 30).toString(),Duration.ofMinutes(30));
		
	}

}
