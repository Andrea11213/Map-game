package com.example;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {

        try(FileWriter locFile = new FileWriter("locations.txt");
            FileWriter dirFile = new FileWriter("directions.txt")){
            for(Location location : locations.values()){
                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
            for(String direction : location.getExits().keySet()){
                dirFile.write(location.getLocationID() + ", " + direction + "," + location.getExits().get(direction) + "\n");
            }
            }
        }

        //It creates a file writer object, passes the file name as a parameter to the constructor then uses the right method to write data to a file
//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for (Location location : locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                //  throw new IOException(("test exception thrown while writing"));
//            }
//        } finally {
//            System.out.println("In finally block");
//            if (locFile != null) {
//                System.out.println("Attempting to close locFile");
//                locFile.close();
//            }
//        }



    }


    static {

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))){
           // scanner = new Scanner(new FileReader("locations_big.txt")); // grab data form a file instead form a keyboard
            scanner.useDelimiter(","); // way to tell our scanner that our info is separated by comma
            while(scanner.hasNextLine()){
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter()); //skip the comma to move to next section
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String,Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //Now read the exits
        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))){
            //scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
           // Here above FileReader is passed to the buffer's constructor, and than bufferReader itself is passed into scanners constructor
            //scanner.useDelimiter(",");
            String input;
            while((input = dirFile.readLine()) != null){
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());// we are doing this to go to the next comma
//                // so we set comma as a delimiter, and now we are using it above to tell the scanner when to stop reading each piece of info
//                String direction = scanner.next();
//                scanner.skip(scanner.delimiter());// we are doing this to go to the n ext comma
//                String dest = scanner.nextLine(); // now we are reading nextLine because we now it is the end of the data there
//                int destination = Integer.parseInt(dest);
             //   String input = scanner.nextLine(); //reading entire line instead of using delimiter
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                //TODO problem with the spaces that is why game is not working well

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction,destination);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key); // returns whatever the key is that was pass to us
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
