package com.learn.e03.collectors;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class SpecialStreamsExercise {
	
	public static LinkedList<Integer> getSquaresOfThree() {
		return	IntStream.range(20, 50).filter(p->p%3==0 && p%2!=0).map(n -> n*n).collect(LinkedList :: new,LinkedList::add,LinkedList::addAll);
		
	}
	
	public static int[] getMultiplesOfFive() {
		return IntStream.iterate(5,num -> num+5).limit(20).toArray();
	}
	
	public static void main(String[] args) {
		System.out.println("------getSquaresOfThree method output------------- ");
		LinkedList<Integer> resList = getSquaresOfThree();
		System.out.println(resList);
		System.out.println("------getMultiplesOfFive method output------------- ");
		int [] resArr = getMultiplesOfFive();
		for (int i = 0; i < resArr.length; i++) {
			System.out.println(resArr[i]);
		}
		
	}

}
