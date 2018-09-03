/**
Simple application to track driving history for people.
Application was tested with 5 different text files.
     
Name: Ryan Evans
Date: 09/03/2018
*/
import java.io.*;
import java.util.*;

public class Driver_Processor{
     /**
     Creates a HashMap that will be used to store all of the drivers from the text file.
     */
     static HashMap<String, ArrayList<String>> tripsMap = new HashMap<String, ArrayList<String>>();
     public static void main(String[] args) throws IOException{
          try {
               //Reads the given file
               readFile();
               //Clears the map after completion
               tripsMap.clear();

          }catch (FileNotFoundException e) {
               System.out.println(e.getMessage());
          }

     }
     /**
     Method that reads the file provided and inserts all items fro the text file in to the HashMap mentioned above.
     Method also calls all other methods below.
     */
     public static void readFile() throws FileNotFoundException{
          Scanner read = new Scanner(new File("input.txt"));

          while (read.hasNextLine()) {
               //Gets the first word in the text file line
               String firstWord = read.next();
               //Creates an ArrayList to store all driver names
               ArrayList<String> driverList = new ArrayList<String>();
               //Checks if the first word is driver then adds that driver to the HashMap mentioned above
               if (firstWord.compareTo("Driver") == 0) {
                    String driver = read.next();
                    //The two zeros are used becuase the HashMap will automically override duplicates.
                    tripsMap.put(driver, new ArrayList<String>(Arrays.asList("0", "0", driver)));
                    //Clear the list for easier calculation
                    driverList.clear();
                    //Checks if the first word is trip
               }else if (firstWord.compareTo("Trip") == 0) {
                    //Creates variables for each spot of the text file that will be needed.
                    String driverName = read.next();
                    String startTime = read.next();
                    String endTime = read.next();
                    String miles = read.next();
                    //Calls method to get the difference in the time between the start and end.
                    String timeDifference = calculateDifference(startTime, endTime);
                    //Since the driverList is now empty (From above) I can add the difference in time and miles to the driverList
                    Collections.addAll(driverList, timeDifference, miles);
                    //Now to check if there is a key in the map that matches the driver name that we are at.
                    //If there is then we know we need more calculations
                    if (tripsMap.containsKey(driverName)) {
                         //Creating a string array of the current driver in the HashMap
                         String[] duplicateDriver = tripsMap.get(driverName).toArray(new String[tripsMap.get(driverName).size()]);
                         //Extracting the time from that String array above
                         int duplicateTime = Integer.parseInt(duplicateDriver[0]) + Integer.parseInt(timeDifference);
                         //Extracting the miles from that String above
                         double duplicateMiles = Double.parseDouble(duplicateDriver[1]) + Double.parseDouble(miles);
                         //Putting an updated driver in the HashMap wiht updated time and miles
                         tripsMap.put(driverName, new ArrayList<String>(Arrays.asList(Integer.toString(duplicateTime), Double.toString(duplicateMiles), driverName)));

                    }else{
                         //If this is not a duplicate, then just add the whole line
                         tripsMap.put(driverName, new ArrayList<String>(Arrays.asList(timeDifference, miles, driverName)));
                    }
                    //Clears the list for easier calculation
                    driverList.clear();
               }
          }
          //Prints the output
          print();

     }
   /**
   Method that will calculate the MPH with the miles and mintues.
   Method checks if the two numbers are not 0 and then calculates the MPH
   */
   public static int calculateMPH(String miles, String minutes){
        //Checks if input numbers are not 0
          if (miles != "0"  && minutes != "0" ) {
               //Creates double and int variables to store the miles and minutes
               double min = Double.parseDouble(minutes);
               double mil = Double.parseDouble(miles);
               //Calculates and rounds the MPH
               int mph = (int) Math.round(mil/min * 60);
               //Checks if it is over 100 or under 5 MPH
               if (mph < 5 || mph > 100) {
                    return 0;
               }else{
                    return mph;
               }
          //If 0 was entered for miles or mintues, just return it.
          }else {
               return 0;
          }
     }
     /**
     Method to get the difference between the start and end times.
     Method will work on any start and end times and allows for duplicates to be added together.
     */
     public static String calculateDifference(String start, String end){
          //4 variables to store the mintues and hours of the start and end inputs
          int startHour = Integer.parseInt(start.substring(0,2));
          int startMin = Integer.parseInt(start.substring(3,5));
          int endHour = Integer.parseInt(end.substring(0,2));
          int endMin = Integer.parseInt(end.substring(3,5));
          //Calculates the total hours
          int totalHours = endHour - startHour;
          //Calculates the total mintues
          int totalMinutes = endMin - startMin;
          //Converts the hours to mintues
          int convertHTM = totalHours * 60;
          //Returns the final string of time difference
          return Integer.toString((convertHTM + totalMinutes));
     }
     /**
     Method that sorts the HashMap mentioned above (tripsMap).
     Everything will be put in a treeMap to be sorted and then printed.
     */
      public static void print(){
           //Creates a TreeMap that will help sort the drivers by their miles
           Map<Integer, ArrayList<String>> treeMap = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
           //Creating a String array to store everything for access from the HashMap (tripsMap) and then putting them in the TreeMap based on the miles
           for (Map.Entry<String, ArrayList<String>> entry : tripsMap.entrySet()) {
               String[] driverInfo = tripsMap.get(entry.getKey()).toArray(new String[tripsMap.get(entry.getKey()).size()]);
               treeMap.put((int) Math.round(Double.parseDouble(driverInfo[1])), new ArrayList<String>(Arrays.asList(driverInfo)));
          }
          //Creates a set and Iterator to iterate over for printing
          Set set = treeMap.entrySet();
          Iterator it = set.iterator();
          //Loops over the whole set
          while(it.hasNext()) {
               //Gets the entry for the map
               Map.Entry<String, ArrayList<String>> entry = (Map.Entry<String, ArrayList<String>>)it.next();
               String[] driverInfo1 = treeMap.get(entry.getKey()).toArray(new String[treeMap.get(entry.getKey()).size()]);
               //Checks to see if the MPH was greater than 0 for output
               if (calculateMPH(driverInfo1[1], driverInfo1[0]) != 0) {
                    System.out.println(driverInfo1[2] + ": " + (int)Math.round(Double.parseDouble(driverInfo1[1])) + " miles @ " + calculateMPH(driverInfo1[1], driverInfo1[0]) + " mph");
              }else {
                   System.out.println(driverInfo1[2] + ": " + (int)Math.round(Double.parseDouble(driverInfo1[1])) + " miles");
              }
        }
   }
}
