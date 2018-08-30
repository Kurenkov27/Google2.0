import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hard {
    public static void main(String[] args) {
        int[] [] buildings = new int[] [] { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        System.out.print("[");
        int count = 0;
        for (int[] elem: getSkyline(buildings)) {
            count++;
            if(count <getSkyline(buildings).size()) {
                System.out.print(Arrays.toString(elem) + ", ");
            } else if(count == getSkyline(buildings).size()){
                System.out.print(Arrays.toString(elem));
            }
        }
        System.out.print("]");
        //System.out.println(buildings[1][2]); //15
    }

    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> finalList = new ArrayList<>();

        int length = buildings.length;

        List jump = new ArrayList();
        int Rmax = buildings[0][1];
        for (int i = 1; i < length; i++) {
            if (buildings[i][0] > Rmax){
                jump.add(Rmax);
                Rmax = buildings[i][1];
            }
            Rmax = getMaxValue(Rmax,buildings[i][1]);
        }

        Rmax = buildings[0][1];
        List step = new ArrayList();
        List stepH = new ArrayList();
        int Hmax1 = 0;
        for (int i = 1; i < length; i++) {
            if (buildings[i][0] > buildings[i-1][1] && buildings[i][0] < Rmax){
                step.add(buildings[i-1][1]);
                for(int h = 0; h<length; h++){
                    if (buildings[i-1][1]>buildings[h][0] && buildings[i-1][1]<buildings[h][1]){
                        Hmax1 = getMaxValue(Hmax1, buildings[h][2]);
                    }
                }
                stepH.add(Hmax1);
            }
            Rmax = getMaxValue(Rmax,buildings[i][1]);
        }

        List x = new ArrayList();
        for(int i = 0; i < length; i++){
            x.add(buildings[i][0]);
        }
        for(int i = length; i < 2*length; i++){
            x.add(buildings[i%length][1]);
        }

        Collections.sort(x);

        List<int[]> maxHeights = new ArrayList();

        for (int i = 0; i < 2*length; i++){
            int Hmax = 0;

            for (int k = 0; k < length; k++){
                if ((Integer) x.get(i) >= buildings[k][0] && (Integer) x.get(i) <= buildings[k][1]){
                        Hmax = getMaxValue(Hmax, buildings[k][2]);
                }
            }

            int[] xAndHeight = new int[2];
            xAndHeight[0] = (Integer) x.get(i);
            xAndHeight[1] = Hmax;

            maxHeights.add(xAndHeight);
        }

        int[] arr0 = new int[] {maxHeights.get(0)[0], maxHeights.get(0)[1]};
        finalList.add(arr0);

        for (int j = 0; j < maxHeights.size() - 1; j++) {

            for (int f = 0; f < jump.size(); f++) {
                if (maxHeights.get(j)[0] == (Integer) jump.get(f)) {
                    maxHeights.get(j)[1] = 0;
                    int[] arr1 = new int[]{maxHeights.get(j)[0], 0};
                    finalList.add(arr1);
                }
            }

            for(int f = 0; f < step.size(); f++) {
                if (maxHeights.get(j)[0] == (Integer) step.get(f)) {
                    maxHeights.get(j)[1] = (Integer) stepH.get(f);
                    int[] arr1 = new int[]{maxHeights.get(j)[0], maxHeights.get(j)[1]};
                    finalList.add(arr1);
                }

            }
            if(maxHeights.get(j+1)[1] > maxHeights.get(j)[1]){
                int[] arr1 = new int[] {maxHeights.get(j+1)[0], maxHeights.get(j+1)[1]};
                finalList.add(arr1);
            } else if (maxHeights.get(j+1)[1] < maxHeights.get(j)[1]){
                int[] arr1 = new int[] {maxHeights.get(j)[0], maxHeights.get(j+1)[1]};
                finalList.add(arr1);
            }
        }

        int[] arrEnd = new int[] {maxHeights.get(maxHeights.size()-1)[0], 0};
        finalList.add(arrEnd);

        return finalList;
    }

    public static int getMaxValue(int a, int b){
        int maxValue = a;
            if(a >= b){
                maxValue = a;
            } else{
                maxValue = b;
            }
        return maxValue;
    }

}
