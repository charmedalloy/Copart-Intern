/*Avaljot Khurana
Pavithra Sridharan
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class MinimumDistance {
    Map locationMap;
    Map ruleMap;
    double distanceMin = Integer.MAX_VALUE;
    String minzip = null;

    public static void main(String[] args) throws Exception {
        MinimumDistance d = new MinimumDistance();
        d.locationMap = d.putCopartLocationInMap(new File("copartLocations.csv"));
        d.ruleMap = d.putRulesInMap(new File("Rules.csv"));
        System.out.println("Enter zip: ");
        Scanner in = new Scanner(System.in);
        String zip = in.next();
        System.out.println("Enter customer id: ");
        String custId = in.next();
//        System.out.println("Enter vehicle type: ");
//        String vehicleType = in.next();
        ArrayList<Rules> rule = new ArrayList<>();
        if (d.ruleMap.containsKey(custId)) {
            rule = (ArrayList<Rules>) d.ruleMap.get(custId);
            String state = ZipToLocation.getPlaceFromZipCode(zip); //getting the state of user
            System.out.println("user state:" + state);
            String minZipCode = "";
            for (Rules r : rule) {
                if (r.outOfState == -1) { //only the user state locations are allowed
                    for (Object curzip : d.locationMap.keySet()) {
                        CopartLocations locs = (CopartLocations) d.locationMap.get(curzip);
                        if (locs.stateCode.toString().equals(state)) {
                            if (r.yard.length == 1 && r.yard[0] == 0) { //yard no is not present
                                minZipCode = d.calculateDistance(zip, (String) curzip);
                            } else {
                                for (int i : r.yard) {
                                    if (String.valueOf(i).equals(locs.yardNo)) {
                                        minZipCode = d.calculateDistance(zip, (String) curzip);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (Object curzip : d.locationMap.keySet()) {
                        CopartLocations locs = (CopartLocations) d.locationMap.get(curzip);
                        if (r.yard.length == 1 && r.yard[0] == 0) { //yard no is not present
                            minZipCode = d.calculateDistance(zip, (String) curzip);
                        } else {
                            for (int i : r.yard) {
                                if (String.valueOf(i).equals(locs.yardNo)) {
                                    minZipCode = d.calculateDistance(zip, (String) curzip);
                                }
                            }
                        }

                    }
                }
            }
            if (d.locationMap.containsKey(minZipCode)) {
                System.out.println(minZipCode);
                CopartLocations nearestLoc = (CopartLocations) d.locationMap.get(minZipCode);
                System.out.println(nearestLoc.address);
            }

        }
    }

    Map putRulesInMap(File f) throws FileNotFoundException {
        HashMap<String, ArrayList<Rules>> ruleMap = new HashMap<>();
        Scanner s = new Scanner(f);
        int[] yard;
        while (s.hasNextLine()) {
            try {
                String now = s.nextLine();
                now = now.replace("\"", "");
                String str[] = now.split(",");
                if (str.length == 3) {
                    yard = new int[1];
                    yard[0] = 0;
                } else {
                    yard = new int[str.length - 3];
                }
                if (str.length != 3) {
                    for (int i = 3; i < str.length; i++) {
                        yard[i - 3] = Integer.parseInt(str[i]);
                    }
                }
                Rules rule = new Rules(Integer.parseInt(str[1]), Integer.parseInt(str[2]), yard);
                if (ruleMap.containsKey(str[1])) {
                    ruleMap.get(str[1]).add(rule);
                } else {
                    ArrayList<Rules> r = new ArrayList<>();
                    r.add(rule);
                    ruleMap.put(str[1], r);
                }

            } catch (Exception e) {
                //just skip mismatched locations
            }
        }
        return ruleMap;
    }

    Map putCopartLocationInMap(File f) throws FileNotFoundException {
        HashMap<String, CopartLocations> locMap = new HashMap<>();
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            try {
                String line = s.nextLine();
                String str[] = line.split(",");
                CopartLocations copartLoc = new CopartLocations(str[0], str[1], str[2], str[3], str[4], str[5]);
                locMap.put(str[3], copartLoc);
            } catch (Exception e) {

            }
        }
        return locMap;
    }

    private String calculateDistance(String zip, String curzip) {

        double value = ZipToLocation.getDistance(zip, curzip);
        if (value < distanceMin) {
            distanceMin = value;
            minzip = (String) curzip;
        }
        return minzip;
    }
}

