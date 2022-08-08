package Hackerrank.DataStructures.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Start time: 7:27
End time: 7:48
 */
public class TruckTour {
    public static int truckTour(List<List<Integer>> petrolpumps) {
        int total = petrolpumps.size();
        List<Integer> pumps = new ArrayList<>(total);

        int totalPetrol = 0;
        int totalDistance = 0;
        for(int i = 0; i < total; i ++){
            int petrol = petrolpumps.get(i).get(0);
            int distance = petrolpumps.get(i).get(1);

            totalPetrol += petrol;
            totalDistance += distance;
            pumps.add(petrol - distance);
        }

        int finalContainerLevel = totalPetrol - totalDistance;
        if(finalContainerLevel < 0){
            return -1;
        }

        int initContainerLevel = 0;
        int minContainerLevel = -1;
        for(Integer pump: pumps){
            initContainerLevel += pump;
            minContainerLevel = Math.min(minContainerLevel, initContainerLevel);
        }

        if(minContainerLevel >= 0){
            return 1;
        }

        for(int i = 1; i < total; i++){
            minContainerLevel -= pumps.get(i - 1);
            finalContainerLevel += pumps.get(i - 1);

            if(minContainerLevel >= 0){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        List<List<Integer>> pumps = new ArrayList<>();
        pumps.add(Arrays.asList(1, 5));
        pumps.add(Arrays.asList(10, 3));
        pumps.add(Arrays.asList(3, 4));

        int result = (new TruckTour()).truckTour(pumps);
    }
}
