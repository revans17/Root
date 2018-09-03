import java.io.*;
import java.util.*;

public class Driver_Processor{
     static HashMap<String, ArrayList<String>> tripsMap = new HashMap<String, ArrayList<String>>();
     public static void main(String[] args) {

          try {
               readFile();
               tripsMap.clear();

          }catch (FileNotFoundException e) {
               System.out.println(e.getMessage());
          }

     }

     public static void readFile() throws FileNotFoundException{
          Scanner read = new Scanner(new File("input.txt"));

          while (read.hasNextLine()) {
               String firstWord = read.next();
               ArrayList<String> driverList = new ArrayList<String>();
               if (firstWord.compareTo("Driver") == 0) {
                    String driver = read.next();
                    tripsMap.put(driver, new ArrayList<String>(Arrays.asList("0", "0")));
                    driverList.clear();
               }else if (firstWord.compareTo("Trip") == 0) {
                    String driverName = read.next();
                    String startTime = read.next();
                    String endTime = read.next();
                    String miles = read.next();
                    String timeDifference = calculateDifference(startTime, endTime);
                    Collections.addAll(driverList, timeDifference, miles);
                    if (tripsMap.containsKey(driverName)) {
                         String[] duplicateDriver = tripsMap.get(driverName).toArray(new String[tripsMap.get(driverName).size()]);

                         int duplicateTime = Integer.parseInt(duplicateDriver[0]) + Integer.parseInt(timeDifference);

                         double duplicateMiles = Double.parseDouble(duplicateDriver[1]) + Double.parseDouble(miles);

                         tripsMap.put(driverName, new ArrayList<String>(Arrays.asList(Integer.toString(duplicateTime), Double.toString(duplicateMiles), driverName)));

                    }else{
                         tripsMap.put(driverName, new ArrayList<String>(Arrays.asList(timeDifference, miles, driverName)));
                    }

                    driverList.clear();
               }
          }
          print();
          // for (Map.Entry entry : tripsMap.entrySet()) {
          //      System.out.println(entry.getKey() + ", " + entry.getValue());
          // }
     }

    //  public static <K, V> void printMap(Map<K, V> map) {
    //     for (Map.Entry<K, V> entry : map.entrySet()) {
    //         System.out.println("Key : " + entry.getKey()
	// 			+ " Value : " + entry.getValue());
    //     }
    // }
    //
      public static void print(){
           Map<Integer, ArrayList<String>> treeMap = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
    //       for (Map.Entry entry : tripsMap.entrySet()) {
    //           String[] driverInfo = tripsMap.get(entry.getKey()).toArray(new String[tripsMap.get(entry.getKey()).size()]);
    //           treeMap.put((int) Math.round(Double.parseDouble(driverInfo[1])), new ArrayList<String>(Arrays.asList(driverInfo)));
    //
    //            //calculateMPH(driverInfo[1], driverInfo[0])
    //
    //
    //            //System.out.println("a " + driverInfo[0]);
    //       //System.out.println(Arrays.toString(driverInfo));
    //  }
    //
    //
    //  printMap(treeMap);
    //
    //  for (Map.Entry entry : treeMap.entrySet()) {
    //       String[] driverInfo = treeMap.get(entry.getKey()).toArray(new String[tripsMap.get(entry.getKey()).size()]);
    //       treeMap.put((int) Math.round(Double.parseDouble(driverInfo[1])), new ArrayList<String>(Arrays.asList(driverInfo)));
    //            if (calculateMPH(driverInfo[1], driverInfo[0]) != 0) {
    //                 System.out.println(entry.getKey() + ": " + (int)Math.round(Double.parseDouble(driverInfo[1])) + " miles @ " + calculateMPH(driverInfo[1], driverInfo[0]) + " mph");
    //            }else {
    //                 System.out.println(entry.getKey() + ": " + (int)Math.round(Double.parseDouble(driverInfo[1])) + " miles");
    //            }
    //  }

    //  Set set = treeMap.entrySet();
    //
    // // Get an iterator
    // Iterator it = set.iterator();
    //
    // // Display elements
    // while(it.hasNext()) {
    //   Map.Entry me = (Map.Entry)it.next();
    //   String[] driverInfo1 = treeMap.get(entry.getKey()).toArray(new String[treeMap.get(entry.getKey()).size()]);
    //   System.out.println(me.getValue());
    //   System.out.print("Key is: "+me.getKey() + " & ");
    //   System.out.println("Value is: "+me.getValue());
    // }



    // for (Map.Entry entry : treeMap.entrySet()) {
    //      String[] driverInfo = treeMap.get(entry.getKey()).toArray(new String[treeMap.get(entry.getKey()).size()]);
    //      System.out.println(driverInfo[2]);
    // }
          // for (int i = 0; i < tripsMap.size(); i++) {
          //      treeMap.put(driverInfo[i].getMilesDriven(), driverInfo[i]);
          // }

                    //Works But No Sorting
//           ---------------------------------------------------
          for (Map.Entry entry : tripsMap.entrySet()) {
               String[] driverInfo = tripsMap.get(entry.getKey()).toArray(new String[tripsMap.get(entry.getKey()).size()]);


               if (calculateMPH(driverInfo[1], driverInfo[0]) != 0) {
                    System.out.println(entry.getKey() + ": " + (int)Math.round(Double.parseDouble(driverInfo[1])) + " miles @ " + calculateMPH(driverInfo[1], driverInfo[0]) + " mph");
               }else {
                    System.out.println(entry.getKey() + ": " + (int)Math.round(Double.parseDouble(driverInfo[1])) + " miles");
               }

          }

     }

     public static int calculateMPH(String miles, String minutes){
          if (miles != "0"  && minutes != "0" ) {
               double min = Double.parseDouble(minutes);
               double mil = Double.parseDouble(miles);
               return (int) Math.round(mil/min * 60);

          }else {
               return 0;
          }
     }


     public static String calculateDifference(String start, String end){
          int startHour = Integer.parseInt(start.substring(0,2));
          int startMin = Integer.parseInt(start.substring(3,5));
          int endHour = Integer.parseInt(end.substring(0,2));
          int endMin = Integer.parseInt(end.substring(3,5));
          int totalHours = endHour - startHour;
          int totalMinutes = endMin - startMin;
          int convertHTM = totalHours * 60;
          return Integer.toString((convertHTM + totalMinutes));
     }







}
