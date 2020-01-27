package com.learn.e02.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationsExercise {

	private static List<Player> playerList;

	static {

		playerList = new ArrayList<Player>();
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

	public static void displayPlayers() {

		playerList.stream().forEach(player -> System.out.println(player.getPlayerName()));

	}

	public static void displayPlayersForCountry(String country) {

		List<String> countrySpecificPlayerList = playerList.stream()
				.filter(player -> player.getCountry().getCountryName().equalsIgnoreCase(country) && player.getHighestScore()>100)
				.map(p -> p.getPlayerName())
				.collect(Collectors.toList());

		countrySpecificPlayerList.forEach(System.out::println);


	}

	public static LinkedList<String> getPlayerName() {

		LinkedList<String> pList = playerList.stream().filter(player -> player.getRuns()>5000)
				.sorted(Comparator.comparing(Player :: getPlayerName).reversed())
				.map(p -> p.getPlayerName())
				.collect(Collectors.toCollection(LinkedList::new));

		return pList;

	}

	public static int getAverageRunsByCountry(String countryName) {

		Stream<Player> playerStream =playerList.stream().filter(players -> players.getCountry().getCountryName().equalsIgnoreCase(countryName));
		Double avgRunsInDouble = playerStream.collect(Collectors.averagingDouble((Player p) ->p.getRuns()));
		int avgRunsInInt = avgRunsInDouble.intValue();
		return avgRunsInInt;

	}

	public static List<String> getPlayerNamesSorted() {

		List<String> playerNamesList = 	playerList.stream()
				.sorted(Comparator.comparing((Player p) -> p.getCountry().getCountryName())
						.thenComparing((Player p) -> p.getMatchesPlayed()).reversed())
				.map(p -> p.getPlayerName())
				.collect(Collectors.toList());
		return playerNamesList;

	}

	public static Map<String, String> getPlayerCountry() {

		Map<String, String> playerMap =	playerList.stream()
				.filter(p -> p.getMatchesPlayed()>200)
				.collect(Collectors.toMap(p -> p.getPlayerName(), p -> p.getCountry().getCountryName()));
		return playerMap;

	}

	public static String getMaxRunPlayer() {

		return playerList.stream().max(Comparator.comparing(Player :: getRuns))
				.map(p -> p.getPlayerName()).get();

	}

	public static void findPlayer(String name, String country) {
		
		Optional<Player> studOptional =	playerList.stream()
				.filter(player -> player.getPlayerName().equalsIgnoreCase(name) && player.getCountry().getCountryName().equalsIgnoreCase(country)).findAny();
		studOptional.ifPresent(System.out::println);
	
	}
	
	public static boolean checkHighScorerByCountry(String country) {
		
	boolean playerFound = playerList.stream().anyMatch(player -> player.getCountry().getCountryName().equalsIgnoreCase(country) && player.getRuns() > 10000);
	return playerFound;
	
	}
	
	public static void main(String[] args) {
		System.out.println("-------displayPlayers method-------------");
		displayPlayers();
		System.out.println("-------displayPlayersForCountry method-------------");
		displayPlayersForCountry("India");
		System.out.println("-------getPlayerName method-------------");
		LinkedList<String> playerNamesList = getPlayerName();
		playerNamesList.forEach(System.out::println);
		System.out.println("--------getAverageRunsByCountry method------------");
		int avgRuns = getAverageRunsByCountry("India");
		System.out.println("avgRuns are :: "+avgRuns);
		System.out.println("-------getPlayerCountry method-------------");
		Map<String, String> playersMap = getPlayerCountry();
		System.out.println(playersMap);
		System.out.println("-------getMaxRunPlayer method-------------");
		String maxRunPlayer = getMaxRunPlayer();
		System.out.println(maxRunPlayer);
		System.out.println("--------getPlayerNamesSorted method------------");
		List<String> playersNamesSortedStr = getPlayerNamesSorted();
		playersNamesSortedStr.forEach(System.out::println);
		System.out.println("--------findPlayer method------------");
		findPlayer("Amrit", "India");
		System.out.println("--------checkHighScorerByCountry method------------");
		boolean result = checkHighScorerByCountry("Australia");
		System.out.println("Player found :: "+result);

	}

}
