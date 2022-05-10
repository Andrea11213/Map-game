package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
   // private static Map<Integer,Location> locations = new HashMap<>();
        private static Locations locations = new Locations();
    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);



        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT","Q");
        vocabulary.put("WEST","W");
        vocabulary.put("SOUTH","S");
        vocabulary.put("NORTH","N");
        vocabulary.put("EAST","E");
        System.out.println("Printing the locations info");
        for(Location location : locations.values()){
            System.out.println(location.getExits().keySet());
        }



        int loc = 1;
        while(true){
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0){
                break;
            }
            Map<String,Integer> exits = locations.get(loc).getExits();
            System.out.println("available exits are:");
            for(String exit: exits.keySet()){
                System.out.print(exit + ",");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1){
                String[] words = direction.split(" ");
                for(String word: words){
                    if(vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                    }
                }
            }



               if (exits.containsKey(direction)) {
                   loc = exits.get(direction);
               } else {
                   System.out.println("You can not go in that direction");
               }



        }
    }
}
