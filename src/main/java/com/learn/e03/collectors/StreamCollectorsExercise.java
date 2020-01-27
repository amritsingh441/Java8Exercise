package com.learn.e03.collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learn.e02.streams.Country;
import com.learn.e02.streams.Player;

public class StreamCollectorsExercise {

	private List<Player> playerList = new ArrayList<Player>();

	public StreamCollectorsExercise() {

		playerList.add(new Player("Vijay", 215, 6561, 823, new Country(1,"India")));
		playerList.add(new Player("Amrit", 121, 3700, 685, new Country(1,"India")));
		playerList.add(new Player("Bala", 11, 1561, 376, new Country(4,"WestIndies")));
		playerList.add(new Player("Entaz", 14, 2261, 190,  new Country(3,"SriLanka")));
		playerList.add(new Player("Gayatri", 221, 11561, 923, new Country(2,"Australia")));
		playerList.add(new Player("Chaman", 125, 1001, 62, new Country(3,"SriLanka")));
		playerList.add(new Player("Bhuvan", 215, 7001, 562, new Country(1,"India")));
		playerList.add(new Player("Lakha", 25, 1601, 79, new Country(4,"WestIndies")));
		playerList.add(new Player("Kachra", 25, 3001, 62, new Country(1,"India")));
	}

	public  Map<String, List<Player>> getPlayersByCountry() {
		return playerList.stream().collect(Collectors.groupingBy(player -> player.getCountry().getCountryName()));


	}

	public  Map<String, List<String>> getPlayerNamesByCountry() {
		return playerList.stream().filter(player -> player.getMatchesPlayed()>100)
				.collect(Collectors.groupingBy(player -> player.getCountry().getCountryName(),Collectors.mapping(Player :: getPlayerName, Collectors.toList())));


	}

	public  LinkedHashMap<String, Long> getTotalPlayersByCountry() {
		return playerList.stream().collect(Collectors.groupingBy(player ->player.getCountry().getCountryName(), LinkedHashMap::new, Collectors.counting()));

	}

	public  Map<String, Integer> getTotalRunsByCountry() {
		return playerList.stream().collect(Collectors.groupingBy(player ->player.getCountry().getCountryName(), Collectors.summingInt(Player::getRuns)));

	}

	public  Map<String, Player> getMaxScoresByCountry() {
		return playerList.stream()
				.collect(Collectors.groupingBy(player ->player.getCountry().getCountryName(),
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(Player :: getHighestScore))
								,Optional::get)));

	}

	public  Map<String, String> getPlayerNamesStringByCountry() {
		return playerList.stream()
				.collect(Collectors.groupingBy(player ->player.getCountry().getCountryName(),
						Collectors.mapping(Player :: getPlayerName, Collectors.joining(","))));

	}


	public static void main(String[] args) {
		StreamCollectorsExercise collectorsExercise = new StreamCollectorsExercise();

		System.out.println("------getPlayersByCountry method output------------- ");
		Map<String, List<Player>> gettingPlayersByCountry =collectorsExercise.getPlayersByCountry();
		System.out.println(gettingPlayersByCountry);
		System.out.println("------getPlayerNamesByCountry method output------------- ");
		Map<String, List<String>> gettingPlayerNamesByCountry =collectorsExercise.getPlayerNamesByCountry();
		System.out.println(gettingPlayerNamesByCountry);
		System.out.println("------getTotalPlayersByCountry method output------------- ");
		LinkedHashMap<String, Long>  gettingTotalPlayersByCountry =collectorsExercise.getTotalPlayersByCountry();
		System.out.println(gettingTotalPlayersByCountry);
		System.out.println("------gettingTotalRunsByCountry method output------------- ");
		Map<String, Integer> gettingTotalRunsByCountry = collectorsExercise.getTotalRunsByCountry();
		System.out.println(gettingTotalRunsByCountry);
		System.out.println("------gettingMaxScoresByCountry method output------------- ");
		Map<String, Player> gettingMaxScoresByCountry =  collectorsExercise.getMaxScoresByCountry();
		System.out.println(gettingMaxScoresByCountry);
		System.out.println("------gettingPlayerNamesStringByCountry method output------------- ");
		Map<String, String> gettingPlayerNamesStringByCountry =  collectorsExercise.getPlayerNamesStringByCountry();
		System.out.println(gettingPlayerNamesStringByCountry);
	}


}
